package com.cc.library.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Reader;
import com.cc.library.service.ReaderService;
import com.cc.library.util.Md5Utils;
import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class ReaderLoginAction extends ActionSupport {

	private ReaderService readerService;
	
	
	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}




	private String paperNO;
	private String pwd;


	
	
	
	

	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
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
		reader.setPaperNO(paperNO);
		reader.setPwd(Md5Utils.md5(pwd));
		int login = 1;
		Reader newReader = readerService.getReaderByPaperNO(reader);
		if(newReader==null){
			//用户名不存在
			login = -1;
		}else if(!newReader.getPwd().equals(reader.getPwd())){
			//密码不正确
			login =  -2;
		}else{
			//存储入session
			ServletActionContext.getContext().getSession().put("reader", newReader);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		 try {	
			response.getWriter().print(login);
			
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
