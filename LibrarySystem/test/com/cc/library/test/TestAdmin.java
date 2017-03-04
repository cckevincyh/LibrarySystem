package com.cc.library.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Admin;

public class TestAdmin extends BaseSpring{

	
	@Test
	public void testSaveAdmin(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Admin admin = new Admin();
		admin.setName("cairou");
		admin.setUsername("admin");
		admin.setPwd("admin");
		admin.setAdminType(0);
		session.save(admin);
		transaction.commit();
		session.close();
	}
	
	

}
