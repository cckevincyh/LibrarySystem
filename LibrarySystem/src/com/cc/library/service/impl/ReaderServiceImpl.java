package com.cc.library.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.cc.library.dao.ForfeitDao;
import com.cc.library.dao.ReaderDao;
import com.cc.library.dao.ReaderTypeDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;
import com.cc.library.service.ReaderService;

public class ReaderServiceImpl implements ReaderService{

	private ReaderDao readerDao;

	private ReaderTypeDao readerTypeDao;
	
	private ForfeitDao forfeitDao;
	
	

	public void setForfeitDao(ForfeitDao forfeitDao) {
		this.forfeitDao = forfeitDao;
	}



	/**
	 * @param readerTypeDao the readerTypeDao to set
	 */
	public void setReaderTypeDao(ReaderTypeDao readerTypeDao) {
		this.readerTypeDao = readerTypeDao;
	}



	public void setReaderDao(ReaderDao readerDao) {
		this.readerDao = readerDao;
	}



	@Override
	public Reader getReader(Reader reader) {
		return readerDao.getReader(reader);
	}



	@Override
	public Reader updateReaderInfo(Reader reader) {
		return readerDao.updateReaderInfo(reader);
	}



	@Override
	public boolean addReader(Reader reader) {
		return readerDao.addReader(reader);
	}



	@Override
	public PageBean<Reader> findReaderByPage(int pageCode, int pageSize) {
		return readerDao.findReaderByPage(pageCode,pageSize);
	}



	@Override
	public Reader getReaderById(Reader reader) {
		return readerDao.getReaderById(reader);
	}



	@Override
	public int deleteReader(Reader reader) {
		//删除读者需要注意的点：如果该读者有尚未归还的书籍或者尚未缴纳的罚款,则不能删除
		//得到该读者的借阅集合
		Reader readerById = readerDao.getReaderById(reader);
		Set<BorrowInfo> borrowInfos = readerById.getBorrowInfos();
		for (BorrowInfo borrowInfo : borrowInfos) {
			if(!(borrowInfo.getState()==2 || borrowInfo.getState()==5)){
				return -1;//有尚未归还的书籍
			}
			//得到该借阅记录的罚金信息
			ForfeitInfo forfeitInfo = new ForfeitInfo();
			forfeitInfo.setBorrowId(borrowInfo.getBorrowId());
			ForfeitInfo forfeitInfoById = forfeitDao.getForfeitInfoById(forfeitInfo);
			if(forfeitInfoById!=null){
				if(forfeitInfoById.getIsPay()==0){
					return -2;//尚未缴纳的罚款
				}
			}
		}
		boolean deleteReader = readerDao.deleteReader(reader);
		if(deleteReader){
			return 1;
		}
		return 0;
	}



	@Override
	public PageBean<Reader> queryReader(Reader reader,int pageCode, int pageSize) {
		return readerDao.queryReader(reader,pageCode,pageSize);
	}



	@Override
	public Reader getReaderBypaperNO(Reader reader) {
		// TODO Auto-generated method stub
		return readerDao.getReaderBypaperNO(reader);
	}



	@Override
	public Reader getReaderByPaperNO(Reader reader) {
		// TODO Auto-generated method stub
		return readerDao.getReaderBypaperNO(reader);
	}



	@Override
	public JSONObject batchAddReader(String fileName,Admin admin) {
		List<Reader> readers = new ArrayList<Reader>();
		List<Reader> failReaders = new ArrayList<Reader>();
		String str[] = new String[]{"证件号码","姓名","读者类型","邮箱","联系方式"};
		// TODO Auto-generated method stub
		String realPath = ServletActionContext.getServletContext().getRealPath(fileName);
		 //创建workbook
        try {
			Workbook workbook = Workbook.getWorkbook(new File(realPath));
			//获取第一个工作表sheet
            Sheet sheet = workbook.getSheet(0);
            
            if(sheet.getColumns()!=5  ){
            	JSONObject jsonObject = new JSONObject();
            	jsonObject.put("error","请下载模板,填入数据上传" );
            	jsonObject.put("state","-1" );
            	return jsonObject;
            }else{
            	  //获取数据
              
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j,0);
                    if(!cell.getContents().equals(str[j])){
                    	JSONObject jsonObject = new JSONObject();
                    	jsonObject.put("state","-1" );
                    	return jsonObject;
                    }
                }
                
            }
            
               
            //接下来就是真正的模板文件，需要解析它

            //获取数据
            for (int i = 1; i < sheet.getRows(); i++) {
              

           	
                String id = sheet.getCell(0,i).getContents().trim();
                String name = sheet.getCell(1,i).getContents().trim();
                String type = sheet.getCell(2,i).getContents().trim();
                String email = sheet.getCell(3,i).getContents().trim();
                String phone = sheet.getCell(4,i).getContents().trim();
                
                if("".equals(id) && "".equals(name) && "".equals(type) && "".equals(email) && "".equals(phone)){
                	//说明这条数据是空的
                	continue;
                }
                
                Reader reader = new Reader();
                reader.setPaperNO(id);
                reader.setName(name);
                reader.setEmail(email);
                reader.setPhone(phone);
                ReaderType readerType = new ReaderType();
                readerType.setReaderTypeName(type);
                reader.setReaderType(readerType);
            	if("".equals(id) || "".equals(name) || "".equals(type)){
	        		//要是前3列有一列没有数据，说明这条数据是非法的
            		//保存这条非法数据
            		failReaders.add(reader);
	        		continue;
            	}
                
                //需要根据类型名称找到对应的读者类型
                ReaderType typeByName = readerTypeDao.getTypeByName(readerType);
                if(typeByName==null){
                	//找不到这个类型，就说明这条数据非法,跳出循环
                	//保存这条非法数据
            		failReaders.add(reader);
                	continue;
                }
                //有这个类型的读者,接下来就是封装数据，准备进行批量添加
                reader.setPwd("123456");//默认密码123456
                reader.setReaderType(typeByName);
                reader.setCreateTime(new Date(System.currentTimeMillis()));
                reader.setAdmin(admin);
                readers.add(reader);
            }
            workbook.close();
            int success = readerDao.batchAddReader(readers,failReaders);
            
            JSONObject jsonObject = new JSONObject();
            if(failReaders.size() != 0){
            	 //把不成功的导出成excel文件
                String exportExcel = exportExcel(failReaders);
                jsonObject.put("state", "2");
                jsonObject.put("message","成功" + success + "条,失败" + failReaders.size() + "条");
                jsonObject.put("failPath", "admin/FileDownloadAction.action?fileName=" + exportExcel);
                return jsonObject;
            }else{
            	 jsonObject.put("state", "1");
                 jsonObject.put("message","成功" + success + "条,失败" + failReaders.size() + "条");
                 return jsonObject;
            }
            
            
            
            
		} catch (Throwable e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
	}
	
	
	/**
	 * 导出excel文件
	 * @param failReaders 失败的数据
	 * @return 返回文件路径
	 */
	public String exportExcel(List<Reader> failReaders){
		//用数组存储表头
        String[] title = {"证件号码","姓名","读者类型","邮箱","联系方式"};
        String path = ServletActionContext.getServletContext().getRealPath("/download");
        String fileName = +System.currentTimeMillis()+"_failReaders.xls";
        //创建Excel文件
        File file = new File(path, fileName);
        try {
            file.createNewFile();
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("sheet1", 0);

            Label label = null;

            //第一行设置列名
            for(int i = 0; i<title.length; i++){
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }

            //追加数据
            for(int i = 1; i<=failReaders.size(); i++){
                label = new Label(0, i, failReaders.get(i-1).getPaperNO());
                sheet.addCell(label);
                label = new Label(1, i, failReaders.get(i-1).getName());
                sheet.addCell(label);
                label = new Label(2, i, failReaders.get(i-1).getReaderType().getReaderTypeName());
                sheet.addCell(label);
                label = new Label(3, i, failReaders.get(i-1).getEmail());
                sheet.addCell(label);
                label = new Label(4, i, failReaders.get(i-1).getPhone());
                sheet.addCell(label);
            }
            //写入数据
            workbook.write();

            workbook.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return fileName;
	}

}
