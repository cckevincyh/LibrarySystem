package com.cc.library.dao;

import com.cc.library.domain.Authorization;

public interface AuthorizationDao {

	public boolean addAuthorization(Authorization authorization);

	public Authorization getAuthorizationByaid(Authorization authorization);

}
