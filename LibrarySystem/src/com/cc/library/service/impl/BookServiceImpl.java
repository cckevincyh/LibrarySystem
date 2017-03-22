package com.cc.library.service.impl;

import java.util.List;

import com.cc.library.dao.BookDao;
import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;

public class BookServiceImpl implements BookService{

	private BookDao bookDao;

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<BookType> getAllBookTypes() {
		// TODO Auto-generated method stub
		return bookDao.getAllBookTypes();
	}

	@Override
	public PageBean<BookType> findBookByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return bookDao.findBookByPage(pageCode,pageSize);
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.addBook(book);
	}

	@Override
	public BookType getBookType(BookType bookType) {
		// TODO Auto-generated method stub
		return bookDao.getBookType(bookType);
	}


}
