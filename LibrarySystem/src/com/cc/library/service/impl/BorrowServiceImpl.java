package com.cc.library.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cc.library.dao.BackDao;
import com.cc.library.dao.BookDao;
import com.cc.library.dao.BorrowDao;
import com.cc.library.dao.ForfeitDao;
import com.cc.library.dao.ReaderDao;
import com.cc.library.domain.BackInfo;
import com.cc.library.domain.Book;
import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.ForfeitInfo;
import com.cc.library.domain.PageBean;
import com.cc.library.domain.Reader;
import com.cc.library.service.BorrowService;

public class BorrowServiceImpl implements BorrowService{

	private BorrowDao borrowDao;
	private BookDao bookDao;
	private ReaderDao readerDao;
	private ForfeitDao forfeitDao;
	private BackDao backDao;
	
	
	
	
	

	public void setBackDao(BackDao backDao) {
		this.backDao = backDao;
	}

	public void setForfeitDao(ForfeitDao forfeitDao) {
		this.forfeitDao = forfeitDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setReaderDao(ReaderDao readerDao) {
		this.readerDao = readerDao;
	}

	public void setBorrowDao(BorrowDao borrowDao) {
		this.borrowDao = borrowDao;
	}

	@Override
	public PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		return borrowDao.findBorrowInfoByPage(pageCode,pageSize);
	}

	@Override
	public BorrowInfo getBorrowInfoById(BorrowInfo info) {
		// TODO Auto-generated method stub
		return borrowDao.getBorrowInfoById(info);
	}

	
	@Override
	public int addBorrow(BorrowInfo info) {
		/*
		 * 1. 得到借阅的读者
		 * 
		 * 2. 查看读者输入的密码是否匹配
		 * 		2.1 如果不匹配提示 密码错误
		 * 		2.2 如果匹配,执行第3步骤
		 * 
		 * 3. 查看该读者的借阅信息
		 * 		3.1 查看借阅信息的条数
		 * 		3.2 查看该读者的类型得出该读者的最大借阅数量
		 * 		3.3 匹配借阅的数量是否小于最大借阅量
		 * 			3.3.1 小于,则可以借阅
		 * 			3.3.2 大于,则不可以借阅,直接返回借阅数量已超过
		 * 		3.4 查看读者的罚款信息,是否有未缴纳的罚款
		 * 			3.4.1 如果没有,继续第3的操作步骤
		 * 			3.4.2 如果有,直接返回有尚未缴纳的罚金
		 * 
		 * 4. 查看借阅的书籍
		 * 		4.1 查看该书籍的在馆数量是否大于1,,如果为1则不能借阅,必须留在馆内浏览
		 * 			4.1.1 如果大于1,进入第4操作步骤
		 * 			4.1.2 如果小于等于1,提示该图书为馆内最后一本,无法借阅
		 * 
		 * 5. 处理借阅信息
		 * 		5.1 得到该读者的最大借阅天数,和每日罚金
		 * 			5.1.1 获得当前时间
		 * 			5.1.2 根据最大借阅天数,计算出截止日期
		 * 			5.1.3 为借阅信息设置每日的罚金金额
		 * 		5.2 获得该读者的信息,为借阅信息设置读者信息
		 * 		5.3 获得图书信息,为借阅信息设置图书信息
		 * 		5.4 获得管理员信息,为借阅信息设置操作的管理员信息
		 * 
		 * 6. 存储借阅信息
		 * 
		 * 
		 * 
		 * 7. 借阅的书籍的在馆数量需要减少
		 * 
		 * 8. 生成归还记录
		 * 
		 */
		//得到密码
		String pwd = info.getReader().getPwd();
		//1. 得到借阅的读者
		Reader reader = readerDao.getReaderBypaperNO(info.getReader());
		//2. 查看读者输入的密码是否匹配
		if(!reader.getPwd().equals(pwd)){
			return -1;//返回-1表示密码错误
		}
		//3. 查看该读者的借阅信息
		List<BorrowInfo> readerInfos = borrowDao.getBorrowInfoByReader(reader);
		//查看借阅信息的条数
		//查看该读者的类型得出该读者的最大借阅数量
		// 匹配借阅的数量是否小于最大借阅量
		if(readerInfos!=null && readerInfos.size()>=reader.getReaderType().getMaxNum()){
			return -2;	//返回借阅数量已超过
		}
		// 查看读者的罚款信息,是否有未缴纳的罚款
		List<ForfeitInfo> list = forfeitDao.getForfeitByReader(reader);
		for(ForfeitInfo forfeitInfo : list){
			if(forfeitInfo.getIsPay()==0){
				return -3;//尚未缴纳的罚金
			}
		}
		//4. 查看借阅的书籍
		// 查看该书籍的在馆数量是否大于1,,如果为1则不能借阅,必须留在馆内浏览
		Book bookByISBN = bookDao.getBookByISBN(info.getBook());
		if(bookByISBN.getCurrentNum()<=1){
			return -4;	//该图书为馆内最后一本,无法借阅
		}
		
		//5. 处理借阅信息
		//得到该读者的最大借阅天数,和每日罚金
		int maxDay = reader.getReaderType().getBday();//可借天数
		double penalty = reader.getReaderType().getPenalty();//每日罚金
		
		//获得当前时间
		Date borrowDate = new Date(System.currentTimeMillis());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(borrowDate);
		calendar.add(Calendar.DAY_OF_MONTH, +maxDay);//+maxDay今天的时间加maxDay天
		
		// 根据最大借阅天数,计算出截止日期
		Date endDate = calendar.getTime();
		
		//获得该读者的信息,为借阅信息设置读者信息
		BorrowInfo borrowInfo = new BorrowInfo();
		borrowInfo.setReader(reader);
		borrowInfo.setAdmin(info.getAdmin());
		borrowInfo.setBook(bookByISBN);
		borrowInfo.setBorrowDate(borrowDate);
		borrowInfo.setEndDate(endDate);
		borrowInfo.setPenalty(penalty);
	
		int id = borrowDao.addBorrow(borrowInfo);//返回1成功添加,返回0添加失败
		//在馆数量需要减少
		int state = 0;
		if(id!=0){
			bookByISBN.setCurrentNum(bookByISBN.getCurrentNum()-1);
			bookDao.updateBookInfo(bookByISBN);
			BackInfo info2 = new BackInfo();
			BorrowInfo borrowInfo2 = new BorrowInfo();
			borrowInfo2.setBorrowId(id);
			info2.setBorrowInfo(borrowInfo2);
			info2.setBorrowId(id);
			state = backDao.addBack(info2);
		}
		
		
		return state;
	}

	@Override
	public boolean checkBorrowInfo() {
		// 检查借阅表是否有逾期
		//主要步骤
		/*
		 *	1.得到所有的未归还的借阅记录
		 *
		 * 	2.遍历所有的未归还的借阅记录
		 * 
		 * 	3.查看当前时间和借阅的截止的时间的大小
		 *		3.1 如果小于,直接跳过
		 *		3.2如果大于则需要进行逾期处理的,进行第4步操作
		 *
		 *	4.用当前时间减去截止时间得到逾期的天数
		 *
		 *	5.为当前借阅记录设置逾期天数,并进行数据库修改
		 *
		 *	6.需要生成罚金记录
		 *		6.1 得到当前借阅记录的罚金金额,和当前的逾期天数进行相乘,得到罚金金额
		 *		6.2 将当前借阅记录的id和罚金的金额设置进新生成的罚金记录
		 * 
		 */
		//得到所有的未归还的借阅记录
		List<BorrowInfo> borrowInfos = borrowDao.getBorrowInfoByState(0);
		if(borrowInfos!=null){
			for (BorrowInfo borrowInfo : borrowInfos) {
				long time1 = borrowInfo.getEndDate().getTime();//截止时间
				long time2 = System.currentTimeMillis();//当前时间
				if(time2>time1){
					//当前时间大于截止时间,已经逾期
					Double days =Math.floor((time2-time1)/(24*60*60*1000));
					//用当前时间减去截止时间得到逾期的天数
					int day = days.intValue();
					//为当前借阅记录设置逾期天数,并进行数据库修改
					borrowInfo.setOverday(day);
					//进行数据库修改
					borrowDao.updateBorrowInfo(borrowInfo);
					//需要生成罚金记录
					ForfeitInfo forfeitInfo = new ForfeitInfo();
					forfeitInfo.setBorrowId(borrowInfo.getBorrowId());
					forfeitInfo.setBorrowInfo(borrowInfo);
					// 得到当前借阅记录的罚金金额,和当前的逾期天数进行相乘,得到罚金金额
					double pay = borrowInfo.getPenalty() * day;
					//将当前借阅记录的id和罚金的金额设置进新生成的罚金记录
					forfeitInfo.setForfeit(pay);
					//生成的罚金记录
					boolean flag = forfeitDao.addForfeitInfo(forfeitInfo);
					if(!flag){
						return false;
						
					}
				}
			}
		}
		return true;
		
	}

	
}
