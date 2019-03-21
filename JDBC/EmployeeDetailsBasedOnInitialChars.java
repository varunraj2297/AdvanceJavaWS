package com.nt.jdbc;

/*
program to display employee details based on initial characters 
Author :: TEAM-A
Version ::1
*/
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDetailsBasedOnInitialChars
{
		
		public static void main(String args[]){
			Scanner sc=null;
			String initialChars=null;
			Connection con=null;
			Statement st=null;
			String query=null;
			ResultSet rs=null;
			boolean flag=false;
			try{

				//reading inputs
				sc=new Scanner(System.in);
				System.out.println("Enter initialChars:");
 		
				if(sc!=null)
					initialChars=sc.next();
			
				//convert into sql form
								//'A%'
				initialChars="'"+initialChars+"%'";
			
				//registering jdbc driver software
				Class.forName("oracle.jdbc.OracleDriver");
	
				//establishing the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	
				//creating Statement object
				if(con!=null)
					st=con.createStatement();
			
				//preparing SQL query
					//SELECT ENAME,EMPNO,SAL,JOB FROM EMP WHERE ENAME LIKE 'A%'
				query="SELECT ENAME,EMPNO,SAL,JOB FROM EMP WHERE ENAME LIKE "+initialChars;
			
				//sending and executing query
				if(st!=null)
					rs=st.executeQuery(query);
		
				//process ResultSet
				while(rs.next())
				{
					flag=true;
					System.out.println(rs.getString(1)+"	"+rs.getInt(2)+"	"+rs.getInt(3)+"	"+rs.getString(4));
				}	
		
				if(flag)
					System.out.println("Results found and displayed");
				else
					System.out.println("Results not found to display");

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
		
			}
	}
}