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

	public ForfeitInfo() {
		
	}
	
	
}
