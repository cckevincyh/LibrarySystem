package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.Reader;

public class ReaderDaoImpl extends HibernateDaoSupport implements ReaderDao{

	@Override
	public Reader getReader(Reader reader) {
		//this.getHibernateTemplate().find(hql, value)方法无法执行的问题(未解决)
		//解决需要catch (Throwable e)
		String hql= "from Reader r where r.readerId=? and r.state=1";
		try {
			List list = this.getHibernateTemplate().find(hql, reader.getReaderId());
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

}
