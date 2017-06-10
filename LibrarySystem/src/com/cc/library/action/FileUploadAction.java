package com.cc.library.action;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUploadAction {

    private File upload;
    private String uploadContentType;
    private String uploadFileName;//上传图片的名字
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
    
    
    
	public void fileUpload() throws IOException{
		  //设置上传的路径
        String path = ServletActionContext.getServletContext().getRealPath("/upload");
        uploadFileName = +System.currentTimeMillis()+"_"+uploadFileName;
        File file = new File(path, uploadFileName);
		if(uploadContentType!=null && uploadContentType.equals("application/vnd.ms-excel")){
			 try {

		            FileUtils.copyFile(upload, file);
		            upload.delete();
		            
		            //将要传入页面的数据变成json格式
		            JSONObject jsonObject=new JSONObject();
		            String path2 = "upload/"+uploadFileName;
		            jsonObject.put("path", path2);
		            jsonObject.put("state", "success");
		            //控制台输出格式化的json数据
		            
		            ServletActionContext.getResponse().getWriter().print(jsonObject);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}else{
			 //将要传入页面的数据变成json格式
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("state", "error");
            ServletActionContext.getResponse().getWriter().print(jsonObject);
		}

     


        
        
        
    }
}
