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

import com.cc.library.dao.ForfeitDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public class ForfeitDaoImpl extends HibernateDaoSupport implements ForfeitDao{

	@Override
	public List<ForfeitInfo> getForfeitByReader(Reader reader) {
		// TODO Auto-generated method stub
		String hql = "SELECT f.borrowId,f.forfeit,f.isPay,f.aid FROM forfeitinfo  f,borrowinfo  b where  b.borrowId = f.borrowId and b.readerId =?";
		List list = null;
		try{
			Session session = this.getSession();
			SQLQuery createSQLQuery = session.createSQLQuery(hql);
			createSQLQuery.setInteger(0, reader.getReaderId());
			list = createSQLQuery.list();
			if(list!=null){
				List<ForfeitInfo> infos = new ArrayList<ForfeitInfo>();
				for(int i = 0;i<list.size();i++){
					Object[] objects = (Object[]) list.get(i);
					Integer borrowId = (Integer) objects[0];
					Double forfeit = (Double) objects[1];
					Integer isPay = (Integer) objects[2];
					Integer aid = (Integer) objects[3];
					Admin admin = new Admin();
					admin.setAid(aid);
					BorrowInfo info = new BorrowInfo();
					info.setBorrowId(borrowId);
					ForfeitInfo forfeitInfo = new ForfeitInfo();
					forfeitInfo.setAdmin(admin);
					forfeitInfo.setBorrowId(borrowId);
					forfeitInfo.setForfeit(forfeit);
					forfeitInfo.setIsPay(isPay);
					infos.add(forfeitInfo);
				}
				return infos;
			}
		}catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean addForfeitInfo(ForfeitInfo forfeitInfo) {
		boolean  b  = true;
		try{
			this.getHibernateTemplate().clear();	
			this.getHibernateTemplate().save(forfeitInfo);
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
	public PageBean<ForfeitInfo> findForfeitInfoByPage(int pageCode,
			int pageSize) {
		PageBean<ForfeitInfo> pb = new PageBean<ForfeitInfo>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List forfeitInfoList = null;
		try {
			String sql = "SELECT count(*) FROM ForfeitInfo";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString()); //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from ForfeitInfo";
			//分页查询
			forfeitInfoList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(forfeitInfoList!=null && forfeitInfoList.size()>0){
			pb.setBeanList(forfeitInfoList);
			return pb;
		}
		return null;
	}

	


	@Override
	public ForfeitInfo getForfeitInfoById(ForfeitInfo forfeitInfo) {
		String hql= "from ForfeitInfo f where f.borrowId=?";
		List list = null;
		try{
			list = this.getHibernateTemplate().find(hql, forfeitInfo.getBorrowId());
		}catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		if(list!=null && list.size()>0){
			return (ForfeitInfo) list.get(0);
		}
		return null;
	}

	@Override
	public ForfeitInfo updateForfeitInfo(ForfeitInfo forfeitInfoById) {
		ForfeitInfo forfeitInfo = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			forfeitInfo = (ForfeitInfo) this.getHibernateTemplate().merge(forfeitInfoById);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return forfeitInfo;
	}
	
	
	

}
