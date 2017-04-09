package com.cc.library.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.cc.library.domain.PageBean;
import com.cc.library.service.AdminService;
import com.cc.library.service.AuthorizationService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AdminManageAction extends ActionSupport{

	private AdminService adminService;
	private AuthorizationService authorizationService;
	
	

	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
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
	 * Ajax请求该方法
	 * 向浏览器返回该管理员的json对象
	 * @return
	 */
	public String getAdmin(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Admin admin = new Admin();
		admin.setAid(id);
		Admin newAdmin = adminService.getAdminById(admin);
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(name.equals("admin")){//过滤掉Authorization中的admin
				return true;
			}else{
				return false;
			}
		   }
		});
		
		JSONObject jsonObject = JSONObject.fromObject(newAdmin,jsonConfig);
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
		admin.setAid(id);
		Admin updateAdmin = adminService.getAdminById(admin);//查出需要修改的管理员对象
		updateAdmin.setUsername(username);
		updateAdmin.setName(name);
		updateAdmin.setPhone(phone);
		updateAdmin.setPwd(pwd);
		Admin newAdmin = adminService.updateAdminInfo(updateAdmin);//修改该管理员
		int success = 0;
		if(newAdmin!=null){
			success = 1;
			//由于是转发并且js页面刷新,所以无需重查
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
		Admin admin2 = adminService.getAdminByUserName(admin);//按照姓名查找管理员，查看用户名是否已经存在
		int success = 0;
		if(admin2!=null){
			success = -1;//已经存在该管理员
		}else{
			admin.setName(name);
			admin.setPhone(phone);
			admin.setPwd(pwd);
			Authorization authorization = new Authorization();
			authorization.setAdmin(admin);
			admin.setAuthorization(authorization);//设置权限
			boolean b = adminService.addAdmin(admin);//添加管理员,返回是否添加成功
			if(b){
				success = 1;
			}else{
				success = 0;
			
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);//向浏览器响应是否成功的状态码
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
		if(pb!=null){
			pb.setUrl("findAdminByPage.action?");
		}
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

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	/**
	 * 删除指定管理员
	 * @return
	 */
	public String deleteAdmin(){
		Admin admin = new Admin();
		admin.setAid(id);
		boolean deleteAdmin = adminService.deleteAdmin(admin);
		int success = 0;
		if(deleteAdmin){
			success = 1;
			//由于是转发并且js页面刷新,所以无需重查
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
