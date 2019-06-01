package com.nt.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ExcelServlet extends HttpServlet{
	static {
		System.out.println("ExcelServlet.enclosing_method()");
	}
	
	public ExcelServlet() {
		System.out.println("ExcelServlet 0-param con");
	}
	
	
	

	@Override
	public void destroy() {
		System.out.println("ExcelServlet.destroy()");
	}




	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("ExcelServlet.init(-)");
	}




	protected void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		System.out.println("ExcelServlet.service()");
		PrintWriter pw=null;
		res.setContentType("application/vnd.ms-excel");
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