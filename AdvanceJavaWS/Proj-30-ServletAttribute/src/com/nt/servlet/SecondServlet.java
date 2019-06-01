package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   // PrintWriter pw=null;
		RequestDispatcher rd=null;
	    String val=null;
	    HttpSession ses=null;
	    
	    res.setContentType("text/html");
	    //pw=res.getWriter();
		
		
		//req.setAttribute("attr1","Val1");
	    ses=req.getSession();
	   // val=(String) ses.getAttribute("attr2");
	    //pw.println("Second Servlet ses attribute"+val);
		rd=req.getRequestDispatcher("/thirdurl");
		rd.forward(req,res);	
		
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
