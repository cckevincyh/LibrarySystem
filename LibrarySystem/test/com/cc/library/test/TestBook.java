package com.cc.library.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.Reader;

public class TestBook extends BaseSpring{
	@Test
	public void testFindBook(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		String hql= "from Book";
		List createQuery = session.createQuery(hql).list();
		Book book = (Book) createQuery.get(0);
		System.out.println(book);
		System.out.println(book.getBookType());
	}
	
	
	
	
	@Test
	public void testSaveBook(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Book book = new Book();
		book.setBookName("1111");
		BookType type = new BookType();
		type.setTypeId(1);
		book.setBookType(type);
		session.save(book);
		
		transaction.commit();
		session.close();
	}
	
	
	
	@Test
	public void testDelete(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Book book = (Book) session.get(Book.class, 1);
		session.delete(book);
		transaction.commit();
		session.close();
	}
}
