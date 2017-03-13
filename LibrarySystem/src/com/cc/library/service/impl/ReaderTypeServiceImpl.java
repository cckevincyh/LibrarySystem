package com.cc.library.service.impl;

import java.util.List;

import com.cc.library.dao.ReaderTypeDao;
import com.cc.library.domain.ReaderType;
import com.cc.library.service.ReaderTypeService;

public class ReaderTypeServiceImpl implements ReaderTypeService{

	public ReaderTypeDao readerTypeDao;

	public void setReaderTypeDao(ReaderTypeDao readerTypeDao) {
		this.readerTypeDao = readerTypeDao;
	}

	@Override
	public List<ReaderType> getAllReaderType() {
		return readerTypeDao.getAllReaderType();
	}

	@Override
	public ReaderType getTypeById(ReaderType readerType) {
		return readerTypeDao.getTypeById(readerType);
	}

	@Override
	public ReaderType updateReaderType(ReaderType updateReaderType) {
		// TODO Auto-generated method stub
		return readerTypeDao.updateReaderType(updateReaderType);
	}
	
	
	
	
}
