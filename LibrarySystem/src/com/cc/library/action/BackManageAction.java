package com.cc.library.action;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BackService;
import com.opensymphony.xwork2.ActionSupport;

public class BackManageAction extends ActionSupport{

	
	private BackService backService;

	public void setBackService(BackService backService) {
		this.backService = backService;
	}
	
	private int pageCode;
	
	
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}



	public String findBackInfoByPage(){
		
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<BorrowInfo> pb = backService.findBackInfoByPage(pageCode,pageSize);
		System.out.println(pb);
		if(pb!=null){
			pb.setUrl("findBackInfoByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
}
