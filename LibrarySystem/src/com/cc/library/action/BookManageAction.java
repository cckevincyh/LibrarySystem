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
	private int bookId;//图书编号
	



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
	
	


	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	
	
	
	public String getBook(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Book book = new Book();
		book.setBookId(bookId);
		Book newBook = bookService.getBookById(book);
		JSONObject jsonObject = JSONObject.fromObject(newBook);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

	
	
	
	
	public String updateBook(){
		Book book = new Book();
		book.setBookId(bookId);
		System.out.println(bookId);
		Book updateBook = bookService.getBookById(book);
		System.out.println(updateBook);
		updateBook.setBookName(bookName);
		updateBook.setAutho(autho);
		BookType type = new BookType();
		type.setTypeId(bookTypeId);
		BookType bookType = bookService.getBookType(type);//得到图书类型
		updateBook.setBookType(bookType);//设置图书类型
		updateBook.setDescription(description);
		updateBook.setPress(press);
		updateBook.setPrice(price);
		System.out.println("updateBook"+updateBook);
		Book newBook = bookService.updateBookInfo(updateBook);
		System.out.println(newBook);
		int success = 0;
		if(newBook!=null){
			success = 1;
			//由于是转发并且js页面刷新,所以无需重查
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
