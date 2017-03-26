package com.cc.library.service;

import com.cc.library.domain.BorrowInfo;
import com.cc.library.domain.PageBean;

public interface BorrowService {

	PageBean<BorrowInfo> findBorrowInfoByPage(int pageCode, int pageSize);

}
