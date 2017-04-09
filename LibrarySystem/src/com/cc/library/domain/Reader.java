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

	private String readerId;	//自动编号
	private String name;	//真实名称
	private String pwd;	//密码
	private String phone;	//联系方式
	private ReaderType readerType;	//读者类型(学生或者教师)
    private String email;	//邮箱
    private Admin admin;	//操作管理员
    private String paperNO;	//证件号码
	private Date createTime;	//创建时间
	private Set<BorrowInfo> borrowInfos;	//该读者的借阅信息
	
	private Integer state;	//删除状态

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ReaderType getReaderType() {
		return readerType;
	}

	public void setReaderType(ReaderType readerType) {
		this.readerType = readerType;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getPaperNO() {
		return paperNO;
	}

	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<BorrowInfo> getBorrowInfos() {
		return borrowInfos;
	}

	public void setBorrowInfos(Set<BorrowInfo> borrowInfos) {
		this.borrowInfos = borrowInfos;
	}



	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Reader() {

	
	
	}
	
	



	
	
	
	
	
}
