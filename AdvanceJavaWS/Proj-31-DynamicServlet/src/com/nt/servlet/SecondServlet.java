package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null; 
	       pw=res.getWriter();
		   res.setContentType("text/html");
		   
		    pw.println("f1 form data  "+req.getParameter("pname")+"  "+req.getParameter("page")+"  "+req.getParameter("ms")+" <br> ");
		    pw.println("f2 form data  "+req.getParameter("f2t1")+"   "+req.getParameter("f2t2"));
             pw.println("<a href='person_details.html'>home</a>");
		    System.out.println("Second Servlet");
	       pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
