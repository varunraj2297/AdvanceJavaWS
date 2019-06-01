package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	      PrintWriter pw=null;
	      String name=null,fname=null;
	      int age=0;
	      
	      Cookie ck1=null,ck2=null,ck3=null;
	      
	      pw=res.getWriter();
		 res.setContentType("text/html");
		 
		 name=req.getParameter("pname");
		 fname=req.getParameter("fname");
		 age=Integer.parseInt(req.getParameter("page"));
		 
		 ck1=new Cookie("c1",name);
	      ck2=new Cookie("c2",fname);
	      ck3=new Cookie("c3",String.valueOf(age));
	      
	      ck1.setMaxAge(70);
	      ck2.setMaxAge(80);
	      ck3.setMaxAge(90);
	      
	     
	      res.addCookie(ck1);   res.addCookie(ck2);  res.addCookie(ck3);
		 
	    	  pw.println("<h1  style='color:red;text-align: center'>Income tax</h1>");
	    	  pw.println("<form action='secondurl' method='post'> ");
	    	  pw.println("Income::<input type='text' name='income'><br>");
	    	  pw.println("Tax::<input type='text' name='tax'><br>");
	    	  pw.println("<input type='submit' value='submit'>");
	    	  pw.println("</form>");
	      	      
	      pw.close();
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
