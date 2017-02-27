package com.cc.library.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Reader;
import com.cc.library.service.ReaderService;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class ReaderLoginAction extends ActionSupport {

	private ReaderService readerService;
	
	
	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}




	private String readerId;
	private String pwd;


	
	
	
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

	/**
	 * Ajax异步请求获得登录许可
	 * @return 返回登录状态
	 */
	public String login(){
		//学生
		Reader reader = new Reader();
		reader.setReaderId(readerId);
		reader.setPwd(pwd);
		int login = 1;
		Reader newReader = readerService.login(reader);
		if(newReader==null){
			//用户名不存在
			login = -1;
		}else if(!newReader.getPwd().equals(reader.getPwd())){
			//密码不正确
			login =  0;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		 try {	
			response.getWriter().print(login);
			//存储入session
			if(login==1){
				ServletActionContext.getContext().getSession().put("reader", newReader);
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
			
		return null;
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	public String logout(){
		ServletActionContext.getContext().getSession().remove("reader");
		return "logout";
	}
	
	
}
