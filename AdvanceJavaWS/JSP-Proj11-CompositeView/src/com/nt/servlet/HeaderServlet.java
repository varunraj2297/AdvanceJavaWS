package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/headerurl")
public class HeaderServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        PrintWriter pw=null;
	        res.setContentType("text/html");
	        pw=res.getWriter();
	        
	        pw.println("<h1 style='color:red;text-align:center'>THE HINDU</h1>");
	        pw.println("<marquee><b>Srinivas from hyderabad got 1st rank in IIT</b></marquee>");
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
