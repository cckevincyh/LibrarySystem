package com.cc.library.action;

import com.cc.library.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;

public class AdminInfoAction extends ActionSupport{

	private AdminService  adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
	
	
	public String adminInfo(){
		System.out.println("1123");
		return null;
	}
}
