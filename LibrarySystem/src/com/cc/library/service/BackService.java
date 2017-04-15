package com.cc.library.service;

import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public interface BackService {

	public PageBean<BorrowInfo> findBackInfoByPage(int pageCode, int pageSize);

	public BackInfo getBackInfoById(BackInfo backInfo);

}
