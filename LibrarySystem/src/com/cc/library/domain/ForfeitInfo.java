package com.cc.library.domain;

import java.io.Serializable;

/**
 * 罚金信息类
 * @author c
 *
 */
public class ForfeitInfo implements Serializable{
	
	private int forfeitId;	//罚金序号
	private BorrowInfo info;	//借阅信息
	private double forfeit;	//罚金金额
	private Admin admin;	//操作管理员
	private Reader reader;	//逾期读者
	private int isPay;	//是否已经支付罚金
	public int getForfeitId() {
		return forfeitId;
	}
	public void setForfeitId(int forfeitId) {
		this.forfeitId = forfeitId;
	}
	public BorrowInfo getInfo() {
		return info;
	}
	public void setInfo(BorrowInfo info) {
		this.info = info;
	}
	public double getForfeit() {
		return forfeit;
	}
	public void setForfeit(double forfeit) {
		this.forfeit = forfeit;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public int getIsPay() {
		return isPay;
	}
	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}
	public ForfeitInfo(int forfeitId, BorrowInfo info, double forfeit,
			Admin admin, Reader reader, int isPay) {
		super();
		this.forfeitId = forfeitId;
		this.info = info;
		this.forfeit = forfeit;
		this.admin = admin;
		this.reader = reader;
		this.isPay = isPay;
	}
	public ForfeitInfo() {
		
	}
	
	
}
