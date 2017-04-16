package com.cc.library.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.BorrowDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.Book;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public class BorrowDaoImpl extends HibernateDaoSupport implements BorrowDao{

	
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
	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize) {
		PageBean<BorrowInfo> pb = new PageBean<BorrowInfo>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List borrowInfoList = null;
		try {
			String sql = "SELECT count(*) FROM BorrowInfo";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from BorrowInfo";
			//分页查询
			borrowInfoList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(borrowInfoList!=null && borrowInfoList.size()>0){
			pb.setBeanList(borrowInfoList);
			return pb;
		}
		return null;
	}




	@Override
	public BorrowInfo getBorrowInfoById(BorrowInfo info) {
		String hql= "from BorrowInfo b where b.borrowId=?";
		List list = this.getHibernateTemplate().find(hql, info.getBorrowId());
		if(list!=null && list.size()>0){
			return (BorrowInfo) list.get(0);
		}
		return null;
	}




	@Override
	public int addBorrow(BorrowInfo info) {
		Integer integer = 0;
		try{
			this.getHibernateTemplate().clear();
			//save方法返回的是Serializable接口，该结果的值就是你插入到数据库后新记录的主键值
			Serializable save = this.getHibernateTemplate().save(info);
			this.getHibernateTemplate().flush();
			integer = (Integer)save;
		}catch (Throwable e1) {
			integer = 0;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return integer;
	}




	@Override
	public List<BorrowInfo> getBorrowInfoByReader(Reader reader) {
		String hql= "from BorrowInfo b where b.reader.readerId=?";
		List list = null;
		try {
			list = this.getHibernateTemplate().find(hql, reader.getReaderId());
		}catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return list;
	}




	@Override
	public BorrowInfo updateBorrowInfo(BorrowInfo borrowInfoById) {
		BorrowInfo borrowInfo = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			borrowInfo = (BorrowInfo) this.getHibernateTemplate().merge(borrowInfoById);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return borrowInfo;
	}
	
	
	
	

}
