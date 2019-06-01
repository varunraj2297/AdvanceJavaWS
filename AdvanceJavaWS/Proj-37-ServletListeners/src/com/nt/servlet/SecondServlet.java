package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	       PrintWriter pw=null;
	       String skill=null;
	       int exp=0;
	       HttpSession ses=null;
	       
	       pw=res.getWriter();
	       res.setContentType("text/html");
	       skill=req.getParameter("skill");
	       exp=Integer.parseInt(req.getParameter("exp"));
	       
	       ses=req.getSession(false);
	       
	       ses.setAttribute("skill",skill);
	       ses.setAttribute("exp",exp);
	       
	       pw.println("<h1 style='color:red;text-align:center'>Naukri.com Registration Page--3</h1>");
	       pw.println("<form action="+res.encodeURL("thirdurl")+" method='post'>");
	       pw.println("Preffered Location::<input type='text' name='loc'><br>");
	       pw.println("Expected Salary::<input type='text' name='expsal'><br>");
	       pw.println("<input type='submit' value='continue'>");
	       
	       pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
