package com.cc.library.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cc.library.service.BorrowService;
import com.cc.library.service.impl.BorrowServiceImpl;
/**
 * 定时任务
 * @author c
 *
 */
public class CheckBorrowInfo extends QuartzJobBean{

	private BorrowService borrowService;

	public void setBorrowService(BorrowService borrowService) {
		this.borrowService = borrowService;
	}
	
	public CheckBorrowInfo(){
		 super();
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		boolean checkBorrowInfo = true;
		try{
			 checkBorrowInfo = borrowService.checkBorrowInfo();
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(!checkBorrowInfo){
			System.err.println("定时检查借阅表逾期出现了错误!!!");
		}
		
	}
	
	
	
}
