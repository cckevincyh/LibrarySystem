package com.cc.library.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.service.AdminService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminInfoAction extends ActionSupport{

	private AdminService  adminService;

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	private String username;
	private String name;
	private String phone;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
	
	
	
	




	public void setUsername(String username) {
		this.username = username;
	}




	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}




	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}




	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}




	public void setName(String name) {
		this.name = name;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	/**
	 * 管理员个人资料
	 * @return
	 */
	public String adminInfo(){
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		Admin admin = (Admin) session.get("admin");
		admin.setUsername(username);
		admin.setName(name);
		admin.setPhone(phone);
		Admin newAdmin = adminService.updateAdminInfo(admin);
		int success = 0;
		if(newAdmin!=null){
			success = 1;
			//重新存入session
			session.put("admin", newAdmin);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 管理员密码修改
	 * @return
	 */
	public String adminPwd(){
		Admin admin = (Admin) ServletActionContext.getContext().getSession().get("admin");
		int state = -1;//原密码错误
		//取出原密码进行比对
		if(admin.getPwd().equals(oldPwd)){
			if(newPwd.equals(confirmPwd)){
				state = 1;//修改成功
				admin.setPwd(newPwd);
				admin = adminService.updateAdminInfo(admin);
				//重新存入session
				ServletActionContext.getContext().getSession().put("admin", admin);
			}else{
				state = 0;//确认密码不一致
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
}
