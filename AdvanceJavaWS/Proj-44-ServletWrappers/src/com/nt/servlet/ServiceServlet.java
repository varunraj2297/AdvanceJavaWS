package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/service")
public class ServiceServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String user=null,pass=null;
		
		pw=res.getWriter();
		res.setContentType("text/html");
		user=req.getParameter("uname");
		System.out.println(user);
		pass=req.getParameter("pwd");
		
		if(user.equalsIgnoreCase("raja@gmail.com") && pass.equalsIgnoreCase("rani")) 
			pw.println("<h1 style='color:green;text-align:center'>Valid Credentials</h1>");
		else
			pw.println("<h1 style='color:red;text-align:center'>InValid Credentials</h1>");
		
		pw.println("<b><a href='login.html'>home</a></b>");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	     doGet(req, res);
	}
	
	
    
}
