package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/fourthurl")
public class FourthServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    PrintWriter pw=null;
		//RequestDispatcher rd=null;
	    String val=null,val2=null,val3=null;
	   HttpSession ses=null;
	   ServletContext sc=null;
	    
	    res.setContentType("text/html");
		pw=res.getWriter();
	    ses=req.getSession();
	    sc=req.getServletContext();
		 
		
		val=(String) req.getAttribute("attr1");
		val2=(String) ses.getAttribute("attr2");
		val3=(String)sc.getAttribute("attr3");
		
    	pw.println("Fourth Servlet req attribute value::"+val);
    	pw.println("Fourth Servlet ses attribute value::"+val2); //to get this first run firstservlet
        pw.println("Fourth Servlet sc attribute value::"+val3);  //to get this 1st run 1st servlet
    	
		pw.close();
		//req.setAttribute("attr1","Val1");
		//rd=req.getRequestDispatcher("/thirdurl");
		//rd.forward(req,res);	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
