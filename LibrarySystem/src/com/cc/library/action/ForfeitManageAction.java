package com.cc.library.action;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.BackInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.ForfeitService;
import com.opensymphony.xwork2.ActionSupport;

public class ForfeitManageAction extends ActionSupport{

	private ForfeitService forfeitService;

	public void setForfeitService(ForfeitService forfeitService) {
		this.forfeitService = forfeitService;
	}
	
	private int pageCode;
	
	private int borrowId;
	private String ISBN;
	private String paperNO;
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	
	
	
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}




	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}




	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}




	public String queryForfeitInfo(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<ForfeitInfo> pb = null;
		if("".equals(ISBN.trim()) && "".equals(paperNO.trim()) && borrowId==0){
			pb = forfeitService.findForfeitInfoByPage(pageCode,pageSize);
		}else{
			pb = forfeitService.queryForfeitInfo(ISBN,paperNO,borrowId,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryForfeitInfo.action?ISBN="+ISBN+"&paperNO="+paperNO+"&borrowId="+borrowId+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}


	public String findForfeitInfoByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<ForfeitInfo> pb = forfeitService.findForfeitInfoByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findForfeitInfoByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
}
