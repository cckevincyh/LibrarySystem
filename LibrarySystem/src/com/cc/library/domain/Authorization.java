package com.cc.library.domain;

import java.io.Serializable;

public class Authorization implements Serializable{
	private Integer aid;	//管理员id
	private Integer sysSet;	//系统设置权限
	private Integer readerSet;	//读者设置权限
	private Integer bookSet;	//图书设置权限
	private Integer typeSet;	//图书分类设置权限
	private Integer borrowSet;	//借阅设置权限
	private Integer backSet;	//归还设置权限
	private Integer superSet;	//超级管理权限
	private Admin admin;
	
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	

	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getSysSet() {
		return sysSet;
	}
	public void setSysSet(Integer sysSet) {
		this.sysSet = sysSet;
	}
	public Integer getReaderSet() {
		return readerSet;
	}
	public void setReaderSet(Integer readerSet) {
		this.readerSet = readerSet;
	}
	public Integer getBookSet() {
		return bookSet;
	}
	public void setBookSet(Integer bookSet) {
		this.bookSet = bookSet;
	}
	public Integer getTypeSet() {
		return typeSet;
	}
	public void setTypeSet(Integer typeSet) {
		this.typeSet = typeSet;
	}
	public Integer getBorrowSet() {
		return borrowSet;
	}
	public void setBorrowSet(Integer borrowSet) {
		this.borrowSet = borrowSet;
	}
	public Integer getBackSet() {
		return backSet;
	}
	public void setBackSet(Integer backSet) {
		this.backSet = backSet;
	}
	public Integer getSuperSet() {
		return superSet;
	}
	public void setSuperSet(Integer superSet) {
		this.superSet = superSet;
	}
	public Authorization() {

	
	}

	
	
	

}