package com.cc.library.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.service.AdminService;
import com.cc.library.service.ReaderService;
import com.opensymphony.xwork2.ActionSupport;

public class ReaderManageAction extends ActionSupport{
	
	private ReaderService readerService;
	
	
	
	
	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}






	private String readerId;
	private String name;
	private String phone;
	private String pwd;
	private Integer readerType;
	private Integer maxNum;
	private int pageCode;
	
	
	
	
	
	
	
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}






	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}






	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}






	public void setName(String name) {
		this.name = name;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}






	public void setPwd(String pwd) {
		this.pwd = pwd;
	}






	public void setReaderType(Integer readerType) {
		this.readerType = readerType;
	}






	/**
	 *添加读者
	 * @return
	 */
	public String addReader(){
		Reader reader = new Reader(readerId, name, phone, pwd, readerType, maxNum);
		Reader oldReader = readerService.getReader(reader);//检查是否已经存在该id
		int success = 0;
		if(oldReader!=null){
			success = -1;//已存在该id
		}else{
			boolean b = readerService.addReader(reader);
			if(b){
				success = 1;
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
		
	}
	
	
	
	public String findReaderByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Reader> pb = readerService.findReaderByPage(pageCode,pageSize);
		pb.setUrl("findReaderByPage.action?");
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}

}
