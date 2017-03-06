package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.Admin;
import com.cc.library.domain.PageBean;

public interface AdminDao {

	public Admin getAdminByUserName(Admin admin);

	public Admin updateAdminInfo(Admin admin);

	public List<Admin> getAllAdmins();

	public boolean addAdmin(Admin admin);

	public Admin getAdminById(Admin admin);

	public PageBean<Admin> findAdminByPage(int pageCode, int pageSize);

	public PageBean<Admin> queryAdmin(Admin admin,int pageCode, int pageSize);
}
