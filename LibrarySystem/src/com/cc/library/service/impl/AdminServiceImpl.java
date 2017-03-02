package com.cc.library.service.impl;


import java.util.List;

import com.cc.library.dao.AdminDao;
import com.cc.library.domain.Admin;
import com.cc.library.service.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin getAdmin(Admin admin) {
		return adminDao.getAdmin(admin);
	}

	@Override
	public Admin updateAdminInfo(Admin admin) {
		return adminDao.updateAdminInfo(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminDao.getAllAdmins();
	}
	
}
