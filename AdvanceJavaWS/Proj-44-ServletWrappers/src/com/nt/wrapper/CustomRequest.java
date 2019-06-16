package com.nt.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomRequest extends HttpServletRequestWrapper {

	private HttpServletRequest req;
	public CustomRequest(HttpServletRequest req) {
		super(req);
		this.req=req;
		System.out.println("CustomRequest.CustomRequest()");
	}
	@Override
	public String getParameter(String name) {
		System.out.println("CustomRequest.getParameter()");
		String paramValue=null;
		paramValue=req.getParameter(name);
		if(name.equalsIgnoreCase("uname")) {
			if(!paramValue.endsWith("@gmail.com")) {
				paramValue=paramValue+"@gmail.com";
			    System.out.println("CustomRequest.getParameter()"+paramValue);
			}
		}
		return paramValue;
	}

}
