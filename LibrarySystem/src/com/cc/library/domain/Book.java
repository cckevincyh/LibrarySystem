package com.cc.library.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书信息类
 * @author c
 *
 */
public class Book implements Serializable{
	private Integer bookId;	//图书编号
	private BookType bookType;	//图书类型
	private String bookName;	//图书名称
	private String autho;	//作者名称
	private String press;	//出版社
	private Date putdate;	//上架日期
	private Integer num;	//总数量
	private Integer currentNum;	//在馆数量
	private Double price;	//价格
	private String description;	//简介
	private Integer state = 1;	//状态(删除或者未删除,1表示未删除,0表示删除)
	
	
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAutho() {
		return autho;
	}
	public void setAutho(String autho) {
		this.autho = autho;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public Date getPutdate() {
		return putdate;
	}
	public void setPutdate(Date putdate) {
		this.putdate = putdate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Book(Integer bookId, BookType bookType, String bookName,
			String autho, String press, Date putdate, Integer num,
			Integer currentNum, Double price, String description) {
		super();
		this.bookId = bookId;
		this.bookType = bookType;
		this.bookName = bookName;
		this.autho = autho;
		this.press = press;
		this.putdate = putdate;
		this.num = num;
		this.currentNum = currentNum;
		this.price = price;
		this.description = description;
	}
	public Book() {
		
	}

	
	
	
	
}
