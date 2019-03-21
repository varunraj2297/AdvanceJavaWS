package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoterServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,sage=null;
		int age=0;
		
		res.setContentType("text/html");
		pw=res.getWriter();
		name=req.getParameter("pname");
		sage=req.getParameter("page");
		age=Integer.parseInt(sage);
		
		if(age>18)
			pw.println("<h1 style='color:green;text-align:center'>Mr/Miss/Mrs."+name+", You are Eligible to vote</h1>");
		else
			pw.println("<h1 style='color:red;text-align:center'>Mr/Miss/Mrs."+name+", You are not Eligible to vote wait for "+ (18-age) +"  years </h1>");

		pw.println("<a href='input.html'><img src='home.jpg'/><a>");
		pw.close();
	}
	
}
