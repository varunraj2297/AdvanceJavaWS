package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/secondurl")
public class SecondServlet extends HttpServlet {
	private static final String INSERT_QUERY="INSERT INTO  cookie_person Values(cookie_pid_seq.nextval,?,?,?,?,?)";
	@Resource(name="DsJndi")
	private DataSource ds;
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		   PrintWriter pw=null; 
		   String name=null,fname=null;
		   int age=0,count=0;
		   float income=0.0f,tax=0.0f;
		   Cookie cookie[]=null,ck4=null,ck5=null;
		   Connection con=null;
		   PreparedStatement ps=null;
		   
		   pw=res.getWriter();
		   res.setContentType("text/html");
		   
		   cookie=req.getCookies();

		     if(cookie!=null) {
		   name=cookie[0].getValue();
		   fname=cookie[1].getValue();
		   age=Integer.parseInt(cookie[2].getValue());
		  }
    		
		   
		   income=Float.parseFloat(req.getParameter("income"));
		   System.out.println(income);
		   tax=Float.parseFloat(req.getParameter("tax"));
		   
		   ck4=new Cookie("c4",String.valueOf(income));
		   ck5=new Cookie("c5",String.valueOf(tax));
		   
		   ck4.setMaxAge(60);
		   ck5.setMaxAge(50);
		   
		   res.addCookie(ck4); res.addCookie(ck5);
		   try {
			con=ds.getConnection();
			ps=con.prepareStatement(INSERT_QUERY);
			
			ps.setString(1,name);
			ps.setString(2,fname );
			ps.setInt(3, age);
			ps.setFloat(4,income);
			ps.setFloat(5, tax);
			
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
		   
	       
		   pw.println("f1 form data  "+name+"  "+age+"  "+fname+" <br> ");
		    pw.println("f2 form data  "+income+"   "+tax);
            pw.println("<a href='person_details.html'>home</a>");

	       pw.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
