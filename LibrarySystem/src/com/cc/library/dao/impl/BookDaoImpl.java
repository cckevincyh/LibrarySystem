package com.cc.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.BookDao;
import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public class BookDaoImpl extends HibernateDaoSupport implements BookDao{

	
	
	/**
     * 
     * @param hql传入的hql语句
     * @param pageCode当前页
     * @param pageSize每页显示大小
     * @return
     */
    public List doSplitPage(final String hql,final int pageCode,final int pageSize){
        //调用模板的execute方法，参数是实现了HibernateCallback接口的匿名类，
        return (List) this.getHibernateTemplate().execute(new HibernateCallback(){
            //重写其doInHibernate方法返回一个object对象，
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                //创建query对象
                Query query=session.createQuery(hql);
                //返回其执行了分布方法的list
                return query.setFirstResult((pageCode-1)*pageSize).setMaxResults(pageSize).list();
                 
            }
             
        });
         
    }
	
	
	
	@Override
	public PageBean<Book> findBookByPage(int pageCode, int pageSize) {
		PageBean<Book> pb = new PageBean<Book>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List bookList = null;
		try {
			String sql = "SELECT count(*) FROM Book b where b.state=1";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			//不支持limit分页
			String hql= "from Book b where b.state=1";
			//分页查询
			bookList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(bookList!=null && bookList.size()>0){
			pb.setBeanList(bookList);
			return pb;
		}
		return null;
	}



	@Override
	public boolean addBook(Book book) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(book);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}





	@Override
	public Book getBookById(Book book) {
		String hql= "from Book b where b.bookId=? and b.state=1";
		List list = this.getHibernateTemplate().find(hql, book.getBookId());
		if(list!=null && list.size()>0){
			return (Book) list.get(0);
		}
		return null;
	}



	@Override
	public Book updateBookInfo(Book updateBook) {
		Book newBook = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newBook = (Book) this.getHibernateTemplate().merge(updateBook);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newBook;
	}



	@Override
	public PageBean<Book> queryBook(Book book, int pageCode, int pageSize) {
		PageBean<Book> pb = new PageBean<Book>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		
		
		StringBuilder sb = new StringBuilder();
		StringBuilder sb_sql = new StringBuilder();
		String sql = "SELECT count(*) FROM Book b where b.state=1";
		String hql= "from Book b where b.state=1";
		sb.append(hql);
		sb_sql.append(sql);
		if(!"".equals(book.getISBN().trim())){
			sb.append(" and b.ISBN like '%" + book.getISBN() +"%'");
			sb_sql.append(" and b.ISBN like '%" + book.getISBN() +"%'");
		}
		if(!"".equals(book.getBookName().trim())){
			sb.append(" and b.bookName like '%" +book.getBookName() +"%'");
			sb_sql.append(" and b.bookName like '%" + book.getBookName() +"%'");
		}
		if(!"".equals(book.getPress())){
			sb.append(" and b.press like '%" +book.getPress() +"%'");
			sb_sql.append(" and b.press like '%" + book.getPress() +"%'");
		}
		if(!"".equals(book.getAutho().trim())){
			sb.append(" and b.autho like '%" +book.getAutho() +"%'");
			sb_sql.append(" and b.autho like '%" + book.getAutho() +"%'");
		}
		if(book.getBookType().getTypeId()!=-1){
			sb.append(" and b.bookType="+book.getBookType().getTypeId());
			sb_sql.append(" and b.bookType="+book.getBookType().getTypeId());
		}
		try{
			
			List list = this.getSession().createQuery(sb_sql.toString()).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			
			List<Book> bookList = doSplitPage(sb.toString(),pageCode,pageSize);
			if(bookList!=null && bookList.size()>0){
				pb.setBeanList(bookList);
				return pb;
			}
		}catch (Throwable e1){
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return null;
	}



	@Override
	public boolean deleteBook(Book book) {
		boolean b = true;
		try{
			Book deleteBook = getBookById(book);
			deleteBook.setState(0);
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().update(deleteBook);
			this.getHibernateTemplate().flush();
		}catch  (Throwable e1){
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public Book getBookByISBN(Book book) {
		String hql= "from Book b where b.ISBN=? and b.state=1";
		List list = this.getHibernateTemplate().find(hql, book.getISBN());
		if(list!=null && list.size()>0){
			return (Book) list.get(0);
		}
		return null;
	}




}
