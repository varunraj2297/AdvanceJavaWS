package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@WebFilter("/*")
public class PerformanceMonitoringFilter implements Filter {

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		        PrintWriter pw=null;
		        HttpServletRequest hreq=null;
		        long start=0,end=0;
		        ServletContext sc=null;
		        
		        hreq=((HttpServletRequest)req);
		        sc=hreq.getServletContext();
		        res.setContentType("text/html");
		        pw=res.getWriter();
		        
		        start=System.currentTimeMillis();
		       	 chain.doFilter(req, res);	
		       	 end=System.currentTimeMillis();
		       	
		       	 sc.log(hreq.getRequestURL()+"has taken "+(end-start)+" ms to process the request");
		       	
		       	 pw.println(hreq.getRequestURL()+"has taken "+(end-start)+" ms to process the request");
		     }
}
