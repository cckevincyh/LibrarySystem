package com.cc.library.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.cc.library.dao.BookDao;
import com.cc.library.dao.BookTypeDao;
import com.cc.library.dao.BorrowDao;
import com.cc.library.dao.ForfeitDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;

public class BookServiceImpl implements BookService{

	private BookDao bookDao;

	private BookTypeDao bookTypeDao;
	private BorrowDao borrowDao;
	private ForfeitDao forfeitDao;
	
	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	


	
	/**
	 * @param bookTypeDao the bookTypeDao to set
	 */
	public void setBookTypeDao(BookTypeDao bookTypeDao) {
		this.bookTypeDao = bookTypeDao;
	}





	public void setForfeitDao(ForfeitDao forfeitDao) {
		this.forfeitDao = forfeitDao;
	}




	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}



	@Override
	public PageBean<Book> findBookByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return bookDao.findBookByPage(pageCode,pageSize);
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.addBook(book);
	}


	@Override
	public Book getBookById(Book book) {
		// TODO Auto-generated method stub
		return bookDao.getBookById(book);
	}

	@Override
	public Book updateBookInfo(Book updateBook) {
		// TODO Auto-generated method stub
		return bookDao.updateBookInfo(updateBook);
	}

	@Override
	public PageBean<Book> queryBook(Book book, int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return bookDao.queryBook(book,pageCode,pageSize);
	}

	@Override
	public int deleteBook(Book book) {
		// TODO Auto-generated method stub
		//删除图书需要注意的事项：如果该书有尚未归还的记录或者尚未缴纳的罚款记录,则不能删除
		//得到该书的借阅记录
		List<BorrowInfo> borrowInfos = borrowDao.getBorrowInfoByBook(book);
		for (BorrowInfo borrowInfo : borrowInfos) {
			if(!(borrowInfo.getState()==2 || borrowInfo.getState()==5)){
				return -1;//该书还在借阅中,无法删除
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
		boolean deleteBook = bookDao.deleteBook(book);
		if(deleteBook){
			return 1;
		}
		return 0;
	}




	@Override
	public JSONObject batchAddBook(String fileName, Admin admin) {
		List<Book> books = new ArrayList<Book>();
		List<Book> failBooks = new ArrayList<Book>();
		String str[] = new String[]{"图书ISBN号","图书类型","图书名称","作者名称","出版社","价格","数量","描述"};
		// TODO Auto-generated method stub
		String realPath = ServletActionContext.getServletContext().getRealPath(fileName);
		 //创建workbook
        try {
			Workbook workbook = Workbook.getWorkbook(new File(realPath));
			//获取第一个工作表sheet
            Sheet sheet = workbook.getSheet(0);
            
            if(sheet.getColumns()!=8 ){
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
                    	jsonObject.put("error","请下载模板,填入数据上传" );
                    	jsonObject.put("state","-1" );
                    	return jsonObject;
                    }
                }
                
            }
            
               
            //接下来就是真正的模板文件，需要解析它

            //获取数据
            for (int i = 1; i < sheet.getRows(); i++) {
              

           	
                String ISBN = sheet.getCell(0,i).getContents().trim();
                String type = sheet.getCell(1,i).getContents().trim();
                String bookName = sheet.getCell(2,i).getContents().trim();
                String author = sheet.getCell(3,i).getContents().trim();
                String publish = sheet.getCell(4,i).getContents().trim();
                String price = sheet.getCell(5,i).getContents().trim();
                String num = sheet.getCell(6,i).getContents().trim();
                String description = sheet.getCell(7,i).getContents().trim();
                if("".equals(ISBN) && "".equals(type) && "".equals(bookName) && "".equals(author) && "".equals(publish) && "".equals(price) && "".equals(num) && "".equals(description)){
                	//说明这条数据是空的
                	continue;
                }
                
                Book book = new Book();
                book.setISBN(ISBN);
                book.setBookName(bookName);
                book.setAutho(author);
                book.setDescription(description);
                book.setPress(publish);
                BookType bookType = new BookType();
                bookType.setTypeName(type);
                book.setBookType(bookType);
                
                try {  
                    if(Double.parseDouble(price)<=0){
                    	//说明不是正数
                    	book.setPrice(new Double(-1));
                    	failBooks.add(book);
                    	continue ;
                    }
                    book.setPrice(Double.parseDouble(price));
                } catch (NumberFormatException e) { 
                    //说明不是数字
                	book.setPrice(new Double(-1));
                	failBooks.add(book);
                	continue ;
                }  
                
                
                
                try {  
                    if(Integer.parseInt(num)<=0){
                    	//说明不是正整数
                    	book.setNum(-1);
                    	failBooks.add(book);
                    	continue ;
                    }
                    book.setNum(Integer.parseInt(num));
                    book.setCurrentNum(Integer.parseInt(num));
                } catch (NumberFormatException e) {  
                    //说明不是整数
                	book.setNum(-1);
                	failBooks.add(book);
                	continue ;
                }  
                
                
            	if("".equals(ISBN) || "".equals(bookName) || "".equals(type)){
	        		//要是前3列有一列没有数据，说明这条数据是非法的
            		//保存这条非法数据
            		failBooks.add(book);
	        		continue;
            	}
                
                //需要根据类型名称找到对应的读者类型
                BookType typeByName = bookTypeDao.getBookTypeByName(bookType);
                book.setBookType(typeByName);
                if(typeByName==null){
                	//找不到这个类型，就说明这条数据非法,跳出循环
                	//保存这条非法数据
                	bookType.setTypeName(bookType.getTypeName() + "(没有该类型)");
            		failBooks.add(book);
                	continue;
                }
                
                
                //有这个类型的读者,接下来就是封装数据，准备进行批量添加
                book.setPutdate(new Date(System.currentTimeMillis()));
                book.setAdmin(admin);
                books.add(book);
            }
            workbook.close();
            int success = bookDao.batchAddBook(books,failBooks);
            
            JSONObject jsonObject = new JSONObject();
            if(failBooks.size() != 0){
            	 //把不成功的导出成excel文件
                String exportExcel = exportExcel(failBooks);
                jsonObject.put("state", "2");
                jsonObject.put("message","成功" + success + "条,失败" + failBooks.size() + "条");
                jsonObject.put("failPath", "admin/FileDownloadAction.action?fileName=" + exportExcel);
                return jsonObject;
            }else{
            	 jsonObject.put("state", "1");
                 jsonObject.put("message","成功" + success + "条,失败" + failBooks.size() + "条");
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
	public String exportExcel(List<Book> failBooks){
		//用数组存储表头
        String[] title = {"图书ISBN号","图书类型","图书名称","作者名称","出版社","价格","数量","描述"};
        String path = ServletActionContext.getServletContext().getRealPath("/download");
        String fileName = +System.currentTimeMillis()+"_failBooks.xls";
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
            for(int i = 1; i<=failBooks.size(); i++){
                label = new Label(0, i, failBooks.get(i-1).getISBN());
                sheet.addCell(label);
                label = new Label(1, i, failBooks.get(i-1).getBookType().getTypeName());
                sheet.addCell(label);
                label = new Label(2, i, failBooks.get(i-1).getBookName());
                sheet.addCell(label);
                label = new Label(3, i, failBooks.get(i-1).getAutho());
                sheet.addCell(label);
                label = new Label(4, i, failBooks.get(i-1).getPress());
                sheet.addCell(label);
                
                if(failBooks.get(i-1).getPrice()==null){
                	label = new Label(5, i, "数据错误");
                    sheet.addCell(label);
                }else if(failBooks.get(i-1).getPrice().equals(new Double(-1))){
                	 label = new Label(5, i, "数据错误");
                     sheet.addCell(label);
                }else{
                	 label = new Label(5, i, failBooks.get(i-1).getPrice().toString());
                     sheet.addCell(label);
                }
                if(failBooks.get(i-1).getNum()==null){
                	label = new Label(6, i, "数据错误");
                    sheet.addCell(label);
                }else if(failBooks.get(i-1).getNum().equals(-1)){
                	 label = new Label(6, i, "数据错误");
                     sheet.addCell(label);
                }else{
               	 label = new Label(6, i, failBooks.get(i-1).getNum().toString());
                 sheet.addCell(label);
                }
                label = new Label(7, i, failBooks.get(i-1).getDescription());
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
