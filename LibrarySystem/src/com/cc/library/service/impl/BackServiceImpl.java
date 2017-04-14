package com.cc.library.service.impl;

import com.cc.library.dao.BackDao;
import com.cc.library.service.BackService;

public class BackServiceImpl implements BackService{

	private BackDao backDao;

	public void setBackDao(BackDao backDao) {
		this.backDao = backDao;
	}
	
	
}
