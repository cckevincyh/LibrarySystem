package com.cc.library.service.impl;

import java.util.List;

import com.cc.library.dao.BookDao;
import com.cc.library.dao.BorrowDao;
import com.cc.library.dao.ForfeitDao;
import com.cc.library.domain.Book;
import com.cc.library.domain.BookType;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.service.BookService;

public class BookServiceImpl implements BookService{

	private BookDao bookDao;

	private BorrowDao borrowDao;
	private ForfeitDao forfeitDao;
	
	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
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


}
