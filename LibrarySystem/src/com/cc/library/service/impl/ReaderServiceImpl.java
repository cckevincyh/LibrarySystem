package com.cc.library.service.impl;

import java.util.List;
import java.util.Set;

import com.cc.library.dao.ForfeitDao;
import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.domain.ReaderType;
import com.cc.library.service.ReaderService;

public class ReaderServiceImpl implements ReaderService{

	private ReaderDao readerDao;

	private ForfeitDao forfeitDao;
	
	

	public void setForfeitDao(ForfeitDao forfeitDao) {
		this.forfeitDao = forfeitDao;
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


	
}
