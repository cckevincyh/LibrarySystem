package com.cc.library.service.impl;

import com.cc.library.dao.BorrowDao;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BorrowService;

public class BorrowServiceImpl implements BorrowService{

	private BorrowDao borrowDao;

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	@Override
	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return borrowDao.findBorrowInfoByPage(pageCode,pageSize);
	}
	
	
}
