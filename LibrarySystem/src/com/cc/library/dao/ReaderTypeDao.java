package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.ReaderType;

public interface ReaderTypeDao {

	public List<ReaderType> getAllReaderType();

	public ReaderType getTypeById(ReaderType readerType);

	public ReaderType updateReaderType(ReaderType updateReaderType);

}
