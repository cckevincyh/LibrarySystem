package com.cc.library.dao;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public interface BorrowDao {

	PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

}
