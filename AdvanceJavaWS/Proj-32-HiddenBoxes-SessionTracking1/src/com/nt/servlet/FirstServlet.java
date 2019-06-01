package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/firsturl")
public class FirstServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	      PrintWriter pw=null; 
	      pw=res.getWriter();
		 res.setContentType("text/html");
	      String ms=null;
	      ms=req.getParameter("ms");
	      
	      if(ms.equalsIgnoreCase("married")) {
	    	  pw.println("<h1  style='color:red;text-align: center'>Marital Details</h1>");
	    	  pw.println("<form action='secondurl' method='post'> ");
	    	  pw.println("Spouse name::<input type='text' name='f2t1'><br>");
	    	  pw.println("no of kids::<input type='text' name='f2t2'><br>");
	    	  pw.println("<input type='hidden' name='hname' value='"+req.getParameter("pname")+"'>");
	    	  pw.println("<input type='hidden' name='hage' value='"+req.getParameter("page")+"'>");
	    	  pw.println("<input type='hidden' name='hms' value='"+ms+"'>");
	    	  pw.println("<input type='submit' value='submit'>");
	    	  pw.println("</form>");
	      }
	      else {
	    	  pw.println("<h1  style='color:red;text-align: center'>Bacholer Details</h1>");
	    	  pw.println("<form action='secondurl' method='post'> ");
	    	  pw.println("When do you what to marry::<input type='text' name='f2t1'><br>");
	    	  pw.println("why do you want to marry::<input type='text' name='f2t2'><br>");
	    	  pw.println("<input type='hidden' name='hname' value='"+req.getParameter("pname")+"'>");
	    	  pw.println("<input type='hidden' name='hage' value='"+req.getParameter("page")+"'>");
	    	  pw.println("<input type='hidden' name='hms' value='"+ms+"'>");
	    	  pw.println("<input type='submit' value='submit'>");
	    	  pw.println("</form>");
	      }
	      
	      System.out.println("first servlet");
	      pw.close();
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
