package com.nt.jdbc;

/*
program to display student details by passing student id
Author			:	Team-A
Version 		:	1.0 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDetailsBasedOnIdNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		int studId=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
		
		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter studid:");
				studId=sc.nextInt();
			}
		
			//registering jdbc driver software
			Class.forName("oracle.jdbc.OracleDriver");

			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement obj
			if(con!=null){
				st=con.createStatement();
			}
			
			//preparing query
					//SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO=102
			query="SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO="+studId;
			System.out.println(query);
			//sending and executing query
			if(st!=null){
				rs=st.executeQuery(query);	
			}
			
			//processing result set
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"		"+rs.getString(2)+"		"+rs.getString(3));
				}
			}
			
			if(flag)
				System.out.println("results found and displayed");
			else
				System.out.println("results not found");
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
Enter studid:
130
SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO=130
130		rani		hyd
results found and displayed
*/