package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		Random random=null;
		int bal=0;
		
		pw=res.getWriter();
		res.setContentType("text/html");
		random=new Random();
		bal=random.nextInt(1000000);
		pw.println("<h1  style='color:red;text-align:center'>Balance::"+bal+"</h1>");
		pw.println("<br> <a href='home.html'>home </a>");
		pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req, res);
	}
	
	

}
