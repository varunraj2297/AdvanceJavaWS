package com.nt.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.GenericFilter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class TimeCheckFilter extends GenericFilter implements Filter {
       
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	    Calendar calendar=null;
	    int hours=0;
	    RequestDispatcher rd=null;
	    
	    calendar=Calendar.getInstance();
		hours=calendar.get(Calendar.HOUR_OF_DAY);
		
		if(hours>=12 && hours<=15) {
			//throw new IllegalArgumentException("registration is not allowed between 12pm to 3pm");
		    rd=((HttpServletRequest)req).getRequestDispatcher("error.html");
		    rd.forward(req, res);
		}else
            chain.doFilter(req, res);			
	}
}
