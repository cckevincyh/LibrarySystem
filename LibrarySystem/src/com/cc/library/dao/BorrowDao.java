package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.Book;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public interface BorrowDao {

	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

	public BorrowInfo getBorrowInfoById(BorrowInfo info);

	public int addBorrow(BorrowInfo info);

	public List<BorrowInfo> getNoBackBorrowInfoByReader(Reader reader);

	public BorrowInfo updateBorrowInfo(BorrowInfo borrowInfoById);


	public List<BorrowInfo> getBorrowInfoByNoBackState();

	public List<BorrowInfo> getBorrowInfoByBook(Book book);

}
