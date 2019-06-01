package com.nt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.EmployeeDTO;
import com.nt.service.EmployeeService;
import com.nt.service.EmployeeServiceImpl;

public class MainController extends HttpServlet {
	private EmployeeService service=null;

	@Override
	public void init() throws ServletException {
		System.out.println("MainController.init()");
		service=new EmployeeServiceImpl();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw=null;
			res.setContentType("text/html");
			pw=res.getWriter();
			int sal=0;
			String ename=null,addrs=null,desg=null,result=null;
			EmployeeDTO dto=null;
			
		
			ename=req.getParameter("ename");
			addrs=req.getParameter("addrs");
			desg=req.getParameter("desg");
			sal=Integer.parseInt(req.getParameter("sal"));

			dto=new EmployeeDTO();
			
			dto.setEname(ename);
			dto.setAddrs(addrs);
			dto.setDesg(desg);
			dto.setSal(sal);

			result=service.calculateGrossnNetSalary(dto);
			
			pw.println("<h1 style='text-align:center;color:blue'>"+result+"</h1>");
			pw.println("<br><br><a href='input.html'>Home</a>");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("MainController.destroy()");
		service=null;
	}
		
}
