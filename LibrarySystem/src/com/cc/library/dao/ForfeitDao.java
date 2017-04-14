package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.Reader;

public interface ForfeitDao {

	public List<ForfeitInfo> getForfeitByReader(Reader reader);

}
