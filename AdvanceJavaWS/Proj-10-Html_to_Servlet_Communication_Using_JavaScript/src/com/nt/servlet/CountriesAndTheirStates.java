package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountriesAndTheirStates extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		int index=0;
		String countryStates[][]= {{"Hyderabad","Mumbai","Chennai","Kolkata"},
														{"Islamabad","Karachi","Lahore","Balochistan"},
														{"New South Wales","Victoria","Melbourne","Perth"},
														{"California","Texas","Florida","Washington DC"}
														};
		res.setContentType("text/html");
		pw=res.getWriter();
		index=Integer.parseInt(req.getParameter("country"));
		String[] states=countryStates[index];
		pw.println("<h1>Country and its States</h1><br>");
		for(String s:states) {
			pw.println("<b>"+s+"</b><br>");
		}
		pw.println("<a href='input.html'>home</a>");
		pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
