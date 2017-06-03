package com.cc.library.domain;

import java.io.Serializable;

/**
 * 管理员类
 * @author c
 *
 */
public class Admin implements Serializable{
	private Integer aid;	//编号
	private String username;	//用户名
	private String name;	//管理员姓名
	private String pwd;	//密码
	private String phone;	//联系方式
	private Authorization authorization;//权限
	private Integer state;	//删除状态
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Authorization getAuthorization() {
		return authorization;
	}
	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Admin() {
		
		
	}
	


	
	
	
	
}
