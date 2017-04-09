package com.cc.library.action;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Book;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;
import com.cc.library.service.BookService;
import com.cc.library.service.BorrowService;
import com.opensymphony.xwork2.ActionSupport;

public class BorrowManageAction extends ActionSupport{

	private BorrowService borrowService;
	private BookService bookService;

	public void setBorrowService(BorrowService borrowService) {
		this.borrowService = borrowService;
	}
	
	




	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}






	private int pageCode;
	private int borrowId;
	private String borrowReaderId;
	private Integer borrowBookId;
	
	
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}




	public void setBorrowReaderId(String borrowReaderId) {
		this.borrowReaderId = borrowReaderId;
	}




	public void setBorrowBookId(int borrowBookId) {
		this.borrowBookId = borrowBookId;
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
		System.out.println(111);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		BorrowInfo info = new BorrowInfo();
		info.setBorrowId(borrowId);
		BorrowInfo newInfo = borrowService.getBorrowInfoById(info);
		System.out.println(newInfo);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Set||name.equals("books")||obj instanceof Set||name.equals("borrowInfos")||obj instanceof Set||name.equals("forfeitInfos")){	
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

	
	
//	/**
//	 * 借阅处理
//	 * @return
//	 */
//	public String borrowBook(){
//		Reader reader = new Reader();
//		reader.setReaderId(borrowReaderId);
//		Book book = new Book();
//		book.setBookId(borrowBookId);
//		BorrowInfo info = new BorrowInfo(borrowId, book, reader);
//		int code = borrowService.addBorrow(info);//需要特殊处理
//		
//		
//		return "success";
//	}
//	
}
