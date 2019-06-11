package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sumurl")
public class AdditionServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null;
		   float a=0.0f,b=0.0f,sum=0.0f;
		   
		   pw=res.getWriter();
		   res.setContentType("text/html");
		   a=Float.parseFloat(req.getParameter("t1"));
		   b=Float.parseFloat(req.getParameter("t2"));
		   sum=a+b;
		   pw.println("<h1 style='color:red;text-align:center'>Result::"+sum+"</h1>");
		   
		   pw.println("<h1 style='color:red;text-align:center'>Request Count::"+req.getSession().getAttribute("count"));
		   pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  		doGet(req, res);
	}
}
