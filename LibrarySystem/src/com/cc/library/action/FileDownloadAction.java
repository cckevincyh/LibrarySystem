package com.cc.library.action;

import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class FileDownloadAction extends ActionSupport{
	
	private String fileName;
	
	

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	//返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流  
    public InputStream getDownloadFile() throws Exception  
    {  
           //获取资源路径  
           return ServletActionContext.getServletContext().getResourceAsStream("download/"+fileName) ;  
          
    }  
      
    @Override  
    public String execute() throws Exception {  
          
        return SUCCESS;  
    }  
}
