package com.cc.library.dao;

import com.cc.library.domain.Reader;

public interface ReaderDao {

	
	public Reader getReader(Reader reader);

	public Reader updateReaderInfo(Reader reader);

	public boolean addReader(Reader reader);
}
