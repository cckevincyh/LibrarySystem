package com.cc.library.action;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BorrowService;
import com.opensymphony.xwork2.ActionSupport;

public class BorrowManageAction extends ActionSupport{

	private BorrowService borrowService;

	public void setBorrowService(BorrowService borrowService) {
		this.borrowService = borrowService;
	}
	
	
	private int pageCode;
	
	
	
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}




	public String findBorrowInfoByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<BorrowInfo> pb = borrowService.findBorrowInfoByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBorrowInfoByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
}
