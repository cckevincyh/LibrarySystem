package com.cc.library.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.ReaderTypeDao;

import com.cc.library.domain.ReaderType;

public class ReaderTypeDaoImpl extends HibernateDaoSupport implements ReaderTypeDao{

	@Override
	public List<ReaderType> getAllReaderType() {

		String hql= "from ReaderType";
		List list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
		
	}

	@Override
	public ReaderType getTypeById(ReaderType readerType) {
		String hql= "from ReaderType r where r.readerTypeId=?";
		List list = this.getHibernateTemplate().find(hql, readerType.getReaderTypeId());
		if(list!=null && list.size()>0){
			return (ReaderType) list.get(0);
		}
		return null;
	}

	@Override
	public ReaderType updateReaderType(ReaderType updateReaderType) {
		ReaderType newReaderType = null;
		try{
			this.getHibernateTemplate().clear();
			//将传入的detached(分离的)状态的对象的属性复制到持久化对象中，并返回该持久化对象
			newReaderType = (ReaderType) this.getHibernateTemplate().merge(updateReaderType);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return newReaderType;
	}

	@Override
	public boolean addReaderType(ReaderType readerType) {
		boolean b = true;
		try{
			this.getHibernateTemplate().clear();
			this.getHibernateTemplate().save(readerType);
			this.getHibernateTemplate().flush();
		}catch (Throwable e1) {
			b = false;
			e1.printStackTrace();
			throw new RuntimeException(e1.getMessage());
		}
		return b;
	}

}
