package com.cc.library.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.PageBean;
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
	
	
	private int pageCode;//当前页数

	
	private String adminUserName;	//查询管理员用户名
	private String adminName;//查询管理员姓名
	
	
	
	
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

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
			PageBean<Admin> pb = adminService.findAdminByPage(1, 5);
			ServletActionContext.getRequest().setAttribute("pb",pb);
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
				PageBean<Admin> pb = adminService.findAdminByPage(1, 5);
				ServletActionContext.getRequest().setAttribute("pb",pb);
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
	
	
	/**
	 * 根据页码查询管理员
	 * @return
	 */
	public String findAdminByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<Admin> pb = adminService.findAdminByPage(pageCode,pageSize);
		pb.setUrl("findAdminByPage.action?");
		//存入session域中
		//ServletActionContext.getContext().getSession().put("pb", pb);
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public String queryAdmin(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Admin> pb = null;
		if("".equals(adminUserName.trim()) && "".equals(adminName.trim())){
			pb = adminService.findAdminByPage(pageCode,pageSize);
		}else{
			Admin admin = new Admin();
			admin.setUsername(adminUserName);
			admin.setName(adminName);
			pb = adminService.queryAdmin(admin,pageCode,pageSize);
			
		}
		if(pb!=null){
			pb.setUrl("queryAdmin.action?adminUserName="+adminUserName+"&adminName="+adminName+"&");
		}
		//存入session域中
		//ServletActionContext.getContext().getSession().put("pb", pb);
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
}
