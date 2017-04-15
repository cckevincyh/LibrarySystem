package com.cc.library.dao;

import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;


public interface BackDao {

	public int addBack(BackInfo backInfo);

	public PageBean<BorrowInfo> findBackInfoByPage(int pageCode, int pageSize);

	public BackInfo getBackInfoById(BackInfo backInfo);

}
