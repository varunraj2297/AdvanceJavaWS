package com.nt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.wrapper.CustomRequest;
import com.nt.wrapper.CustomResponse;

@WebFilter("/service")
public class MyFilter extends GenericFilter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
                System.out.println("MyFilter.doFilter()");
				CustomRequest creq = null;
				PrintWriter pw=null;
				String output=null;
				CustomResponse cres = null;
				
                res.setContentType("text/html");
		    	creq = new CustomRequest((HttpServletRequest) req);
		    	cres=new CustomResponse((HttpServletResponse)res);
		    	
		    	chain.doFilter(creq, cres);
		    
		    	output=cres.toString();
		    	
		    	pw=res.getWriter();
		        pw.println(cres+"<br>....from hyd");
		        System.out.println(pw);
		    	pw.close();
	}

}
