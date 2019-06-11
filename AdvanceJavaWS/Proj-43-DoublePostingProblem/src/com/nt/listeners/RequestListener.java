package com.nt.listeners;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

	private long startTime,endTime;
	
	
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		  ServletContext sc=null;
		  HttpServletRequest req=null;
		    
		  System.out.println("RequestListener.requestDestroyed()");
		    sc=sre.getServletContext();	
			sc.log("response generated time::"+new Date());
			req=(HttpServletRequest) sre.getServletRequest();
			endTime=System.currentTimeMillis();
			
			sc.log("req n res performace "+ req.getRequestURL() +"   "+(endTime-startTime)+" ms");
			
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		    ServletContext sc=null;
		    
		    sc=sre.getServletContext();	
			sc.log("requested time::"+new Date());
			
			startTime=System.currentTimeMillis();
			
	}

	
}
