package com.cc.library.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BookManageAction extends ActionSupport{

	
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	private int pageCode;
	
	
	
	
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	
	private Integer bookTypeId;	//图书类型
	private String bookName;	//图书名称
	private String autho;	//作者名称
	private String press;	//出版社
	private Integer num;	//总数量
	private Double price;	//价格
	private String description;	//简介
	
	



	public void setBookTypeId(Integer bookTypeID) {
		this.bookTypeId = bookTypeID;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public void setAutho(String autho) {
		this.autho = autho;
	}


	public void setPress(String press) {
		this.press = press;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAllBookTypes(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		List<BookType> allBookTypes = bookService.getAllBookTypes();
		String json = JSONArray.fromObject(allBookTypes).toString();//List------->JSONArray
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	public String findBookByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<BookType> pb = bookService.findBookByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBookByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	public String addBook(){
		BookType bookType = new BookType();
		bookType.setTypeId(bookTypeId);
		BookType newBookType = bookService.getBookType(bookType);
		System.out.println(newBookType);
		Date putdate = new Date(System.currentTimeMillis());
		Book book = new Book(newBookType, bookName, autho, press, putdate, num, num, price, description);
		boolean b = bookService.addBook(book);
		int success = 0;
		if(b){
			success = 1;
		}else{
			success = 0;
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

}
