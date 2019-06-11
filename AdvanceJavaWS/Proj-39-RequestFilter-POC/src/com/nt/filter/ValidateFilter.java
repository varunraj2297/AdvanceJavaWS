package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ValidateFilter implements Filter {
	
	static {
		System.out.println("ValidateFilter.static block");
	}

	public ValidateFilter() {
        System.out.println("ValidateFilter.ValidateFilter()-0-param constructor");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("ValidateFilter.init(FilterConfig)");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		     PrintWriter pw=null;
		     float a=0.0f,b=0.0f;
		     
		     pw=res.getWriter();
		     res.setContentType("text/html");
		     a=Float.parseFloat(req.getParameter("t1"));
		     b=Float.parseFloat(req.getParameter("t2"));
		     System.out.println("ValidateFilter.doFilter(-,-,-)");
		     
		     if(a<=0 || b<=0) {
		    	System.out.println("Provide valid I/p's");
		    	 pw.println("<h1 style='color:red;text-align:center'>Provide valid I/p's</h1>");
		         return;
		     }
		     else {
		    	 System.out.println("chain.doFilter(-,-)-before");
		    	 chain.doFilter(req, res);
		    	 System.out.println("chain.doFilter(-,-)-after");
		     }
	}
	
	@Override
 	public void destroy() {
 		System.out.println("ValidateFilter.destroy()");
 	}

}
