package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
program to update employee details
Author			:	Team-A
Version 		:	1.0 
*/

public class EmployeeDetailsUpdation {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int empno=0;
		String query=null;
		int count=0;
		// TODO Auto-generated method stub
		try{
			//reading inputs
			sc=new Scanner(System.in);
			
			if(sc!=null){
				System.out.println("Enter Employee Number");
				empno=sc.nextInt();
			}
			//registering jdbc driver software
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			//establishing the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//preparing sql query
						//UPDATE EMP1 SET SAL=SAL+100;
				query="UPDATE EMP1 SET SAL=SAL+100 WHERE EMPNO="+empno;
				
				System.out.println(query);
				
			//executing query
				
					if(st!=null)
						count=st.executeUpdate(query);
					
			
			//process result
					if(count==0)
						System.out.println("records not found");
					else
						System.out.println(count+" no of rows are updated");
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

/*output
Enter Employee Number
7900
UPDATE EMP1 SET SAL=SAL+100 WHERE EMPNO=7900
1 no of rows are updated
*/