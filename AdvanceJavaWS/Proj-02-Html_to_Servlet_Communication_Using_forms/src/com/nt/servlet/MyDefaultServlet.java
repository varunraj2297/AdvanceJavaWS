package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyDefaultServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		
		res.setContentType("text/html");
		pw=res.getWriter();
		pw.println("<h1 style='color:red;text-align:center'>Page not found,<br>Wrong url. Please type correct url</h1>");
		pw.println("<a  href='input.html' style='font-size:40px;text-align:center'>http://localhost:2525/Proj-02-Html_to_Servlet_Communication_Using_forms/input.html</a>");
		pw.close();
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
}
