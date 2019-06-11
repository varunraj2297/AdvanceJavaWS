package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BrowserFilter extends GenericFilter {
	
	static {
		System.out.println("BrowserFilter.static block");
	}
	
	public BrowserFilter() {
		System.out.println("BrowserFilter.BrowserFilter()-0-param constructor");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("BrowserFilter.init(FilterConfig)");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		PrintWriter pw=null;
		String brName=null;
		System.out.println("BrowserFilter.doFilter(-,-,-)");
		pw=res.getWriter();
		res.setContentType("text/html");
		brName=((HttpServletRequest)req).getHeader("user-agent");
		if(brName.contains("Chrome")) {
			System.out.println("chain.doFilter(-,-)-before");
			chain.doFilter(req, res);
			System.out.println("chain.doFilter(-,-)-after");
		}
		else {
			System.out.println("Send request from Chrome Browser");
			pw.println("<h1 style='color:red;text-align:center'>Send request from Chrome Browser</h1>");
			return;
		}
		
	}

	
	@Override
	public void destroy() {
		System.out.println("BrowserFilter.destroy()");
	}
}
