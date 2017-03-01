package com.cc.library.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Reader;
import com.cc.library.service.ReaderService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ReaderInfoAction extends ActionSupport{
	private ReaderService readerService;

	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}
	
	private String name;
	private String phone;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;
	
	
	
	public ReaderService getReaderService() {
		return readerService;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}


	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}


	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}


	/**
	 * 读者个人资料
	 * @return
	 */
	public String readerInfo(){
		Map<String, Object> session = ServletActionContext.getContext().getSession();
		Reader reader = (Reader) session.get("reader");
		reader.setName(name);
		reader.setPhone(phone);
		Reader newReader = readerService.updateReaderInfo(reader);
		int success = 0;
		if(newReader!=null){
			success = 1;
			//重新存入session
			session.put("reader", newReader);
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
	 * 读者密码修改
	 * @return
	 */
	public String readerPwd(){
		Reader reader = (Reader) ServletActionContext.getContext().getSession().get("reader");
		int state = -1;//原密码错误
		//取出原密码进行比对
		if(reader.getPwd().equals(oldPwd)){
			if(newPwd.equals(confirmPwd)){
				state = 1;//修改成功
				reader.setPwd(newPwd);
				reader = readerService.updateReaderInfo(reader);
				//重新存入session
				ServletActionContext.getContext().getSession().put("reader", reader);
			}else{
				state = 0;//确认密码不一致
			}
		}
		try {
			ServletActionContext.getResponse().getWriter().print(state);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}

}
