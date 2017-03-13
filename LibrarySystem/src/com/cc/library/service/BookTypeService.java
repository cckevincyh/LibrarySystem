package com.cc.library.service;

import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public interface BookTypeService {

	public PageBean<BookType> findBookTypeByPage(int pageCode, int pageSize);

	public BookType getBookTypeByName(BookType bookType);

	public boolean addBookType(BookType bookType);

}
