package com.cc.library.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;
import com.cc.library.domain.ForfeitInfo;

public class TestBorrowInfo  extends BaseSpring{

	@Test
	public void testGetInfo(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		List list = session.createSQLQuery("SELECT f.borrowId,f.forfeit,f.isPay,f.aid FROM forfeitinfo  f,borrowinfo  b where  b.borrowId = f.borrowId and b.readerId =?").setString(0,"1").list();

		Object[] object = (Object[]) list.get(0);
		System.out.println(object[0]);
		
	session.close();
	}
}
