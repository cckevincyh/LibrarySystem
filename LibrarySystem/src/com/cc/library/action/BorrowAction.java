package com.cc.library.action;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Authorization;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.service.BackService;
import com.cc.library.service.BorrowService;
import com.opensymphony.xwork2.ActionSupport;

public class BorrowAction extends ActionSupport{
	
	private BackService backService;

	public void setBackService(BackService backService) {
		this.backService = backService;
	}


	private int pageCode;
	private String ISBN;
	private int borrowId;
	
	
	
	
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}





	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}


	public String findMyBorrowInfoByPage(){
		Reader reader = (Reader) ServletActionContext.getContext().getSession().get("reader");
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<BackInfo> pb = null;
		pb = backService.findMyBorrowInfoByPage(reader,pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findMyBorrowInfoByPage.action?");
		}
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	
	public String queryBorrowSearchInfo(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<BackInfo> pb = null;
		Reader reader = (Reader) ServletActionContext.getContext().getSession().get("reader");
		if("".equals(ISBN.trim()) && borrowId==0){
			backService.findMyBorrowInfoByPage(reader,pageCode,pageSize);
		}else{
			pb = backService.queryBackInfo(ISBN,reader.getPaperNO(),borrowId,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryBorrowSearchInfo.action?ISBN="+ISBN+"&borrowId="+borrowId+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
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

}
