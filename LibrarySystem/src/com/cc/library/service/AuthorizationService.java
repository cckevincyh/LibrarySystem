package com.cc.library.service;

import com.cc.library.domain.Authorization;

public interface AuthorizationService {

	
	public boolean addAuthorization(Authorization authorization);

	public Authorization getAuthorizationByaid(Authorization authorization);

	public Authorization updateAuthorization(Authorization authorization);
}
