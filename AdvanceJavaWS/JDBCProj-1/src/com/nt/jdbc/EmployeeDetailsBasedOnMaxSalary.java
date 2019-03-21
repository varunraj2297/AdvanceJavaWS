package com.nt.jdbc;

/*
 program to display employee details who is having highest salary
 Author			:	Team-A
 Version 		:	1.0 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDetailsBasedOnMaxSalary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		
		try{
			//registering jdbc driver software
	
			Class.forName("oracle.jdbc.OracleDriver");
			
			//establishing the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//preparing sql query
					//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)
			query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
		
			//sending and executing the query
			
			if(st!=null)
				rs=st.executeQuery(query);
				
			//processing the ResultSet
			if(rs!=null){
				while(rs.next())
				{
					flag=true;
					System.out.println(rs.getInt(1)+"		"+rs.getString(2)+"			"+rs.getString(3)+"			"+rs.getInt(4));
			
				}
			}
			if(flag)
				System.out.println("Result Found and Displayed");
			else
				System.out.println("No records Found");
			
			
			
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
		
		//closing all jdbc objs
		finally{
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

/*output:
7839		KING			PRESIDENT			5000
Result Found and Displayed
*/