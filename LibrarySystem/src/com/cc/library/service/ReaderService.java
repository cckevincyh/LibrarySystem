package com.cc.library.service;

import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public interface ReaderService {

	public Reader getReader(Reader reader);

	public Reader updateReaderInfo(Reader reader);

	public boolean addReader(Reader reader);

	public PageBean<Reader> findReaderByPage(int pageCode, int pageSize);

	public Reader getReaderById(Reader reader);

	public boolean deleteReader(Reader reader);

	public PageBean<Reader> queryReader(Reader reader,int pageCode, int pageSize);
}
