package com.cc.library.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 借阅信息类
 * @author c
 *
 */
public class BorrowInfo implements Serializable{

	private Integer borrowId;	//借阅编号
	private Book book;	//借阅书籍
	private Reader reader;	//借阅读者
	private Date borrowDate;	//借阅日期
	private Admin admin;	//操作员
	private Date endDate;	//截止日期
	private Double penalty;	//每日罚金
	private Integer overday;	//逾期天数
	private Integer state; //状态 (归还或者未归还)

	
	
	
	
	public Double getPenalty() {
		return penalty;
	}
	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}
	public Integer getOverday() {
		return overday;
	}
	public void setOverday(Integer overday) {
		this.overday = overday;
	}

	
	
	public Integer getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public BorrowInfo() {

	}
	
	
	
	
	
	
	
	
	
	
	
}
