package com.cc.library.domain;

import java.util.Date;

public class BackInfo {
	private Integer borrowId;	//借阅编号
	private BorrowInfo borrowInfo;
	private Admin admin;	//操作员
	private Date backDate;	//归还时间
	
	
	

	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public BorrowInfo getBorrowInfo() {
		return borrowInfo;
	}
	public void setBorrowInfo(BorrowInfo borrowInfo) {
		this.borrowInfo = borrowInfo;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Date getBackDate() {
		return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	
	public BackInfo() {

	
	}

	
	
	
}
