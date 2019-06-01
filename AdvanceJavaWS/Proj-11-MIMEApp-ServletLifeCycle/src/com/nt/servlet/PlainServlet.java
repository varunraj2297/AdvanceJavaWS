package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class PlainServlet extends HttpServlet{
	static {
		System.out.println("PlainServlet.enclosing_method()");
	}
	
	public PlainServlet() {
		System.out.println("PlainServlet 0-param con");
	}

	@Override
	public void destroy() {
		System.out.println("PlainServlet.destroy()");
	}




	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("PlainServlet.init(-)");
	}

	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		System.out.println("PlainServlet.service()");
		PrintWriter pw=null;
		res.setContentType("text/plain");
		pw=res.getWriter();
		pw.println("<table border='1' style='background-color:cyan;text-align:center'>");
		pw.println("<tr><th>sno</th><th>Cricketer</th><th>role</th></tr>");
		pw.println("<tr><td>1</td><td>Dhoni</td><td>Captain</td></tr>");
		pw.println("<tr><td>2</td><td>Rohit</td><td>Batsmen</td></tr>");
		pw.println("<tr><td>3</td><td>Dhawan</td><td>Batsmen</td></tr>");
		pw.println("<tr><td>4</td><td>Virat</td><td>Batsman</td></tr>");
		pw.println("<tr><td>5</td><td>Ashwin</td><td>SpinBowler</td></tr>");
		pw.println("<tr><td>6</td><td>Bhumrah</td><td>FastBowler</td></tr>");
		pw.println("</table>");
		pw.close();
	}
}