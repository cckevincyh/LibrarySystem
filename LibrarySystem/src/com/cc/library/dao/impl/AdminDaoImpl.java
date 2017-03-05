package com.cc.library.dao.impl;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.AdminDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.PageBean;


public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{

	@Override
	public Admin getAdminByUserName(Admin admin) {
		String hql= "from Admin a where a.username=? and a.state=1";
		List list = this.getHibernateTemplate().find(hql, admin.getUsername());
		if(list!=null && list.size()>0){
			return (Admin) list.get(0);
		}
		return null;
	}

	
	@Override
	public Admin updateAdminInfo(Admin admin) {
		Admin newAdmin = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newAdmin = (Admin) this.getHibernateTemplate().merge(admin);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newAdmin;
	}


	@Override
	public List<Admin> getAllAdmins() {
		String hql= "from Admin a where a.adminType=1 and a.state=1";
		List<Admin> list = null;
		try{
			list = this.getHibernateTemplate().find(hql);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return list;
	}


	@Override
	public boolean addAdmin(Admin admin) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(admin);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}


	@Override
	public Admin getAdminById(Admin admin) {
		String hql= "from Admin a where a.id=? and a.state=1";
		List list = this.getHibernateTemplate().find(hql, admin.getId());
		if(list!=null && list.size()>0){
			return (Admin) list.get(0);
		}
		return null;
	}


	@Override
	public PageBean<Admin> findAdminByPage(int pageCode, int pageSize) {
		PageBean<Admin> pb = new PageBean<Admin>();	//pageBean对象，用于分页
		//根据传入的pageCode当前页码和pageSize页面记录数来设置pb对象
		pb.setPageCode(pageCode);//设置当前页码
		pb.setPageSize(pageSize);//设置页面记录数
		List adminList = null;
		try {
			String sql = "SELECT count(*) FROM Admin";
			List list = this.getSession().createQuery(sql).list();
			int totalRecord = Integer.parseInt(list.get(0).toString())-1; //得到总记录数
			pb.setTotalRecord(totalRecord);	//设置总记录数
			this.getSession().close();
			
			//不支持limit分页
			String hql= "from Admin a where a.state=1 and a.adminType=1";
			//分页查询
			adminList = doSplitPage(hql,pageCode,pageSize);
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		if(adminList!=null && adminList.size()>0){
			pb.setBeanList(adminList);
			return pb;
		}
		return null;
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
	
}
