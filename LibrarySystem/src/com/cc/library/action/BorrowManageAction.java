package com.cc.library.action;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.cc.library.domain.Book;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;
import com.cc.library.service.BookService;
import com.cc.library.service.BorrowService;
import com.cc.library.service.ReaderService;
import com.opensymphony.xwork2.ActionSupport;

public class BorrowManageAction extends ActionSupport{

	private BorrowService borrowService;
	





	public void setBorrowService(BorrowService borrowService) {
		this.borrowService = borrowService;
	}
	
	








	private int pageCode;
	private int borrowId;
	private String ISBN;
	private String paperNO;
	private String pwd;
	
	
	
	
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}






	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}






	public void setPwd(String pwd) {
		this.pwd = pwd;
	}






	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}








	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	


	/**
	 * 根据页码分页查询借阅信息
	 * @return
	 */
	public String findBorrowInfoByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<BorrowInfo> pb = borrowService.findBorrowInfoByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBorrowInfoByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	
	/**
	 * 根据借阅id查询该借阅信息
	 * @return
	 */
	public String getBorrowInfoById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		BorrowInfo info = new BorrowInfo();
		info.setBorrowId(borrowId);
		BorrowInfo newInfo = borrowService.getBorrowInfoById(info);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Authorization||name.equals("authorization") || obj instanceof Set || name.equals("borrowInfos")){	
				return true;
			}else{
				return false;
			}
		   }
		});
		
		
		JSONObject jsonObject = JSONObject.fromObject(newInfo,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

	
	
	/**
	 * 借阅处理
	 * @return
	 */
	public String borrowBook(){
		//借阅的主要步骤
		
		/*
		 * 1. 得到借阅的读者
		 * 
		 * 2. 查看读者输入的密码是否匹配
		 * 		2.1 如果不匹配提示 密码错误
		 * 		2.2 如果匹配,执行第3步骤
		 * 
		 * 3. 查看该读者的借阅信息
		 * 		3.1 查看借阅信息的条数
		 * 		3.2 查看该读者的类型得出该读者的最大借阅数量
		 * 		3.3 匹配借阅的数量是否小于最大借阅量
		 * 			3.3.1 小于,则可以借阅
		 * 			3.3.2 大于,则不可以借阅,直接返回借阅数量已超过
		 * 		3.4 查看读者的罚款信息,是否有未缴纳的罚款
		 * 			3.4.1 如果没有,继续第3的操作步骤
		 * 			3.4.2 如果有,直接返回有尚未缴纳的罚金
		 * 
		 * 4. 查看借阅的书籍
		 * 		4.1 查看该书籍的在馆数量是否大于1,,如果为1则不能借阅,必须留在馆内浏览
		 * 			4.1.1 如果大于1,进入第4操作步骤
		 * 			4.1.2 如果小于等于1,提示该图书为馆内最后一本,无法借阅
		 * 
		 * 5. 处理借阅信息
		 * 		5.1 得到该读者的最大借阅天数,和每日罚金
		 * 			5.1.1 获得当前时间
		 * 			5.1.2 根据最大借阅天数,计算出截止日期
		 * 			5.1.3 为借阅信息设置每日的罚金金额
		 * 		5.2 获得该读者的信息,为借阅信息设置读者信息
		 * 		5.3 获得图书信息,为借阅信息设置图书信息
		 * 		5.4 获得管理员信息,为借阅信息设置操作的管理员信息
		 * 
		 * 6. 存储借阅信息
		 * 
		 */
		BorrowInfo borrowInfo = new BorrowInfo();
		Reader reader = new Reader();
		reader.setPaperNO(paperNO);
		reader.setPwd(pwd);
		borrowInfo.setReader(reader);
		Admin admin = (Admin) ServletActionContext.getContext().getSession().get("admin");
		borrowInfo.setAdmin(admin);
		Book book = new Book();
		book.setISBN(ISBN);
		borrowInfo.setBook(book);
		int addBorrow = borrowService.addBorrow(borrowInfo);
		try {
			ServletActionContext.getResponse().getWriter().print(addBorrow);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	
	
	public String renewBook(){
		//续借步骤：
		/*
		 * 1. 得到借阅的记录
		 * 
		 * 2. 得到借阅的记录的状态
		 * 		2.1 如果已经是续借状态(包括续借未归还,续借逾期未归还),则返回已经续借过了,返回-2
		 * 		2.2 如果是归还状态(包括归还,续借归还),则返回该书已还,无法续借,返回-1
		 *		2.3 如果都不是以上情况,则进行下一步。
		 * 
		 * 3. 得到借阅的读者
		 * 
		 * 4. 得到借阅的读者类型
		 * 
		 * 5. 得到可续借的天数
		 * 
		 * 6. 在当前记录的截止日期上叠加可续借天数
		 * 		6.1 如果叠加后的时候比当前时间小,则返回你已超续借期了,无法进行续借,提示读者快去还书和缴纳罚金  返回-3
		 * 		6.2如果大于当前时间进行下一步
		 * 
		 * 7. 得到当前借阅记录的状态
		 * 		7.1 如果当前记录为逾期未归还,则需要取消当前借阅记录的罚金记录,并将该记录的状态设置为续借未归还
		 * 		7.2如果为未归还状态,直接将当前状态设置为续借未归还
		 * 
		 * 8. 为当前借阅记录进行设置,设置之后重新update该记录 返回1
		 */
		BorrowInfo borrowInfo = new BorrowInfo();
		borrowInfo.setBorrowId(borrowId);
		int renewBook = borrowService.renewBook(borrowInfo);
		try {
			ServletActionContext.getResponse().getWriter().print(renewBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		
		return null;
	}
	
}
