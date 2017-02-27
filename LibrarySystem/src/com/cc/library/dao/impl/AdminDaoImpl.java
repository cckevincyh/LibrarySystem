package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.AdminDao;
import com.cc.library.domain.Admin;

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{

	@Override
	public Admin getAdmin(Admin admin) {
		String hql= "from Admin a where a.username=? and a.state=1";
		List list = this.getHibernateTemplate().find(hql, admin.getUsername());
		if(list!=null && list.size()>0){
			return (Admin) list.get(0);
		}
		return null;
	}

	
}
