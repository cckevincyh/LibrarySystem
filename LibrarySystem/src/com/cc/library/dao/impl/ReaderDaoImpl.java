package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.Reader;

public class ReaderDaoImpl extends HibernateDaoSupport implements ReaderDao{

	@Override
	public Reader getReader(Reader reader) {
		//this.getHibernateTemplate().find(hql, value)方法无法执行的问题(未解决)
		Reader newReader = (Reader) this.getSession().get(Reader.class, reader.getReaderId());
		this.getSession().close();
		return newReader;
	}

}
