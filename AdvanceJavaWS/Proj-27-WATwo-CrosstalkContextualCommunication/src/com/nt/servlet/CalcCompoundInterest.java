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

@WebServlet("/compurl")
public class CalcCompoundInterest extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		response.setContentType("text/html");
		pw=response.getWriter();
		float principle=0.0f,rate=0.0f,time=0.0f,ci=0.0f;
		
		principle=Float.parseFloat(request.getParameter("principle"));
		rate=Float.parseFloat(request.getParameter("rate"));
		time=Float.parseFloat(request.getParameter("time"));
		
		ci=(float) (principle*(Math.pow((1+(rate/100)), time))-principle);
		pw.println("<b>CI::"+ci+" </b>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}