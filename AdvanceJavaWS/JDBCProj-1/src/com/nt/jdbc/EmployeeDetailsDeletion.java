package com.nt.jdbc;

/*
program to delete employee details
Author			:	Team-A
Version 		:	1.0 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeDetailsDeletion {
			public static void main(String[] args) {
				
					Scanner sc=null;
					int empno=0;
					Connection con=null;
					Statement st=null;
					String query;
					int count=0;
			
					try{
						//reading inputs
						sc=new Scanner(System.in);
				
						if(sc!=null){
							System.out.println("Enter empno:");
							empno=sc.nextInt();
						}
				
						//registering jdbc driver software
				
						Class.forName("oracle.jdbc.OracleDriver");
				
						//establishing the connection
				
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
				
						//creating Statement obj
						if(con!=null)
								st=con.createStatement();
				
						//prepare query
								//DELETE FROM EMP1 WHERE EMPNO=7839;
						query="DELETE FROM EMP1 WHERE EMPNO="+empno;
				
				
						//executing query
						if(st!=null)
								count=st.executeUpdate(query);
				
						if(count==0)
								System.out.println("No records found");
						else
								System.out.println(count+" no of records deleted");
						
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


/*output:
Enter empno:
7499
1 no of records deleted
*/