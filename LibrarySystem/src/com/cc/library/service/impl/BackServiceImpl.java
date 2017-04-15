package com.cc.library.service.impl;

import com.cc.library.dao.BackDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BackService;

public class BackServiceImpl implements BackService{

	private BackDao backDao;

	public void setBackDao(BackDao backDao) {
		this.backDao = backDao;
	}

	@Override
	public PageBean<BorrowInfo> findBackInfoByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return backDao.findBackInfoByPage(pageCode,pageSize);
	}

	@Override
	public BackInfo getBackInfoById(BackInfo backInfo) {
		// TODO Auto-generated method stub
		return backDao.getBackInfoById(backInfo);
	}
	
	
}
