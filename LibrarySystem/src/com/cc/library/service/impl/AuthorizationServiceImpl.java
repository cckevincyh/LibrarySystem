package com.cc.library.service.impl;

import com.cc.library.dao.AuthorizationDao;
import com.cc.library.domain.Authorization;
import com.cc.library.service.AuthorizationService;

public class AuthorizationServiceImpl implements AuthorizationService{

	private AuthorizationDao authorizationDao;

	public void setAuthorizationDao(AuthorizationDao authorizationDao) {
		this.authorizationDao = authorizationDao;
	}

	@Override
	public boolean addAuthorization(Authorization authorization) {
		// TODO Auto-generated method stub
		return authorizationDao.addAuthorization(authorization);
	}

	@Override
	public Authorization getAuthorizationByaid(Authorization authorization) {
		// TODO Auto-generated method stub
		return authorizationDao.getAuthorizationByaid(authorization);
	}

	@Override
	public Authorization updateAuthorization(Authorization authorization) {
		// TODO Auto-generated method stub
		return authorizationDao.updateAuthorization(authorization);
	}
	
	
}
