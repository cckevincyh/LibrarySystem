package com.cc.library.dao;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public interface BorrowDao {

	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

	public BorrowInfo getBorrowInfoById(BorrowInfo info);

	public boolean addBorrow(BorrowInfo info);

}
