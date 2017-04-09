package com.cc.library.service;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public interface BorrowService {

	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

	public BorrowInfo getBorrowInfoById(BorrowInfo info);

	/**
	 * 返回借阅状态码
	 * @param info
	 * @return
	 */
	public int addBorrow(BorrowInfo info);

}
