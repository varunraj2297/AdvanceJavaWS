package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchurl")
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//	PrintWriter pw=null;
		String url=null,engine=null,searchString=null;
		res.setContentType("text/html");
		
		searchString=req.getParameter("ss");
		engine=req.getParameter("engine");
	
		if(engine.equalsIgnoreCase("google")) 
			url="https://www.google.com/search?q="+searchString;
		else if(engine.equalsIgnoreCase("yahoo"))
			url="https://in.search.yahoo.com/search?p="+searchString;
		else
			url="https://www.bing.com/search?q="+searchString;
		
		 res.sendRedirect(url);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
