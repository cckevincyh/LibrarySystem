package com.cc.library.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;

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
}
