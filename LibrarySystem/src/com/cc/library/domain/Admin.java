package com.cc.library.domain;

import java.io.Serializable;

/**
 * 管理员类
 * @author c
 *
 */
public class Admin implements Serializable{
	private Integer id;	//编号
	private String username;	//用户名
	private String name;	//管理员姓名
	private String phone;	//联系方式
	private Integer adminType;	//管理员类型(超级管理员，普通管理员，0超级管理员，1普通管理员)
	private String pwd;	//密码
	
	private Integer state;	//状态(删除或者未删除,1表示未删除,0表示删除)
	
	public String getUsername() {
		return username;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public Admin() {
	
	}
	public Integer getAdminType() {
		return adminType;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}
	public Admin(String username, String name, String phone, Integer adminType,
			String pwd, Integer state) {
		super();
		this.username = username;
		this.name = name;
		this.phone = phone;
		this.adminType = adminType;
		this.pwd = pwd;
		this.state = state;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", name=" + name
				+ ", phone=" + phone + ", adminType=" + adminType + ", pwd="
				+ pwd + ", state=" + state + "]";
	}
	
	
	
	
	
	
}
