package com.cc.library.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 读者类
 * @author c
 *
 */
public class Reader implements Serializable{

	private String readerId;	//证件号码
	private String name;	//真实名称
	private String phone;	//联系方式
	private String pwd; 	//密码
	private ReaderType readerType;	//读者类型(学生或者教师)
	
	
	private Set<BorrowInfo> borrowInfos;	//该读者的借阅信息
	private Set<ForfeitInfo>forfeitInfos;	//该读者的罚金缴纳信息
	
	private Integer state;	//状态(删除或者未删除,1表示未删除,0表示删除)
	
	
	private int borrowNum;//已借数量
	private int unpaid;//	是否有未缴纳罚款(0:没有未缴纳的罚款，1:有未缴纳的罚款)
	
	
	public Integer getState() {
		return state;
	}

	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setState(Integer state) {
		this.state = state;
	}


	public Set<BorrowInfo> getBorrowInfos() {
		return borrowInfos;
	}


	public void setBorrowInfos(Set<BorrowInfo> borrowInfos) {
		this.borrowInfos = borrowInfos;
	}


	
	
	public Set<ForfeitInfo> getForfeitInfos() {
		return forfeitInfos;
	}


	public void setForfeitInfos(Set<ForfeitInfo> forfeitInfos) {
		this.forfeitInfos = forfeitInfos;
	}


	public String getReaderId() {
		return readerId;
	}


	public void setReaderId(String readerId) {
		this.readerId = readerId;
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



	public Reader() {
		
	}



	public ReaderType getReaderType() {
		return readerType;
	}



	public void setReaderType(ReaderType readerType) {
		this.readerType = readerType;
	}



	public Reader(String readerId, String name, String phone, String pwd,
			ReaderType readerType) {
		super();
		this.readerId = readerId;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
		this.readerType = readerType;
	}



	public Reader(String readerId, String name, String phone, String pwd) {
		super();
		this.readerId = readerId;
		this.name = name;
		this.phone = phone;
		this.pwd = pwd;
	}



	public int getBorrowNum() {
		return borrowNum;
	}



	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}



	public int getUnpaid() {
		return unpaid;
	}



	public void setUnpaid(int unpaid) {
		this.unpaid = unpaid;
	}



	








	
	
	
	
	
}
