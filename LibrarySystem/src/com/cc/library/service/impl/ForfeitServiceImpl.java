package com.cc.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cc.library.dao.BackDao;
import com.cc.library.dao.ForfeitDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.ForfeitService;

public class ForfeitServiceImpl implements ForfeitService{
	
	private ForfeitDao forfeitDao;
	private BackDao backDao;
	
	
	public void setBackDao(BackDao backDao) {
		this.backDao = backDao;
	}

	public void setForfeitDao(ForfeitDao forfeitDao) {
		this.forfeitDao = forfeitDao;
	}

	@Override
	public PageBean<ForfeitInfo> findForfeitInfoByPage(int pageCode,
			int pageSize) {
		// TODO Auto-generated method stub
		return forfeitDao.findForfeitInfoByPage(pageCode,pageSize);
	}

	@Override
	public PageBean<ForfeitInfo> queryForfeitInfo(String iSBN, String paperNO,
			int borrowId, int pageCode, int pageSize) {
		PageBean<ForfeitInfo> pageBean = new PageBean<ForfeitInfo>();
		pageBean.setPageCode(pageCode);
		pageBean.setPageSize(pageSize);
		PageBean<Integer> list = backDao.getBorrowIdList(iSBN,paperNO,borrowId,pageCode,pageSize);
		pageBean.setTotalRecord(list.getTotalRecord());
		List<Integer> beanList = list.getBeanList();
		if(beanList.size()==0){
			return null;
		}
		List<ForfeitInfo> forfeitInfos = new ArrayList<ForfeitInfo>();
		for(Integer i : beanList){
			ForfeitInfo forfeitInfo = new ForfeitInfo();
			forfeitInfo.setBorrowId(i);
			ForfeitInfo info = forfeitDao.getForfeitInfoById(forfeitInfo);
			forfeitInfos.add(info);
		}
		pageBean.setBeanList(forfeitInfos);
		return pageBean;
	}
	
	
	
	

}
