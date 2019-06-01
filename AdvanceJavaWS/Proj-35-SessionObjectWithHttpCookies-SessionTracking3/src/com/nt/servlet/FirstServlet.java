package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	       PrintWriter pw=null;
	       String name=null,addrs=null;
	       int age=0;
	       HttpSession ses=null;
	       
	       pw=res.getWriter();
	       res.setContentType("text/html");
	       name=req.getParameter("name");
	       age=Integer.parseInt(req.getParameter("age"));
	       addrs=req.getParameter("addrs");
	       
	       ses=req.getSession(true);
	       
	       ses.setAttribute("name",name);
	       ses.setAttribute("age",age);
	       ses.setAttribute("addrs",addrs);
	       
	       pw.println("<h1 style='color:red;text-align:center'>Naukri.com Registration Page--2</h1>");
	       pw.println("<form action='secondurl' method='post'>");
	       pw.println("Skill::<input type='text' name='skill'><br>");
	       pw.println("Experiance::<input type='text' name='exp'><br>");
	       pw.println("<input type='submit' value='continue'>");
	       
	       pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
