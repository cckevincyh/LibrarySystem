package com.cc.library.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Admin;
import com.cc.library.domain.BorrowInfo;


public class TestBorrow  extends BaseSpring{

	
	@Test
	public void testSaveBorrow(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		BorrowInfo borrowInfo = new BorrowInfo();
		Admin admin = new Admin();
		admin.setAid(2);
		borrowInfo.setAdmin(admin);
		session.save(borrowInfo);
		
		transaction.commit();
		session.close();
	}
}
