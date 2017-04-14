package com.cc.library.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.BackDao;
import com.cc.library.domain.BackInfo;

public class BackDaoImpl extends HibernateDaoSupport implements BackDao{

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

}
