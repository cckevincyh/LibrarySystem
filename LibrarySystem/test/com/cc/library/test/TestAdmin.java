package com.cc.library.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.cc.library.util.Md5Utils;

public class TestAdmin extends BaseSpring{

	
	@Test
	public void testSaveAdmin(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Admin admin = new Admin();
		admin.setName("cairou");
		admin.setUsername("admin");
		admin.setPwd(Md5Utils.md5("admin"));
		session.save(admin);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testGetAdmin(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Admin admin = (Admin) session.get(Admin.class, 1);
		System.out.println(admin);
		System.out.println(admin.getAuthorization().getSuperSet());
		session.close();
		
	}
	
	
	@Test
	public void testSaveAdmin2(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Admin admin = new Admin();
		admin.setName("cairou");
		admin.setUsername("admin");
		admin.setPwd("admin");
		Authorization authorization = new Authorization();
		authorization.setSuperSet(1);
		authorization.setAdmin(admin);
		admin.setAuthorization(authorization);
		session.save(admin);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testGetAdmin3(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Authorization authorization = (Authorization) session.get(Authorization.class, 1);
		System.out.println(authorization.getAdmin().getName());
		session.close();
	}

	
	@Test
	public void testSaveAdmin3(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Authorization authorization = new Authorization();
		
		authorization.setAid(2);
		session.save(authorization);
		
		transaction.commit();
		session.close();
	}
	
	@Test
	public void testDeleteAdmin1(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Admin admin = (Admin) session.get(Admin.class, 1);
		session.delete(admin);
		transaction.commit();
		session.close();
	}
}
