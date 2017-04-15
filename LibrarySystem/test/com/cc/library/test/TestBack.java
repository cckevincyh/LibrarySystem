package com.cc.library.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.dao.BackDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;

public class TestBack extends BaseSpring{

	@Test
	public void testSaveBack(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		BackInfo backInfo = new BackInfo();
		BorrowInfo borrowInfo = new BorrowInfo();
		borrowInfo.setBorrowId(1);
		backInfo.setBorrowInfo(borrowInfo);
		backInfo.setBorrowId(1);
		session.save(backInfo);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testGetBack(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		BackInfo back = (BackInfo) session.get(BackInfo.class, 4);
		System.out.println(back);
		session.close();
	}
}
