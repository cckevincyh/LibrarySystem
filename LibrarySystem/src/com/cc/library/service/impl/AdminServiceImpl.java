package com.cc.library.service.impl;


import com.cc.library.dao.AdminDao;
import com.cc.library.domain.Admin;
import com.cc.library.service.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin login(Admin admin) {
		return adminDao.getAdmin(admin);
	}
	
}
