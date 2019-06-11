package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@WebFilter("/*")
public class RequestCountFilter implements Filter {
	int count=0;
	HttpSession ses=null;
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		         count++;
		         ses=((HttpServletRequest)req).getSession();
		         ses.setAttribute("count",count);
		       	 chain.doFilter(req, res);	
		     }
}
