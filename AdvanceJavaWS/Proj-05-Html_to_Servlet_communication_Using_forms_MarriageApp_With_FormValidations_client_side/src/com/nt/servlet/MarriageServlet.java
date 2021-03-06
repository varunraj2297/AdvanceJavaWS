package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MarriageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		String name=null,tage=null,gender=null,status=null;
		int age=0;
		
		res.setContentType("text/html");
		
		name=req.getParameter("name");
		tage=req.getParameter("age");
		gender=req.getParameter("gender");
		status=req.getParameter("vflag");
		pw=res.getWriter();
		
	if(status.equalsIgnoreCase("no")) {
	 List<String> errList=null;
	
		errList=new ArrayList<String>();
	System.out.println("Server side validation is taking place..");
		if(name.equals("")||name.length()==0||name==null) {
			errList.add("name is required");
			
			//  pw.println("<h1 style='color:red;text-align:center'>name is required</h1>");
			 // return;
			 
		}
		
		if(tage.equals("")||tage.length()==0||tage==null) {
			errList.add("age is required");
			
			//  pw.println("<h1 style='color:red;text-align:center'>age is required</h1>");
			 // return;
			 
		}
		else {
		
			try {
				age=Integer.parseInt(tage);
				if(age<=0||age>125){
					errList.add("age must be in the range of 1 to 125");
					
					//  pw.println("<h1 style='color:red;text-align:center'>age must be in the range of 1 to 125</h1>" );
					//   return;
					 
				}
			}
			catch(NumberFormatException nfe) {
				errList.add("age must be a numerical value");
				
				//  pw.println("<h1 style='color:red;text-align:center'>age must be a numerical value</h1>");
				  // return;
				 
			}
		}
		
		if(errList.size()>0) {
			for(String msg:errList) {
				pw.println("<h1 style='color:red;text-align:center'>"+msg+"</h1><br>");
			}
			return;
		}
	
	}
	else {
		age=Integer.parseInt(tage);
	}
		
		if(gender.equalsIgnoreCase("M")&&age>21||gender.equalsIgnoreCase("F")&&age>18) {
			if(gender.equalsIgnoreCase("M"))
				pw.println("<h1 style='color:green;text-align:center'>Mr."+name+" You are Eligible for Marriage</h1>");
			else
				pw.println("<h1 style='color:green;text-align:center'>Miss."+name+" You are Eligible for Marriage</h1>");
		}
		else {
			if(gender.equalsIgnoreCase("F"))
				pw.println("<h1 style='color:red;text-align:center'>Sorry Miss."+name+" You are Not Eligible for Marriage , wait for "+(18-age)+" years</h1>");
			else
				pw.println("<h1 style='color:red;text-align:center'>Sorry Mr."+name+" You are Not Eligible for Marriage , wait for "+(21-age)+" years</h1>");
		}
		
		pw.println("<a href='input.html'><img src='home.jpg'></a>");
		pw.close();
	
	}
	
}
