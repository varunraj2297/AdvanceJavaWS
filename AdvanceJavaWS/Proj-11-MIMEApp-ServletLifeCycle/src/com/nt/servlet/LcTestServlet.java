package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class LcTestServlet extends HttpServlet{
	private static ServletConfig cfg=null;
	static {
		System.out.println("LcTestServlet.enclosing_method()");
	}
	
	public LcTestServlet() {
		System.out.println("LcTestServlet 0-param con");
		
	}
	

	@Override
	public void service(ServletRequest res, ServletResponse req) throws ServletException, IOException {
		System.out.println("LcTestServlet.service()");
		PrintWriter pw=null;
		pw=req.getWriter();
		req.setContentType("text/html");
		pw.println("<h1>"+new Date()+"</h1>");
		pw.println("<a href='hturl'>home</a>");
		System.out.println(this.getServletConfig().getServletName());		 //(best Approach),  will work when you dont override the init(-) of GenericServlet because init(-) contains logic to assign config to class level variable so that getServletConfig() can return config object when we call getServletConfig()
																								//if you override init(-) override getServletConfig() also having logic to return Servlet Config
		//or
		//System.out.println(cfg);	works only when you override init(-) and having initializing Config logic in it as in below overridden method(cfg=config)
		pw.close();
	}

	@Override
	public void destroy() {
		System.out.println("LcTestServlet.destroy()");
	}



	@Override
	public void init() throws ServletException {
		// called by init(ServletConfig config) of 2nd Super class (GenericServlet) 
		//if init(ServletConfig config) is overridden it wont be called to make it execute  , call it explicity from over-ridden method
		System.out.println("LcTestServlet.init()");
		System.out.println(getServletConfig().getInitParameter("email"));
	}

	/*@Override
	public void init(ServletConfig config) throws ServletException {//in this case init() is not called
		System.out.println("LcTestServlet.init(-)");
		System.out.println(config);
		System.out.println(config.getServletName());
		System.out.println(config.getInitParameter("email"));
		cfg=config;
	}*/

}
