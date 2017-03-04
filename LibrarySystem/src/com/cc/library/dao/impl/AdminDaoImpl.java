package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.AdminDao;
import com.cc.library.domain.Admin;

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

	
}
