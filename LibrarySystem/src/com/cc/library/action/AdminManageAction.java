package com.cc.library.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminManageAction extends ActionSupport{

	private AdminService adminService;

	private String username;
	public void setUsername(String username) {
		this.username = username;
	}


	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
	public String getAllAdmins(){
		List<Admin> admins = adminService.getAllAdmins();
		ServletActionContext.getContext().getSession().put("admins", admins);
		return "success";
	}
	
	
	public String getAdmin(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Admin admin = new Admin();
		admin.setUsername(username);
		Admin newAdmin = adminService.getAdmin(admin);
		JSONObject jsonObject = JSONObject.fromObject(newAdmin);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
}
