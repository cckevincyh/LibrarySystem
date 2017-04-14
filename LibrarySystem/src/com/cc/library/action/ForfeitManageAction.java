package com.cc.library.action;

import com.cc.library.service.ForfeitService;
import com.opensymphony.xwork2.ActionSupport;

public class ForfeitManageAction extends ActionSupport{

	private ForfeitService forfeitService;

	public void setForfeitService(ForfeitService forfeitService) {
		this.forfeitService = forfeitService;
	}
	
	
	
}
