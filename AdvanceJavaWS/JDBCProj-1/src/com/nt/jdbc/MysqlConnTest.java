package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnTest {

	public static void main(String[] args) {
		Connection con =null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			//registering jdbc driver
		//	Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/varun","root","Raj*2297");
		
			//creating statement object
			if(con!=null)
				st=con.createStatement();
			//sending and executing query
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT");
			//processing the result set
			if(rs!=null)
				while(rs.next())
					System.out.println(rs.getString(1)+" 		"+rs.getString(2)+" 		"+rs.getString(3));
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//closing jdbc objects
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
