package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    //PrintWriter pw=null;
		RequestDispatcher rd=null;
		HttpSession ses=null;
		ServletContext sc=null;
	  //  String val=null;
		
	    res.setContentType("text/html");
	  // pw=res.getWriter();
		
	
		req.setAttribute("attr1","Val1req");
	    ses=req.getSession();
		ses.setAttribute("attr2", "Val2ses");
		sc=req.getServletContext();
		sc.setAttribute("attr3", "Val3sc");
		
		//val=(String) ses.getAttribute("attr2");
		//pw.println("First Servlet ses attribute"+val);
		rd=req.getRequestDispatcher("/secondurl");
		rd.forward(req,res);	
		
		
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
