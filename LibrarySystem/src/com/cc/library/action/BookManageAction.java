package com.cc.library.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

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


	public String getAllBookTypes(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		List<BookType> allBookTypes = bookService.getAllBookTypes();
		System.out.println(allBookTypes);
		String json = JSONArray.fromObject(allBookTypes).toString();//List------->JSONArray
		System.out.println(json);
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
}
