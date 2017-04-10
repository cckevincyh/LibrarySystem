package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.AuthorizationDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;

public class AuthorizationDaoImpl extends HibernateDaoSupport implements AuthorizationDao{

	@Override
	public boolean addAuthorization(Authorization authorization) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(authorization);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

	@Override
	public Authorization getAuthorizationByaid(Authorization authorization) {
		String hql= "from Authorization a where a.aid=? ";
		List list = this.getHibernateTemplate().find(hql, authorization.getAid());
		if(list!=null && list.size()>0){
			return (Authorization) list.get(0);
		}
		return null;
	}

	@Override
	public Authorization updateAuthorization(Authorization authorization) {
		Authorization newAuthorization = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newAuthorization = (Authorization) this.getHibernateTemplate().merge(authorization);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newAuthorization;
	}

}
