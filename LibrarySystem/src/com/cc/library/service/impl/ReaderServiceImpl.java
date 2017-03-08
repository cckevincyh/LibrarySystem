package com.cc.library.service.impl;

import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.service.ReaderService;

public class ReaderServiceImpl implements ReaderService{

	private ReaderDao readerDao;

	

	public void setReaderDao(ReaderDao readerDao) {
		this.readerDao = readerDao;
	}



	@Override
	public Reader getReader(Reader reader) {
		return readerDao.getReader(reader);
	}



	@Override
	public Reader updateReaderInfo(Reader reader) {
		return readerDao.updateReaderInfo(reader);
	}



	@Override
	public boolean addReader(Reader reader) {
		return readerDao.addReader(reader);
	}



	@Override
	public PageBean<Reader> findReaderByPage(int pageCode, int pageSize) {
		return readerDao.findReaderByPage(pageCode,pageSize);
	}
	
	
}
