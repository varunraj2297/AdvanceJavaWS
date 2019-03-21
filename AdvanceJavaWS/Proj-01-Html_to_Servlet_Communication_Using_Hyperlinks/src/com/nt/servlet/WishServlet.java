package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class WishServlet extends HttpServlet{
	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		PrintWriter pw=null;
		pw=res.getWriter();
		Calendar calendar=null;
		int hours=0;
		res.setContentType("text/html");
		calendar=Calendar.getInstance();
		hours=calendar.get(Calendar.HOUR_OF_DAY);
		
		if(hours<12)
			pw.println("<h1 style='color:red;text-align:center'>Good Morning</h1>");
		else if(hours<16)
			pw.println("<h1 style='color:red;text-align:center'>Good AfterNoon</h1>");
		else if(hours<20)
			pw.println("<h1 style='color:red;text-align:center'>Good Evening</h1>");
		else
			pw.println("<h1 style='color:red;text-align:center'>Good Night</h1>");
		
		pw.println("<a href='http://localhost:2525/Proj-01-Html_to_Servlet_Communication_Using_Hyperlinks/page.html'>Go to home</a>");

		pw.close();
	
	}
}