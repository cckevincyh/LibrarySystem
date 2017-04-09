package com.cc.library.service.impl;


import java.util.List;

import com.cc.library.dao.AdminDao;
import com.cc.library.domain.Admin;
import com.cc.library.domain.PageBean;
import com.cc.library.service.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin getAdminByUserName(Admin admin) {
		return adminDao.getAdminByUserName(admin);
	}

	@Override
	public Admin updateAdminInfo(Admin admin) {
		return adminDao.updateAdminInfo(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminDao.getAllAdmins();
	}

	@Override
	public boolean addAdmin(Admin admin) {
		return adminDao.addAdmin(admin);
	}

	@Override
	public Admin getAdminById(Admin admin) {
		return adminDao.getAdminById(admin);
	}

	@Override
	public PageBean<Admin> findAdminByPage(int pageCode, int pageSize) {
		return adminDao.findAdminByPage(pageCode,pageSize);
	}

	@Override
	public PageBean<Admin> queryAdmin(Admin admin,int pageCode,int pageSize) {
		return adminDao.queryAdmin(admin,pageCode,pageSize);
	}

	@Override
	public boolean deleteAdmin(Admin admin) {
		Admin deleteAdmin = getAdminById(admin);
		return adminDao.deleteAdmin(deleteAdmin);
		
	}
	
}
