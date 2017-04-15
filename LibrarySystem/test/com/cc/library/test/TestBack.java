package com.cc.library.test;

import java.util.List;

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
	
	
	@Test
	public void testGetBack2(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		List list = session.createSQLQuery("select ba.borrowId from BackInfo ba ,BorrowInfo bo,Book bk,Reader r "
	+"where ba.borrowId=bo.borrowId and Bk.bookId=Bo.bookId and bo.readerId=r.readerId "
		+"and bk.ISBN like '%1%' and r.paperNO like '%1%'").list();
		Object objects =  list.get(0);
		System.out.println(objects);
		session.close();
	}
}
