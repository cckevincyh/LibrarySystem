package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;


public interface BackDao {

	public int addBack(BackInfo backInfo);

	public PageBean<BackInfo> findBackInfoByPage(int pageCode, int pageSize);

	public BackInfo getBackInfoById(BackInfo backInfo);

	public PageBean<Integer> getBorrowIdList(String iSBN, String paperNO,int pageCode, int pageSize);

	public BackInfo updateBackInfo(BackInfo backInfoById);

	public boolean deleteBackInfo(BackInfo backInfo);

}
