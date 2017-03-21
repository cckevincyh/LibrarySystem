package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public interface BookDao {

	public List<BookType> getAllBookTypes();

	public PageBean<BookType> findBookByPage(int pageCode, int pageSize);

}
