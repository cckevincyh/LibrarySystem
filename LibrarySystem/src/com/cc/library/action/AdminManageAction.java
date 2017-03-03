package com.cc.library.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	private String name;
	private String phone;
	private String pwd;

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	/**
	 * 得到所有的普通管理员
	 * @return
	 */
	public String getAllAdmins(){
		List<Admin> admins = adminService.getAllAdmins();
		ServletActionContext.getContext().getSession().put("admins", admins);
		return "success";
	}
	
	/**
	 * 得到指定的普通管理员
	 * @return
	 */
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
	
	/**
	 * 修改指定管理员
	 * @return
	 */
	public String updateAdmin(){
		Admin admin = new Admin();
		admin.setUsername(username);
		Admin updateAdmin = adminService.getAdmin(admin);//查出需要修改的管理员对象
		updateAdmin.setName(name);
		updateAdmin.setPhone(phone);
		updateAdmin.setPwd(pwd);
		Admin newAdmin = adminService.updateAdminInfo(updateAdmin);
		int success = 0;
		if(newAdmin!=null){
			success = 1;
			List<Admin> allAdmins = adminService.getAllAdmins();
			//将所有的管理员重新存入session
			ServletActionContext.getContext().getSession().put("admins", allAdmins);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
}
