package com.cc.library.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;
import com.cc.library.service.BookTypeService;
import com.opensymphony.xwork2.ActionSupport;

public class BookTypeManageAction extends ActionSupport{

	private BookTypeService bookTypeService;

	public void setBookTypeService(BookTypeService bookTypeService) {
		this.bookTypeService = bookTypeService;
	}
	
	private int pageCode;
	private String typeName;
	private Integer id;
	
	
	
	
	
	
	
	
	public void setId(Integer id) {
		this.id = id;
	}






	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}






	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}






	public String findBookTypeByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<BookType> pb = bookTypeService.findBookTypeByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBookTypeByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	
	}
	
	
	
	
	public String addBookType(){
		BookType bookType = new BookType();
		bookType.setTypeName(typeName);
		BookType bookType2 = bookTypeService.getBookTypeByName(bookType);
		int success = 0;
		if(bookType2!=null){
			success = -1;//已经存在该图书分类
		}else{
			boolean b = bookTypeService.addBookType(bookType);
			if(!b){
				success = 0;
			}else{
				success = 1;
				//由于是转发并且js页面刷新,所以无需重查
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(success);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	public String getBookType(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		BookType bookType = new BookType();
		bookType.setTypeId(id);
		BookType newType = bookTypeService.getBookTypeById(bookType);
		
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Set||name.equals("books")){//过滤掉集合
				return true;
			}else{
				return false;
			}
		   }
		});
		
		
		JSONObject jsonObject = JSONObject.fromObject(newType,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	
	public String updateBookType(){
		BookType bookType = new BookType();
		bookType.setTypeId(id);
		BookType updateBookType = bookTypeService.getBookTypeById(bookType);
		updateBookType.setTypeName(typeName);
		BookType newBookType = bookTypeService.updateBookTypeInfo(updateBookType);
		int success = 0;
		if(newBookType!=null){
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
	
	
	public String deleteBookType(){
		BookType bookType = new BookType();
		bookType.setTypeId(id);
		boolean deleteType = bookTypeService.deleteBookType(bookType);
		int success = 0;
		if(deleteType){
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
	
	
	public String queryBookType(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<BookType> pb = null;
		if("".equals(typeName.trim())){
			pb = bookTypeService.findBookTypeByPage(pageCode, pageSize);
		}else{
			BookType bookType = new BookType();
			bookType.setTypeName(typeName);
			pb = bookTypeService.queryBookType(bookType,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryBookType.action?typeName="+typeName+"&");
		}

		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
}
