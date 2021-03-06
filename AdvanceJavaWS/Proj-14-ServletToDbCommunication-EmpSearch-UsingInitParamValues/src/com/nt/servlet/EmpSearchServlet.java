package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EmpSearchServlet extends HttpServlet {
public static String EMP_SEARCH="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE EMPNO=?";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con=null;
		PreparedStatement ps=null;
		ServletConfig cg=null;
		int empno=0;
		String driver=null,url=null,dbuser=null,dbpwd=null;
		
		ResultSet rs=null;
		PrintWriter pw=null;
		
		try {
			
			pw=res.getWriter();
			res.setContentType("text/html");
			cg=getServletConfig();
			
			driver=cg.getInitParameter("driver");
			url=cg.getInitParameter("url");
			dbuser=cg.getInitParameter("dbuser");
			dbpwd=cg.getInitParameter("dbpwd");
			
			
			
			Class.forName(driver);
			con=DriverManager.getConnection(url,dbuser,dbpwd);
			
			ps=con.prepareStatement(EMP_SEARCH);
			
			empno=Integer.parseInt(req.getParameter("empno"));
			
			ps.setInt(1, empno);
			
			rs=ps.executeQuery();
			
			
			if(rs.next())
				pw.println("<h1>"+rs.getInt(1)+"    "+rs.getString(2)+"     "+rs.getString(3)+"      "+rs.getInt(4)+"</h1>");
			else
				pw.println("<h1 style='color:red;text-align:center'>Record Not Found</h1>");
			
			System.out.println("Servlet config obj class::"+cg.getClass());
		
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(rs!=null)
					rs.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}catch (SQLException se) {
				se.printStackTrace();
			}
			
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	
}
