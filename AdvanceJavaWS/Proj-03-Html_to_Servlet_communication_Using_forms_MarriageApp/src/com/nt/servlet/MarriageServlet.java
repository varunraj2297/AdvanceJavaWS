package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarriageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null;
		int age=0;
		char gender=0;

		res.setContentType("text/html");
		
		name=req.getParameter("name");
		age=Integer.parseInt(req.getParameter("age"));
		gender=req.getParameter("gender").charAt(0);
		pw=res.getWriter();
			
		if(gender=='M'&&age>21||gender=='F'&&age>18) {
			if(gender=='M')
				pw.println("<h1 style='color:green;text-align:center'>Mr."+name+" You are Eligible for Marriage</h1>");
			else
				pw.println("<h1 style='color:green;text-align:center'>Miss."+name+" You are Eligible for Marriage</h1>");
		}
		else {
			if(gender=='F')
				pw.println("<h1 style='color:red;text-align:center'>Sorry Miss."+name+" You are Not Eligible for Marriage , wait for "+(18-age)+" years</h1>");
			else
				pw.println("<h1 style='color:red;text-align:center'>Sorry Mr."+name+" You are Not Eligible for Marriage , wait for "+(21-age)+" years</h1>");
		}
		
		pw.println("<a href='input.html'><img src='home.jpg'></a>");
		pw.close();
	
	}
	
}
