package com.cc.library.service;

import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;

public interface ForfeitService {

	PageBean<ForfeitInfo> findForfeitInfoByPage(int pageCode, int pageSize);

	PageBean<ForfeitInfo> queryForfeitInfo(String iSBN, String paperNO,
			int borrowId, int pageCode, int pageSize);

}
 