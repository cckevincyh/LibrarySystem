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
import com.cc.library.domain.Reader;
import com.cc.library.service.ForfeitService;
import com.opensymphony.xwork2.ActionSupport;

public class ForfeitAction extends ActionSupport{

	private ForfeitService forfeitService;

	public void setForfeitService(ForfeitService forfeitService) {
		this.forfeitService = forfeitService;
	}
	
	private int pageCode;
	
	private int borrowId;
	private String ISBN;

	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	
	
	
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}




	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}







	public String queryForfeitInfo(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<ForfeitInfo> pb = null;
		Reader reader = (Reader) ServletActionContext.getContext().getSession().get("reader");
		if("".equals(ISBN.trim()) && borrowId==0){
			pb = forfeitService.findMyForfeitInfoByPage(reader,pageCode,pageSize);
		}else{
			pb = forfeitService.queryForfeitInfo(ISBN,reader.getPaperNO(),borrowId,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryForfeitInfo.action?ISBN="+ISBN+"&borrowId="+borrowId+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}


	public String findMyForfeitInfoByPage(){
		Reader reader = (Reader) ServletActionContext.getContext().getSession().get("reader");
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<ForfeitInfo> pb = null;
		pb = forfeitService.findMyForfeitInfoByPage(reader,pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findMyForfeitInfoByPage.action?");
		}
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
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
	

	
}
