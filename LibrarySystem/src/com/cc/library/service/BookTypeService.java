package com.cc.library.service;

import java.util.List;

import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public interface BookTypeService {

	public PageBean<BookType> findBookTypeByPage(int pageCode, int pageSize);

	public BookType getBookTypeByName(BookType bookType);

	public boolean addBookType(BookType bookType);

	public BookType getBookTypeById(BookType bookType);

	public BookType updateBookTypeInfo(BookType bookType);

	public boolean deleteBookType(BookType bookType);

	public PageBean<BookType> queryBookType(BookType bookType, int pageCode,
			int pageSize);

	public BookType getBookType(BookType bookType);

	public List<BookType> getAllBookTypes();
}
