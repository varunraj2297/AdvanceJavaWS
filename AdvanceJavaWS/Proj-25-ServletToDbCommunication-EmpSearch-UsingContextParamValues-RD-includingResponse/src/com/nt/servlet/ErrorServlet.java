package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/errurl",name="errorurl")
public class ErrorServlet extends HttpServlet {
	   
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
		res.setContentType("text/html");
		RequestDispatcher rd=null;
		pw=res.getWriter();
		
		rd=req.getRequestDispatcher("/headerurl");
		rd.include(req, res);
		pw.println("<h1 style='color:red;text-align:center'>Internal Problem</h1>");
		pw.println("<a href='input.html'>home</a>");
		rd=req.getRequestDispatcher("/footer.html");
		rd.include(req, res);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
