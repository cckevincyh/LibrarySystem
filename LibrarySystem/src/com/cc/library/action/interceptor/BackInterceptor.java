package com.cc.library.action.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.cc.library.domain.Admin;
import com.cc.library.domain.Authorization;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class BackInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		 Map sessionMap = ServletActionContext.getContext().getSession();
		 
		 Object obj = sessionMap.get("admin");
		 if(obj!=null && obj instanceof Admin){
			 Admin admin = (Admin) obj;
			 Authorization authorization = admin.getAuthorization();
			 if(authorization.getBackSet()==1 || authorization.getSuperSet()==1){
				 return invocation.invoke(); 
			 }
		 }
		return "nopass";
	}


}
