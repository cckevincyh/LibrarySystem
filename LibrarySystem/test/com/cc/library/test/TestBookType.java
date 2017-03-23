package com.cc.library.test;

import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Test;

import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;


public class TestBookType extends BaseSpring{

	@Test
	public void testFindBookType(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		String hql= "from BookType";
		List createQuery = session.createQuery(hql).list();
		BookType bookType = (BookType) createQuery.get(0);
		System.out.println(bookType);
		Set<Book> books = bookType.getBooks();
		System.out.println(books.size());
		
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Set||name.equals("books")){
				return true;
			}else{
				return false;
			}
		   }
		});
		
		String json = JSONArray.fromObject(createQuery,jsonConfig).toString();//List------->JSONArray
		System.out.println(json);
		
	}
}


