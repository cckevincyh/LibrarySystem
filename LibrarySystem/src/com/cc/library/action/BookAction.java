package com.cc.library.action;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;


import com.cc.library.domain.Authorization;
import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;
import com.cc.library.service.BookTypeService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BookAction extends ActionSupport{

	
	private BookService bookService;
	private BookTypeService bookTypeService;
	
	

	public void setBookTypeService(BookTypeService bookTypeService) {
		this.bookTypeService = bookTypeService;
	}


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
	private int bookId;//图书编号
	private String ISBN;
	
	
	



	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


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




	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	/**
	 * 得到图书类型的集合
	 * ajax请求该方法
	 * 返回图书类型集合的json对象
	 * @return
	 */
	public String getAllBookTypes(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		List<BookType> allBookTypes = bookTypeService.getAllBookTypes();
		
		
		
		String json = JSONArray.fromObject(allBookTypes).toString();//List------->JSONArray
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据页码查询图书
	 * @return
	 */
	public String findBookByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		
		PageBean<Book> pb = bookService.findBookByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findBookByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	

	
	/**
	 * 得到指定图书编号的图书信息
	 * ajax请求该方法
	 * 返回该图书信息的json对象
	 * @return
	 */
	public String getBook(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Book book = new Book();
		book.setBookId(bookId);
		Book newBook = bookService.getBookById(book);//得到图书
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Authorization||name.equals("authorization")){	
				return true;
			}else{
				return false;
			}
		   }
		});
		
		
		JSONObject jsonObject = JSONObject.fromObject(newBook,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

	
	
	

	
	
	
	/**
	 * 多条件查询图书
	 * @return
	 */
	public String queryBook(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Book> pb = null;

		if("".equals(ISBN.trim()) && "".equals(bookName.trim()) && bookTypeId==-1 && "".equals(press.trim()) && "".equals(autho.trim())){
			pb = bookService.findBookByPage(pageCode,pageSize);
		}else{
			BookType bookType = new BookType();
			bookType.setTypeId(bookTypeId);
			Book book = new Book(ISBN, bookType, bookName, autho, press);

			pb = bookService.queryBook(book,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryBook.action?ISBN="+ISBN+"&bookName="+bookName+"&bookTypeId="+bookTypeId+"&press="+press+"&autho="+autho+"&");
		}
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	

	
	

}
