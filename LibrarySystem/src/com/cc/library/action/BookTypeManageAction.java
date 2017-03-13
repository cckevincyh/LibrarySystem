package com.cc.library.action;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookTypeService;
import com.opensymphony.xwork2.ActionSupport;

public class BookTypeManageAction extends ActionSupport{

	private BookTypeService bookTypeService;

	public void setBookTypeService(BookTypeService bookTypeService) {
		this.bookTypeService = bookTypeService;
	}
	
	private int pageCode;
	
	
	
	
	
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}






	public String findBookTypeByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<BookType> pb = bookTypeService.findBookTypeByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBookTypeByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	
	}
}
