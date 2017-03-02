package com.cc.library.service;

import java.util.List;

import com.cc.library.domain.Admin;



public interface AdminService {
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	public Admin getAdmin(Admin admin);

	/**
	 * 修改管理员个人资料
	 * @param admin 传入修改的对象
	 * @return 修改后的对象
	 */
	public Admin updateAdminInfo(Admin admin);

	public List<Admin> getAllAdmins();
	
}
