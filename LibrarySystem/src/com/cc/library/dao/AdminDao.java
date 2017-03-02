package com.cc.library.dao;

import java.util.List;

import com.cc.library.domain.Admin;

public interface AdminDao {

	public Admin getAdmin(Admin admin);

	public Admin updateAdminInfo(Admin admin);

	public List<Admin> getAllAdmins();
}
