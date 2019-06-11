package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdditionServlet extends HttpServlet {
	
	static {
		System.out.println("AddtionServlet.static block");
	}

	public AdditionServlet() {
		System.out.println("AddtionServlet.AddtionServlet()-0-param constructor");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		   System.out.println("AddtionServlet.init(ServletConfig)");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null;
		   float a=0.0f,b=0.0f,sum=0.0f;
		   
		   System.out.println("AddtionServlet.doGet(-,-)");
		   pw=res.getWriter();
		   res.setContentType("text/html");
		   a=Float.parseFloat(req.getParameter("t1"));
		   b=Float.parseFloat(req.getParameter("t2"));
		   sum=a+b;
		   pw.println("<h1 style='color:red;text-align:center'>Result::"+sum+"</h1>");
		   pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    System.out.println("AddtionServlet.doPost(-,-)");
		doGet(req, res);
	}

	@Override
	public void destroy() {
	     System.out.println("AddtionServlet.destroy()");
	}
}
