package com.cc.library.domain;

import java.io.Serializable;

/**
 * 罚金信息类
 * @author c
 *
 */
public class ForfeitInfo implements Serializable{
	
	private Integer borrowId;	//借阅编号
	private BorrowInfo borrowInfo;
	private Admin admin;	//操作员
	private Double forfeit;	//罚金金额
	private int isPay;	//是否已经支付罚金
	
	
	
	




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




	public Double getForfeit() {
		return forfeit;
	}




	public void setForfeit(Double forfeit) {
		this.forfeit = forfeit;
	}




	public int getIsPay() {
		return isPay;
	}




	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}




	public ForfeitInfo() {
		
	}




	@Override
	public String toString() {
		return "ForfeitInfo [borrowId=" + borrowId + ", borrowInfo="
				+ borrowInfo + ", admin=" + admin + ", forfeit=" + forfeit
				+ ", isPay=" + isPay + "]";
	}
	
	
	
	
}
