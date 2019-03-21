package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleType2JDBCDriver {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try{
			//registering jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:oci8:@xe","hr","hr");
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement object
			if(con!=null)
				st=con.createStatement();
			
			//preparing sql query
					//SELECT SNO,SNAME,SADD FROM STUDENT
			query="SELECT SNO,SNAME,SADD FROM STUDENT";
			
			//sending and executing query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the result set
			if(rs!=null)
				while(rs.next())
					System.out.println(rs.getInt(1)+"            "+rs.getString(2)+"              "+rs.getString(3));
			
			//knowing class names
			if(rs!=null)
				System.out.println(rs.getClass());
			if(st!=null)
				System.out.println(st.getClass());
			if(con!=null)
				System.out.println(con.getClass());
		
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			//closing jdbc objects
			try{
				if(rs!=null)
					rs.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		
		}//finally
	
	}//main

}//class
