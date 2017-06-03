package com.cc.library.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.cc.library.service.AuthorizationService;
import com.opensymphony.xwork2.ActionSupport;
//授权设置
public class AuthorizationAction extends ActionSupport{

	private AuthorizationService authorizationService;

	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	private int id;
	private String power;
	
	
	
	
	public void setPower(String power) {
		this.power = power;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAuthorization(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Authorization authorization = new Authorization();
		authorization.setAid(id);
		Authorization newAuthorization = authorizationService.getAuthorizationByaid(authorization);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Admin || name.equals("admin")){//过滤掉Authorization中的admin
				return true;
			}else{
				return false;
			}
		   }
		});
		
		JSONObject jsonObject = JSONObject.fromObject(newAuthorization,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	public String addAuthorization(){
		Authorization authorization = new Authorization();
		authorization.setAid(id);
		String[] str = power.split(",");
		for(String s : str){
			if(s.equals("typeSet")){
				authorization.setTypeSet(1);
			}
			if(s.equals("bookSet")){
				authorization.setBookSet(1);			
			}
			if(s.equals("readerSet")){
				authorization.setReaderSet(1);
			}
			if(s.equals("borrowSet")){
				authorization.setBookSet(1);
			}
			if(s.equals("backSet")){
				authorization.setBackSet(1);
			}
			if(s.equals("forfeitSet")){
				authorization.setForfeitSet(1);
			}
			if(s.equals("sysSet")){
				authorization.setSysSet(1);
			}
		}
//		Admin admin = new Admin();
//		admin.setAid(id);
//		authorization.setAdmin(admin);
		Authorization newAuthorization = authorizationService.updateAuthorization(authorization);//添加权限
		int success = 0;
		if(newAuthorization!=null){
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
