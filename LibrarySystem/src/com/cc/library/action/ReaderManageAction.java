package com.cc.library.action;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;
import com.cc.library.service.ReaderService;
import com.opensymphony.xwork2.ActionSupport;

public class ReaderManageAction extends ActionSupport{
	
	private ReaderService readerService;
	
	
	
	
	public void setReaderService(ReaderService readerService) {
		this.readerService = readerService;
	}






	private Integer readerId;
	private String name;
	private String phone;
	private String pwd;
	private Integer readerType;
	private int pageCode;
	private String paperNO;
	private String email;
	
	
	
	
	
	
	
	
	
	
	public void setPaperNO(String paperNO) {
		this.paperNO = paperNO;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}




	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}






	public void setName(String name) {
		this.name = name;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}






	public void setPwd(String pwd) {
		this.pwd = pwd;
	}






	public void setReaderType(Integer readerType) {
		this.readerType = readerType;
	}






	/**
	 *添加读者
	 * @return
	 */
	public String addReader(){
		//得到当前时间
		Date createTime = new Date(System.currentTimeMillis());
		//得到当前管理员
		Admin admin = (Admin) ServletActionContext.getContext().getSession().get("admin");
		//得到当前读者类型
		ReaderType type = new ReaderType();
		type.setReaderTypeId(readerType);
		Reader reader = new Reader(name, pwd, phone, type, email, admin, paperNO, createTime);
		
		Reader oldReader = readerService.getReaderBypaperNO(reader);//检查是否已经存在该paperNO的读者
		int success = 0;
		if(oldReader!=null){
			success = -1;//已存在该id
		}else{
			
			
			boolean b = readerService.addReader(reader);
			if(b){
				success = 1;
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
	
	
	/**
	 * 根据页码查询读者
	 * @return
	 */
	public String findReaderByPage(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Reader> pb = readerService.findReaderByPage(pageCode,pageSize);
		if(pb!=null){
			pb.setUrl("findReaderByPage.action?");
		}
		//存入request域中
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return  "success";
	}
	
	
	
	
	/**
	 * 得到指定的读者
	 * @return
	 */
	public String getReader(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		Reader reader = new Reader();
		reader.setReaderId(readerId);
		Reader newReader = readerService.getReaderById(reader);
		JsonConfig jsonConfig = new JsonConfig();

		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		    public boolean apply(Object obj, String name, Object value) {
			if(obj instanceof Set||name.equals("borrowInfos") || obj instanceof Authorization || name.equals("authorization") ){//过滤掉集合
				return true;
			}else{
				return false;
			}
		   }
		});
		
		
		JSONObject jsonObject = JSONObject.fromObject(newReader,jsonConfig);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	
	/**
	 * 修改指定读者
	 * @return
	 */
	public String updateReader(){
		Reader reader = new Reader();
		reader.setReaderId(readerId);
		Reader updateReader = readerService.getReaderById(reader);//查出需要修改的读者对象;
		updateReader.setName(name);
		updateReader.setPhone(phone);
		updateReader.setPwd(pwd);
		updateReader.setPaperNO(paperNO);
		updateReader.setEmail(email);
		//设置reader的值
		ReaderType type = new ReaderType();
		type.setReaderTypeId(readerType);
		updateReader.setReaderType(type);
		Reader newReader = readerService.updateReaderInfo(updateReader);
		int success = 0;
		if(newReader!=null){
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
	 * 删除指定管理员
	 * @return
	 */
	public String deleteReader(){
		Reader reader = new Reader();
		reader.setReaderId(readerId);
		boolean deleteReader = readerService.deleteReader(reader);
		int success = 0;
		if(deleteReader){
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
	
	
	
	public String queryReader(){
		//获取页面传递过来的当前页码数
		if(pageCode==0){
			pageCode = 1;
		}
		//给pageSize,每页的记录数赋值
		int pageSize = 5;
		PageBean<Reader> pb = null;
		if("".equals(paperNO.trim()) && "".equals(name.trim()) && readerType==-1){
			pb = readerService.findReaderByPage(pageCode,pageSize);
		}else{
			Reader reader = new Reader();
			reader.setPaperNO(paperNO);
			ReaderType type = new ReaderType();
			type.setReaderTypeId(readerType);
			reader.setReaderType(type);
			reader.setName(name);
			pb = readerService.queryReader(reader,pageCode,pageSize);
		}
		if(pb!=null){
			pb.setUrl("queryReader.action?paperNO="+paperNO+"&name="+name+"&readerType="+readerType+"&");
		}
		ServletActionContext.getRequest().setAttribute("pb", pb);
		return "success";
	}
	
	
	
	


}
