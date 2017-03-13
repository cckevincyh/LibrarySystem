package com.cc.library.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.ReaderType;
import com.cc.library.service.ReaderTypeService;
import com.opensymphony.xwork2.ActionSupport;

public class ReaderTypeManageAction extends ActionSupport{

	public ReaderTypeService readerTypeService;

	public void setReaderTypeService(ReaderTypeService readerTypeService) {
		this.readerTypeService = readerTypeService;
	}
	
	private Integer id;
	private Integer maxNum;
	private Integer bday;
	private Double penalty;
	
	
	
	
	
	
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}



	public void setBday(Integer bday) {
		this.bday = bday;
	}



	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getAllReaderType(){
	
		List<ReaderType> allReaderType = readerTypeService.getAllReaderType();
		ServletActionContext.getRequest().setAttribute("readerTypes", allReaderType);
		return "success";
	} 
	
	
	/**
	 * 得到指定的读者类型信息
	 * @return
	 */
	public String getReaderType(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		ReaderType readerType = new  ReaderType();
		readerType.setReaderTypeId(id);
		ReaderType newReaderType = readerTypeService.getTypeById(readerType);
		JSONObject jsonObject = JSONObject.fromObject(newReaderType);
		try {
			response.getWriter().print(jsonObject);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return null;
	}
	
	
	public String updateReaderType(){
		ReaderType readerType = new ReaderType();
		readerType.setReaderTypeId(id);
		ReaderType updateReaderType = readerTypeService.getTypeById(readerType);
		updateReaderType.setMaxNum(maxNum);
		updateReaderType.setBday(bday);
		updateReaderType.setPenalty(penalty);
		ReaderType newReaderType = readerTypeService.updateReaderType(updateReaderType);
		int success = 0;
		if(newReaderType!=null){
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
