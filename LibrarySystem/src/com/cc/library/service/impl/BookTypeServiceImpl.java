package com.cc.library.service.impl;

import com.cc.library.dao.BookTypeDao;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookTypeService;

public class BookTypeServiceImpl implements BookTypeService{

	private BookTypeDao bookTypeDao;

	public void setBookTypeDao(BookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}

	@Override
	public PageBean<BookType> findBookTypeByPage(int pageCode, int pageSize) {
		return bookTypeDao.findBookTypeByPage(pageCode,pageSize);
	}

	@Override
	public BookType getBookTypeByName(BookType bookType) {
		return bookTypeDao.getBookTypeByName(bookType);
	}

	@Override
	public boolean addBookType(BookType bookType) {
		return bookTypeDao.addBookType(bookType);
	}

	@Override
	public BookType getBookTypeById(BookType bookType) {
		return bookTypeDao.getBookTypeById(bookType);
	}

	@Override
	public BookType updateBookTypeInfo(BookType bookType) {
		// TODO Auto-generated method stub
		return bookTypeDao.updateBookTypeInfo(bookType);
	}

	@Override
	public boolean deleteBookType(BookType bookType) {
		// TODO Auto-generated method stub
		return bookTypeDao.deleteBookType(bookType);
	}

	@Override
	public PageBean<BookType> queryBookType(BookType bookType, int pageCode,
			int pageSize) {
		// TODO Auto-generated method stub
		return bookTypeDao.queryBookType(bookType,pageCode,pageSize);
	}
	
	
}
