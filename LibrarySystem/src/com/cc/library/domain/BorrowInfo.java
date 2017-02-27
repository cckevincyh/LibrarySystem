package com.cc.library.domain;

import java.io.Serializable;
import java.util.Date;

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
	private Date backDate;	//归还日期
	private Integer overdueDay ;	//逾期天数
	private Date endDate;	//截止日期
	
	
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
	public Date getBackDate() {
		return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	public Integer getOverdueDay() {
		return overdueDay;
	}
	public void setOverdueDay(Integer overdueDay) {
		this.overdueDay = overdueDay;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BorrowInfo(Integer borrowId, Book book, Reader reader,
			Date borrowDate, Date backDate, Integer overdueDay, Date endDate) {
		super();
		this.borrowId = borrowId;
		this.book = book;
		this.reader = reader;
		this.borrowDate = borrowDate;
		this.backDate = backDate;
		this.overdueDay = overdueDay;
		this.endDate = endDate;
	}
	public BorrowInfo() {
		
	}
	
	
	
	
	
	
}
