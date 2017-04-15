package com.cc.library.service;

import com.cc.library.domain.BackInfo;
import com.cc.library.domain.PageBean;

public interface BackService {

	public PageBean<BackInfo> findBackInfoByPage(int pageCode, int pageSize);

	public BackInfo getBackInfoById(BackInfo backInfo);

	public PageBean<BackInfo> queryBackInfo(String iSBN, String paperNO,int pageCode,int pageSize);

}
