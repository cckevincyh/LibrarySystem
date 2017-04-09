package com.cc.library.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;
import com.cc.library.service.BookTypeService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BookManageAction extends ActionSupport{

	
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
		
		
		String json = JSONArray.fromObject(allBookTypes,jsonConfig).toString();//List------->JSONArray,配置过滤
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
	
//	/**
//	 * 添加图书
//	 * @return
//	 */
//	public String addBook(){
//		BookType bookType = new BookType();
//		bookType.setTypeId(bookTypeId);
//		BookType newBookType = bookTypeService.getBookType(bookType);//得到图书类型
//		Date putdate = new Date(System.currentTimeMillis());//得到当前时间,作为上架时间
//		Book book = new Book(newBookType, bookName, autho, press, putdate, num, num, price, description);//设置图书
//		boolean b = bookService.addBook(book);//添加图书.返回是否成功添加
//		int success = 0;
//		if(b){
//			success = 1;
//		}else{
//			success = 0;
//		}
//		try {
//			ServletActionContext.getResponse().getWriter().print(success);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			throw new RuntimeException(e.getMessage());
//		}
//		return null;
//	}
//	
	
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
			if(obj instanceof Set||name.equals("books")){	//不过滤book里面的bookType，等到转化bookType的时候，过滤掉其中的set的book集合，解除死循环
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
	 * 修改图书
	 * @return
	 */
	public String updateBook(){
		Book book = new Book();
		book.setBookId(bookId);
		Book updateBook = bookService.getBookById(book);//得到修改的图书信息
		updateBook.setBookName(bookName);
		updateBook.setAutho(autho);//对图书进行修改
		
		BookType type = new BookType();
		type.setTypeId(bookTypeId);
		BookType bookType = bookTypeService.getBookType(type);//得到图书类型
		updateBook.setBookType(bookType);//设置图书类型
		updateBook.setDescription(description);
		updateBook.setPress(press);
		updateBook.setPrice(price);//对图书进行修改
		Book newBook = bookService.updateBookInfo(updateBook);//修改图书信息对象
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
	
	
	
	
	
//	/**
//	 * 多条件查询图书
//	 * @return
//	 */
//	public String queryBook(){
//		//获取页面传递过来的当前页码数
//		if(pageCode==0){
//			pageCode = 1;
//		}
//		//给pageSize,每页的记录数赋值
//		int pageSize = 5;
//		PageBean<Book> pb = null;
//
//		if(bookId==0 && "".equals(bookName.trim()) && bookTypeId==-1 && "".equals(press.trim()) && "".equals(autho.trim())){
//			pb = bookService.findBookByPage(pageCode,pageSize);
//		}else{
//			BookType bookType = new BookType();
//			bookType.setTypeId(bookTypeId);
//			Book book = new Book(bookId, bookType, bookName, autho, press);
//
//			pb = bookService.queryBook(book,pageCode,pageSize);
//		}
//		if(pb!=null){
//			pb.setUrl("queryBook.action?bookId="+bookId+"&bookName="+bookName+"&bookTypeId="+bookTypeId+"&press="+press+"&autho="+autho+"&");
//		}
//		ServletActionContext.getRequest().setAttribute("pb", pb);
//		return "success";
//	}
//	
	
	
	/**
	 * 删除指定图书
	 * @return
	 */
	public String deleteBook(){
		Book book = new Book();
		book.setBookId(bookId);
		boolean deleteBook = bookService.deleteBook(book);//删除图书
		int success = 0;
		if(deleteBook){
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
	
	
	/**
	 * 新增图书，对图书的库存数量进行增加
	 * @return
	 */
	public String addBookNum(){
		Book book = new Book();
		book.setBookId(bookId);
		Book updateBook = bookService.getBookById(book);
		updateBook.setNum(updateBook.getNum()+num);
		updateBook.setCurrentNum((updateBook.getCurrentNum()+num));
		Book newBook = bookService.updateBookInfo(updateBook);
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
