package com.cc.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cc.library.dao.BackDao;
import com.cc.library.dao.BorrowDao;
import com.cc.library.dao.ForfeitDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.service.ForfeitService;

public class ForfeitServiceImpl implements ForfeitService{
	
	private ForfeitDao forfeitDao;
	private BackDao backDao;
	private BorrowDao borrowDao;
	
	
	
	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

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

	@Override
	public ForfeitInfo getForfeitInfoById(ForfeitInfo forfeitInfo) {
		// TODO Auto-generated method stub
		return forfeitDao.getForfeitInfoById(forfeitInfo);
	}

	@Override
	public int payForfeit(ForfeitInfo forfeitInfo) {
		//支付罚金步骤
		/*
		 * 1. 得到借阅记录
		 * 
		 * 2. 查看当前的借阅状态
		 * 		2.1 如果当前状态为未归还(逾期未归还,借阅逾期未归还),则提示读者先去还书再来缴纳罚金,返回-1
		 * 		2.2 如果当前状态为归还,则继续下一步
		 * 
		 * 3. 获得当前的管理员
		 * 
		 * 4. 为当前罚金记录进行设置为已支付并设置管理员
		 * 
		 * 5. 修改罚金记录
		 */
		//得到借阅记录
		BorrowInfo info = new BorrowInfo();
		info.setBorrowId(forfeitInfo.getBorrowId());
		BorrowInfo borrowInfoById = borrowDao.getBorrowInfoById(info);
		//查看当前的借阅状态
		int state = borrowInfoById.getState();
		if(state==1 || state==4){
			//如果当前状态为未归还(逾期未归还,借阅逾期未归还),则提示读者先去还书再来缴纳罚金,返回-1
			return -1;
		}
		//得到当前罚金
		ForfeitInfo forfeitInfoById = forfeitDao.getForfeitInfoById(forfeitInfo);
		//如果当前罚金状态为已支付
		if(forfeitInfoById.getIsPay()==1){
			//提示已经缴纳罚金了
			return -2;
		}
		//为当前罚金记录进行设置为已支付并设置管理员
		forfeitInfoById.setAdmin(forfeitInfo.getAdmin());
		forfeitInfoById.setIsPay(1);	
		//修改罚金记录
		ForfeitInfo updateForfeitInfo = forfeitDao.updateForfeitInfo(forfeitInfoById);
		if(updateForfeitInfo!=null){
			return 1;
		}
		return 0;
	}

	@Override
	public PageBean<ForfeitInfo> findMyForfeitInfoByPage(Reader reader,
			int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		String iSBN = "";
		int borrowId = 0;
		String paperNO = reader.getPaperNO();
		return queryForfeitInfo(iSBN, paperNO, borrowId, pageCode, pageSize);
	}
	
	
	
	

}
