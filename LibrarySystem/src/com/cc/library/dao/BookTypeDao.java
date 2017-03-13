package com.cc.library.dao;

import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;

public interface BookTypeDao {

	PageBean<BookType> findBookTypeByPage(int pageCode, int pageSize);

}
