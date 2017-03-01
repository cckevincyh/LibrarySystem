package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.Reader;

public class ReaderDaoImpl extends HibernateDaoSupport implements ReaderDao{

	@Override
	public Reader getReader(Reader reader) {
		//this.getHibernateTemplate().find(hql, value)方法无法执行的问题(未解决)
		//解决需要catch (Throwable e)
		String hql= "from Reader r where r.readerId=? and r.state=1";
		try {
			List list = this.getHibernateTemplate().find(hql, reader.getReaderId());
			System.out.println(list);
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

	@Override
	public Reader updateReaderInfo(Reader reader) {
		Reader newReader = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newReader = (Reader) this.getHibernateTemplate().merge(reader);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newReader;
	}

}
