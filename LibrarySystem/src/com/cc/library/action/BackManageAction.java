package com.cc.library.action;

import com.cc.library.service.BackService;
import com.opensymphony.xwork2.ActionSupport;

public class BackManageAction extends ActionSupport{

	
	private BackService backService;

	public void setBackService(BackService backService) {
		this.backService = backService;
	}
	
	
	
}
