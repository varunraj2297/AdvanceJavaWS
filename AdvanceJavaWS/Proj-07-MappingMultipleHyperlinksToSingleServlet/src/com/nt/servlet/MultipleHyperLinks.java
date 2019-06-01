package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MultipleHyperLinks extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Locale locales[];
		String link;
		//general settings
		res.setContentType("text/html");
		pw=res.getWriter();
		link=req.getParameter("p");
		locales=Locale.getAvailableLocales();
		
		if(link.equalsIgnoreCase("lang")) {
			pw.println("Languages ::<br>");
			for(Locale l:locales)
				pw.println(l.getDisplayLanguage()+"<br>");
		}
		else if(link.equalsIgnoreCase("con")) {
			pw.println("Countries ::<br>");
			for(Locale l:locales)
				pw.println(l.getDisplayCountry()+"<br>");
		}
		else
			pw.println(new Date()+"<br>");
		
		pw.println("<a href='input.html'>home</a>");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
}
