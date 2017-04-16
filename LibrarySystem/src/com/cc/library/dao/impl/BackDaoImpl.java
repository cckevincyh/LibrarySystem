package com.cc.library.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.BackDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public class BackDaoImpl extends HibernateDaoSupport implements BackDao{

	
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
	public int addBack(BackInfo backInfo) {
		int b = 1;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(backInfo);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = 0;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public PageBean<BackInfo> findBackInfoByPage(int pageCode, int pageSize) {
		PageBean<BackInfo> pb = new PageBean<BackInfo>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List backInfoList = null;
		try {
			String sql = "SELECT count(*) FROM BackInfo";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from BackInfo";
			//分页查询
			backInfoList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(backInfoList!=null && backInfoList.size()>0){
			pb.setBeanList(backInfoList);
			return pb;
		}
		return null;
	}


	@Override
	public BackInfo getBackInfoById(BackInfo backInfo) {
		String hql= "from BackInfo b where b.borrowId=?";
		List list = this.getHibernateTemplate().find(hql, backInfo.getBorrowId());
		if(list!=null && list.size()>0){
			return (BackInfo) list.get(0);
		}
		return null;
	}


	@Override
	public PageBean<Integer> getBorrowIdList(String iSBN, String paperNO,int pageCode, int pageSize) {
		PageBean<Integer> pb = new PageBean<Integer>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		
		List<Integer> integers = new ArrayList<Integer>();
		StringBuilder sb = new StringBuilder();
		StringBuilder sb_sql = new StringBuilder();
		String sql = "select count(*) from BackInfo ba ,BorrowInfo bo,Book bk,Reader r "
				+"where ba.borrowId=bo.borrowId and Bk.bookId=Bo.bookId and bo.readerId=r.readerId ";
		//不支持limit分页
		String hql= "select ba.borrowId from BackInfo ba ,BorrowInfo bo,Book bk,Reader r "
				+"where ba.borrowId=bo.borrowId and Bk.bookId=Bo.bookId and bo.readerId=r.readerId ";
		sb.append(hql);
		sb_sql.append(sql);
		if(!"".equals(iSBN.trim())){
			sb.append(" and bk.ISBN like '%" + iSBN +"%'");
			sb_sql.append(" and bk.ISBN like '%" + iSBN +"%'");
		}
		if(!"".equals(paperNO.trim())){
			sb.append(" and r.paperNO like '%" + paperNO +"%'");
			sb_sql.append(" and r.paperNO like '%" + paperNO +"%'");
		}
		
		try {
			SQLQuery createSQLQuery1 = this.getSession().createSQLQuery(sb_sql.toString());
			List list = createSQLQuery1.list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			//分页查询
			List list2 = doLimitBackInfo(sb.toString(),pageCode,pageSize);
			for(Object object : list2){
				Integer i = new Integer(object.toString());
				integers.add(i);
			}
			pb.setBeanList(integers);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		

		return pb;
	}
	
	
	

    public List doLimitBackInfo(final String hql,final int pageCode,final int pageSize){
        //调用模板的execute方法，参数是实现了HibernateCallback接口的匿名类，
        return (List) this.getHibernateTemplate().execute(new HibernateCallback(){
            //重写其doInHibernate方法返回一个object对象，
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                //创建query对象
            	SQLQuery query=session.createSQLQuery(hql);
                //返回其执行了分布方法的list
                return query.setFirstResult((pageCode-1)*pageSize).setMaxResults(pageSize).list();
                 
            }
             
        });
         
    }


	@Override
	public BackInfo updateBackInfo(BackInfo backInfoById) {
		BackInfo backInfo = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			backInfo = (BackInfo) this.getHibernateTemplate().merge(backInfoById);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return backInfo;
	}

}
