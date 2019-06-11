package com.nt.jdbc;

/*
program to display employee details based on passing Employee id
Author			:	Team-A
Version 		:	1.0 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeDetailsBasedOnEmpNoUsingPreparedStatement {
private static final String EMP_DETAILS_BASED_ON_ID="SELECT * FROM EMP WHERE EMPNO=?";
	public static void main(String[] args) {
		
		Scanner sc=null;
		int empno=0;
		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		boolean flag=false;
		
		try{
			
			//registering jdbc driver class
			Class.forName("oracle.jdbc.OracleDriver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(EMP_DETAILS_BASED_ON_ID);
		
			//setting inputs to query params
			if(ps!=null){
				//reading inputs
				sc=new Scanner(System.in);
				
				do{
					if(sc!=null){
						System.out.println("Enter empno:");
						empno=sc.nextInt();
					}
				
					ps.setInt(1, empno);
					//sending and executing the query
						rs=ps.executeQuery();
					
					//processing the result set
					if(rs!=null)
						if(rs.next())
						{
							flag=true;
							System.out.println(rs.getInt(1)+"		"+rs.getString(2)+"			"+rs.getString(3)+"			"+rs.getString(4)+"			"+rs.getString(5)+"			"+rs.getString(6)+"			"+rs.getString(7)+"			"+rs.getString(8));
						}
					if(empno!=-1){
						if(flag){
							System.out.println("Results are found and displayed");
							flag=false;
						}
						else
							System.out.println("Results not found");
					}
				}while(empno!=-1);
			}
				
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
		//closing all jdbc objs
		finally{
			try{
				if(rs!=null)
					rs.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(ps!=null)
					ps.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(sc!=null)
					sc.close();
			}catch(Exception e){
				e.printStackTrace();
			}	
		}//finally
	}//main
}//class