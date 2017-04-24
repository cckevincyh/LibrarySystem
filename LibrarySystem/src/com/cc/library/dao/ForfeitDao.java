package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;

public interface ForfeitDao {

	public List<ForfeitInfo> getForfeitByReader(Reader reader);

	public boolean addForfeitInfo(ForfeitInfo forfeitInfo);

	public PageBean<ForfeitInfo> findForfeitInfoByPage(int pageCode,
			int pageSize);



	public ForfeitInfo getForfeitInfoById(ForfeitInfo forfeitInfo);

}
