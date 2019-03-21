package com.nt.jdbc;

/*
program to display student details by passing student id
Author			:	Team-A
Version 		:	1.0 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDetailsBasedOnIdNumUsingPreparedStatement {
private static final String STUDENTS_DETAILS="SELECT SNO,SNAME,SADD FROM STUDENT WHERE SNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		int stuid=0;
		ResultSet rs=null;
		boolean flag=false;
		
		try{
		
		
			//registering jdbc driver software
			Class.forName("oracle.jdbc.OracleDriver");

			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating PreparedStatement obj
			if(con!=null){
				ps=con.prepareStatement(STUDENTS_DETAILS);
			}
			
			//setting input values to query param
			if(ps!=null){
				sc=new Scanner(System.in);
				if(sc!=null){
					do{
						System.out.println("enter student id");
						stuid=sc.nextInt();
						ps.setInt(1, stuid);
						
						//executing the query
						rs=ps.executeQuery();
						
						//process the result
						if(rs!=null){
							if(rs.next()){
								flag=true;
								System.out.println(rs.getInt(1)+"		"+rs.getString(2)+"		"+rs.getString(3));
							}
						}
						if(stuid!=-1){
							if(flag){
								System.out.println("Record found and displayed");
								flag=false;
							}
							else{
								System.out.println("Record not found");
							}
						}
					}while(stuid!=-1);
				}
					
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