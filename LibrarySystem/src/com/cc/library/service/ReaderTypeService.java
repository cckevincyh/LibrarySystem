package com.cc.library.service;

import java.util.List;

import com.cc.library.dao.ReaderTypeDao;
import com.cc.library.domain.ReaderType;

public interface ReaderTypeService {

	public  List<ReaderType> getAllReaderType();

	public ReaderType getTypeById(ReaderType readerType);

	public ReaderType updateReaderType(ReaderType updateReaderType);
	
	
	
	

}
