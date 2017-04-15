package com.cc.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cc.library.dao.BackDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BackService;

public class BackServiceImpl implements BackService{

	private BackDao backDao;

	public void setBackDao(BackDao backDao) {
		this.backDao = backDao;
	}

	@Override
	public PageBean<BackInfo> findBackInfoByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return backDao.findBackInfoByPage(pageCode,pageSize);
	}

	@Override
	public BackInfo getBackInfoById(BackInfo backInfo) {
		// TODO Auto-generated method stub
		return backDao.getBackInfoById(backInfo);
	}

	@Override
	public PageBean<BackInfo> queryBackInfo(String iSBN, String paperNO,int pageCode,int pageSize) {
		PageBean<BackInfo> pageBean = new PageBean<BackInfo>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		PageBean<Integer> list = backDao.getBorrowIdList(iSBN,paperNO,pageCode,pageSize);
		pageBean.setTotalRecord(list.getTotalRecord());
		List<Integer> beanList = list.getBeanList();
		if(beanList.size()==0){
			return null;
		}
		List<BackInfo> backInfos = new ArrayList<BackInfo>();
		for(Integer i : beanList){
			BackInfo backInfo = new BackInfo();
			backInfo.setBorrowId(i);
			BackInfo info = backDao.getBackInfoById(backInfo);
			backInfos.add(info);
		}
		pageBean.setBeanList(backInfos);
		return pageBean;
	}
	
	
}
