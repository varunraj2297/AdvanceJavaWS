package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ConnPoolServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con=null;
		PreparedStatement ps=null;
		PrintWriter pw=null;
		String tabName=null;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		ResultSet rs=null;
		
		pw=res.getWriter();
		res.setContentType("text/html");
		tabName=req.getParameter("table");
		try {
			con=makeConnection();
			ps=con.prepareStatement("select * from "+tabName);
			rsmd=ps.getMetaData();
			colCount=rsmd.getColumnCount();
			rs=ps.executeQuery();
			pw.println("<table border=1  align='center'>");
			pw.println("<tr bgcolor='cyan'>");
			for(int i=1;i<=colCount;i++) {
				pw.println("<td>"+rsmd.getColumnLabel(i)+"</td>");
			}
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr bgcolor='orange'>");
				for(int i=1;i<=colCount;i++) {
					pw.println("<td>"+rs.getString(1));
				}
				pw.println("</tr>");
			}
			pw.println("</table>");
			
			pw.println("<h1><a href='input.html'>home</a></h1>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(pw!=null) {
				try {
					pw.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
	
	private Connection makeConnection() {
		Connection con=null;
		InitialContext ic=null;
		DataSource ds=null;
		try {
			ic=new InitialContext();
			ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
			con=ds.getConnection();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}

}
