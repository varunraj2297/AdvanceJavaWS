package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	private static final String INSERT_QUERY="INSERT INTO  HIDDEN_BOX_SESSION_TRACKING VALUES(PID_SEQ1.NEXTVAL,?,?,?,?,?)";
	@Resource(name="DsJndi")
	private DataSource ds;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null; 
		   String name=null,ms=null,f2t1=null,f2t2=null;
		   int age=0,count=0;
		   Connection con=null;
		   PreparedStatement ps=null;
		   
		   pw=res.getWriter();
		   res.setContentType("text/html");
		   
		   name= req.getParameter("hname");
		   age=Integer.parseInt(req.getParameter("hage"));
		   ms=req.getParameter("hms");
		   
		   f2t1=req.getParameter("f2t1");
		   f2t2=req.getParameter("f2t2");
		   
		   try {
			con=ds.getConnection();
			ps=con.prepareStatement(INSERT_QUERY);
			
			ps.setString(1,name);
			ps.setInt(2, age);
			ps.setString(3, ms);
			ps.setString(4, f2t1);
			ps.setString(5, f2t2);
			
			count=ps.executeUpdate();
			if(count==1)
				System.out.println("Registration Succeded");
			else
				System.out.println("Registration Failed");
			
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		   catch (Exception e) {
			e.printStackTrace();
		}
		   
		   finally {
			   try {
			    if(ps!=null)
			    	ps.close();
			   }catch (SQLException se) {
				   se.printStackTrace();
				   System.out.println("Internal problem");
			}
			   
			   try {
				    if(con!=null)
				    	con.close();
				   }catch (SQLException se) {
					   se.printStackTrace();
					   System.out.println("Internal problem");
				}
		}
		   
	       
		   pw.println("f1 form data  "+name+"  "+age+"  "+ms+" <br> ");
		    pw.println("f2 form data  "+f2t1+"   "+f2t2);
            pw.println("<a href='person_details.html'>home</a>");
		    System.out.println("Second Servlet");
	       pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
