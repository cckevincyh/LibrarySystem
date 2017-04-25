package com.cc.library.service.impl;

import java.util.List;

import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;
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



	@Override
	public Reader getReaderById(Reader reader) {
		return readerDao.getReaderById(reader);
	}



	@Override
	public boolean deleteReader(Reader reader) {
		return readerDao.deleteReader(reader);
	}



	@Override
	public PageBean<Reader> queryReader(Reader reader,int pageCode, int pageSize) {
		return readerDao.queryReader(reader,pageCode,pageSize);
	}



	@Override
	public Reader getReaderBypaperNO(Reader reader) {
		// TODO Auto-generated method stub
		return readerDao.getReaderBypaperNO(reader);
	}



	@Override
	public Reader getReaderByPaperNO(Reader reader) {
		// TODO Auto-generated method stub
		return readerDao.getReaderBypaperNO(reader);
	}


	
}
