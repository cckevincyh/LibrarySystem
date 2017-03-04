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

	private int id;
	private String username;
	public void setUsername(String username) {
		this.username = username;
	}
	private String name;
	private String phone;
	private String pwd;

	public void setId(int id) {
		this.id = id;
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
		System.out.println(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Admin admin = new Admin();
		admin.setId(id);
		Admin newAdmin = adminService.getAdminById(admin);
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
		admin.setId(id);
		Admin updateAdmin = adminService.getAdminById(admin);//查出需要修改的管理员对象
		updateAdmin.setUsername(username);
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
	
	
	/**
	 * 添加管理员
	 * @return
	 */
	public String addAdmin(){
		Admin admin = new Admin();
		admin.setUsername(username);
		Admin admin2 = adminService.getAdminByUserName(admin);
		int success = 0;
		if(admin2!=null){
			success = -1;//已经存在该管理员
		}else{
			admin.setName(name);
			admin.setPhone(phone);
			admin.setPwd(pwd);
			boolean b = adminService.addAdmin(admin);
			if(!b){
				success = 0;
			}else{
				success = 1;
				Map<String, Object> session = ServletActionContext.getContext().getSession();
				//取出session中的管理员，添加新的
				List list = (List) session.get("admins");
				list.add(admin);
				session.put("admins", list);
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
	
	
}
