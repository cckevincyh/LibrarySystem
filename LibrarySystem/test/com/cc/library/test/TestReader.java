package com.cc.library.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;

public class TestReader extends BaseSpring{

	@Test
	public void testSaveReader(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Reader reader = new Reader();
		reader.setReaderId("123");
		reader.setName("菜肉");
		reader.setPwd("123");
		//reader.setReaderType(1);
		session.save(reader);
		transaction.commit();
		session.close();
	}
	
	
	@Test
	public void getReader(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		String hql= "from Reader r where r.readerId=123 and r.state=1";
		List createQuery = session.createQuery(hql).list();
		Reader reader = (Reader) createQuery.get(0);
		System.out.println(reader);

	}
	
	
	
	
	@Test
	public void testSaveReader2(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Reader reader = new Reader();
		reader.setReaderId("123");
		reader.setName("菜肉");
		reader.setPwd("123");
		ReaderType readerType = new ReaderType();
		readerType.setReaderTypeId(1);
		reader.setReaderType(readerType);
		
	
		
		session.save(reader);
		transaction.commit();
		session.close();
	}
}
