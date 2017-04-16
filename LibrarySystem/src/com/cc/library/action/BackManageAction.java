package com.cc.library.action;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.cc.library.domain.BackInfo;
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
	
	private int borrowId;
	private String ISBN;
	private String paperNO;
	
	
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}



	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}



	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}



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
		
		PageBean<BackInfo> pb = backService.findBackInfoByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBackInfoByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	public String  getBackInfoById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		BackInfo backInfo = new BackInfo();
		backInfo.setBorrowId(borrowId);
		BackInfo newBackInfo = backService.getBackInfoById(backInfo);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Authorization||name.equals("authorization") || obj instanceof Set || name.equals("borrowInfos")){	
				return true;
			}else{
				return false;
			}
		   }
		});
		
		
		JSONObject jsonObject = JSONObject.fromObject(newBackInfo,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	public String queryBackInfo(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<BackInfo> pb = null;
		if("".equals(ISBN.trim()) && "".equals(paperNO.trim())){
			pb = backService.findBackInfoByPage(pageCode,pageSize);
		}else{
			pb = backService.queryBackInfo(ISBN,paperNO,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryBackInfo.action?ISBN="+ISBN+"&paperNO="+paperNO+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	public String backBook(){
		//还书的步骤
		/*
		 * 1. 获得操作的借阅编号
		 * 2. 获得当前的管理员
		 * 3. 获得借阅的书籍
		 * 		3.1 书籍的在馆数量增加
		 * 4. 获取当前时间
		 * 5. 设置操作管理员
		 * 6. 设置归还时间
		 * 7. 设置归还的状态
		 * 8. 设置借阅的状态 
		 */
		Admin admin = (Admin) ServletActionContext.getContext().getSession().get("admin");
		BackInfo backInfo = new BackInfo();
		backInfo.setBorrowId(borrowId);
		backInfo.setAdmin(admin);
		BorrowInfo borrowInfo = new BorrowInfo();
		borrowInfo.setBorrowId(borrowId);
		backInfo.setBorrowInfo(borrowInfo);
		
		int success = backService.addBackInfo(backInfo);
		 HttpServletResponse response = ServletActionContext.getResponse();
		 try {	
			response.getWriter().print(success);		
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	
	
}

