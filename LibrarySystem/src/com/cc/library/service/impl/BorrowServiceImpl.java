package com.cc.library.service.impl;

import com.cc.library.dao.BorrowDao;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BorrowService;

public class BorrowServiceImpl implements BorrowService{

	private BorrowDao borrowDao;

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	@Override
	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return borrowDao.findBorrowInfoByPage(pageCode,pageSize);
	}

	@Override
	public BorrowInfo getBorrowInfoById(BorrowInfo info) {
		// TODO Auto-generated method stub
		return borrowDao.getBorrowInfoById(info);
	}

	
	@Override
	public int addBorrow(BorrowInfo info) {
		//得到读者Id,查看数据库是否存在该读者,不存在返回对应的状态码
		
		
		//得到借阅图书Id,查看数据库是否存在图书,不存在返回对应的状态码
		

		//获得该读者的借阅的图书数量
		
		
		//匹配读者的借书数量和该读者总借书量是否相同,相同表示借书已满上限,返回对应的状态码
		
		
		//查看该读者是否存在逾期的罚金未缴纳(遍历该读者的所有罚金缴纳信息，查看是否有未缴纳的情况),有则返回对应的状态码
		
		
		
		//获得系统时间作为借阅时间
		
		
		
		//获得该读者借阅的最大天数，和当前系统时间进行叠加得到截止时间
		
		
		
		return 0;
	}
	
	
}
