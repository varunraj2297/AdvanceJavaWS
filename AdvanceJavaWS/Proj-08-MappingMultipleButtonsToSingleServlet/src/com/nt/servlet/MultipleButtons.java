package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultipleButtons extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String element=null;
		int value1=0,value2=0;
		//general settings
		res.setContentType("text/html");
		pw=res.getWriter();
		element=req.getParameter("s");
		System.out.println(element);
		
		if(!element.equalsIgnoreCase("sysdate")&&!element.equalsIgnoreCase("props")) {
			value1=Integer.parseInt(req.getParameter("t1"));
			value2=Integer.parseInt(req.getParameter("t2"));
		}
		
		if(element.equalsIgnoreCase("add"))
			pw.println("<h1>Addition of two numbers is::"+(value1+value2)+"</h1>");
		else if(element.equalsIgnoreCase("sub"))
			pw.println("<h1>Subtraction of two numbers is::"+(value1-value2)+"</h1>");
		else if(element.equalsIgnoreCase("mul"))
			pw.println("<h1>Multiplication of two numbers is::"+(value1*value2)+"</h1>");
		else if(element.equalsIgnoreCase("div") && value2!=0) 
			pw.println("<h1>Division of two numbers is::"+((float)value1/value2)+"<h1>");
		else if(element.equalsIgnoreCase("sysdate"))
			pw.println("<h1>Today's Date::"+new Date()+"</h1>");
		else
			pw.println("<h1> System Properties are::<br>"+System.getProperties()+"</h1>");
	
		pw.println("<a href='input.html'>home</a>");
		
		pw.close();
		
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
