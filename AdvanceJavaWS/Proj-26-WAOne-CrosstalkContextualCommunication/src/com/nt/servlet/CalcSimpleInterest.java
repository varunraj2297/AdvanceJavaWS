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


@WebServlet("/simpleurl")
public class CalcSimpleInterest extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter pw=null;
			response.setContentType("text/html");
			pw=response.getWriter();
			RequestDispatcher rd=null;
			ServletContext sc=null,fc=null;
			float principle=0.0f,rate=0.0f,time=0.0f,si=0.0f;
			
			principle=Float.parseFloat(request.getParameter("principle"));
			rate=Float.parseFloat(request.getParameter("rate"));
			time=Float.parseFloat(request.getParameter("time"));
			
			si=(float)(principle*rate*time)/100;
			pw.println("<b>SI::"+si+" </b>");
			
			sc=getServletContext();
			fc=sc.getContext("/Proj-27-WATwo-CrosstalkContextualCommunication");
			rd=fc.getRequestDispatcher("/compurl");
			rd.include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}