package com.cc.library.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public class ReaderDaoImpl extends HibernateDaoSupport implements ReaderDao{

	@Override
	public Reader getReader(Reader reader) {
		//this.getHibernateTemplate().find(hql, value)方法无法执行的问题(未解决)
		//解决需要catch (Throwable e)
		String hql= "from Reader r where r.readerId=? and r.state=1";
		try {
			List list = this.getHibernateTemplate().find(hql, reader.getReaderId());
			System.out.println(list);
			if(list!=null && list.size()>0){
				return (Reader) list.get(0);
			}	
		} catch (Throwable e1) {
			throw new RuntimeException(e1.getMessage());
		}
		
		return null;
		
//		Reader newReader = (Reader) this.getSession().get(Reader.class, reader.getReaderId());
//		this.getSession().close();
//		return newReader;
	}

	
	@Override
	public Reader updateReaderInfo(Reader reader) {
		Reader newReader = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newReader = (Reader) this.getHibernateTemplate().merge(reader);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newReader;
	}

	
	
	@Override
	public boolean addReader(Reader reader) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(reader);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	
	
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
	public PageBean<Reader> findReaderByPage(int pageCode, int pageSize) {
		PageBean<Reader> pb = new PageBean<Reader>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List readerList = null;
		try {
			String sql = "SELECT count(*) FROM Reader r where r.state=1";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from Reader r where r.state=1";
			//分页查询
			readerList = doSplitPage(hql,pageCode,pageSize);
			

		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(readerList!=null && readerList.size()>0){
			pb.setBeanList(readerList);
			return pb;
		}
		return null;
	}


	@Override
	public Reader getReaderById(Reader reader) {
		String hql= "from Reader r where r.readerId=? and r.state=1";
		List list = this.getHibernateTemplate().find(hql, reader.getReaderId());
		if(list!=null && list.size()>0){
			return (Reader) list.get(0);
		}
		return null;
	}


	@Override
	public boolean deleteReader(Reader reader) {
		//把读者的状态修改为0
		boolean b = true;
		try{
			Reader deleteReader = getReaderById(reader);
			deleteReader.setState(0);
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().update(deleteReader);
			this.getHibernateTemplate().flush();
		}catch  (Throwable e1){
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}
	

}
