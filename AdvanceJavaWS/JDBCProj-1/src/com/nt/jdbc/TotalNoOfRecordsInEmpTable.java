package com.nt.jdbc;

/*
program to display total number of records in a table
Author			:	Team-A
Version 		:	1.0 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TotalNoOfRecordsInEmpTable {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		// TODO Auto-generated method stub
		try{
			//registering jdbc driver software
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			//establishing the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//preparing the query
			query="SELECT COUNT(*) FROM EMPLOYEES";
			System.out.println(query);
	
			//send and execute the query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet
			if(rs!=null){
				rs.next();
				System.out.println("Total number of records are "+rs.getInt(1));
			}
		}//try
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
			//closing all jdbc objs
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

/*   output:
SELECT COUNT(*) FROM EMPLOYEES
Total number of records are 107
*/