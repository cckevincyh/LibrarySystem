package com.cc.library.service;

import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;

public interface ForfeitService {

	public PageBean<ForfeitInfo> findForfeitInfoByPage(int pageCode, int pageSize);

	public PageBean<ForfeitInfo> queryForfeitInfo(String iSBN, String paperNO,
			int borrowId, int pageCode, int pageSize);

	public ForfeitInfo getForfeitInfoById(ForfeitInfo forfeitInfo);

	public int payForfeit(ForfeitInfo forfeitInfo);

}
 