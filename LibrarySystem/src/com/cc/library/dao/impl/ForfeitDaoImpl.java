package com.cc.library.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cc.library.dao.ForfeitDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
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
	
	
	

}
