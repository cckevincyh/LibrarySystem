package com.cc.library.service;

import java.util.List;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public interface BorrowService {

	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

	public BorrowInfo getBorrowInfoById(BorrowInfo info);

	/**
	 * 返回借阅状态码
	 * @param info
	 * @return
	 */
	public int addBorrow(BorrowInfo info);

	public boolean checkBorrowInfo();

	public int renewBook(BorrowInfo borrowInfo);



}
