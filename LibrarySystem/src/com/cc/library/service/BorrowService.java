package com.cc.library.service;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public interface BorrowService {

	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

	public BorrowInfo getBorrowInfoById(BorrowInfo info);

	public boolean addBorrow(BorrowInfo info);

}
