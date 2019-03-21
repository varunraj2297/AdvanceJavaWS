package com.nt.jdbc;

/*
program to display employee details based on passing Employee id
Author			:	Team-A
Version 		:	1.0 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeDetailsBasedOnEmpNo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		int empno=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		
		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter empno:");
				empno=sc.nextInt();
			}
			//registering jdbc driver class
			Class.forName("oracle.jdbc.OracleDriver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement obj
			if(con!=null)
				st=con.createStatement();
		
			//preparing query
			query="SELECT * FROM EMP WHERE EMPNO="+empno;
			
			System.out.println(query);
		
			//sending and executing the query
			if(st!=null){
				rs=st.executeQuery(query);
			}
			//processing the result set
			if(rs!=null)
				if(rs.next())
				{
					flag=true;
					System.out.println(rs.getInt(1)+"		"+rs.getString(2)+"			"+rs.getString(3)+"			"+rs.getString(4)+"			"+rs.getString(5)+"			"+rs.getString(6)+"			"+rs.getString(7)+"			"+rs.getString(8));
				}
			if(flag)
				System.out.println("Results are found and displayed");
			else
				System.out.println("Results not found");
			
			
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
			try{
				if(sc!=null)
					sc.close();
			}catch(Exception e){
				e.printStackTrace();
			}	
		}//finally
	}//main
}//class


/*
 output:
 Enter empno:
7902
SELECT * FROM EMP WHERE EMPNO=7902
7902		FORD			ANALYST			7566			1981-12-03 00:00:00			3000			null			20
Results are found and displayed
*/
