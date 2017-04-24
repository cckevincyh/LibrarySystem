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
	
	public String  getForfeitInfoById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		ForfeitInfo forfeitInfo = new ForfeitInfo();
		forfeitInfo.setBorrowId(borrowId);
		ForfeitInfo  newForfeitInfo = forfeitService.getForfeitInfoById(forfeitInfo);
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
		
		
		JSONObject jsonObject = JSONObject.fromObject(newForfeitInfo,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	public String payForfeit(){
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
		ForfeitInfo forfeitInfo = new ForfeitInfo();
		forfeitInfo.setBorrowId(borrowId);
		Admin admin = (Admin) ServletActionContext.getContext().getSession().get("admin");
		forfeitInfo.setAdmin(admin);
		int pay = forfeitService.payForfeit(forfeitInfo);
		try {
			ServletActionContext.getResponse().getWriter().print(pay);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
}
