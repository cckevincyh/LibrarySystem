package com.cc.library.dao;

import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public interface BookTypeDao {

	public PageBean<BookType> findBookTypeByPage(int pageCode, int pageSize);

	public BookType getBookTypeByName(BookType bookType);

	public boolean addBookType(BookType bookType);

	public BookType getBookTypeById(BookType bookType);

	public BookType updateBookTypeInfo(BookType bookType);

	public boolean deleteBookType(BookType bookType);

	public PageBean<BookType> queryBookType(BookType bookType, int pageCode,
			int pageSize);

	public Book getBookById(Book book);
}
