package com.nt.jdbc;

/*
 ============================================================================
 Name        : StudentDetails
 Author      : Team-A
 Version     : 1.1
 Description : Program to get the details of the students based on passed address (with Coding Standards) 
 ============================================================================
 */

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDetails{

	public static void main(String args[]){
		
		Scanner sc=null;
		String city1=null;
		String city2=null;
		String city3=null;
		Connection con=null;
		Statement st=null;
		String condition=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=false;
	
		try{
			
			//creating Scanner object
			sc=new Scanner(System.in);

			//assigning address to cities
			if(sc!=null){
			
				System.out.println("Enter city1");
				city1=sc.next();
			
				System.out.println("Enter city2");
				city2=sc.next();
				
				System.out.println("Enter city3");
				city3=sc.next();

			}
			
			//registering jdbc driver software(optional)
			Class.forName("oracle.jdbc.OracleDriver");

			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

			//Creating Statement object
			if(con!=null)
				st=con.createStatement();

			//preparing query condition in oracle format ('hyd','jaipur','punjab');
			condition="IN('"+city1+"','"+city2+"','"+city3+"')";
			
			//storing SQL Query in a String variable query
							// SELECT * FROM STUDENT WHERE SADD IN('hyd','jaipur','punjab')
			query="SELECT * FROM STUDENT WHERE SADD "+condition;
			System.out.println(query);

			//sending and executing query
			if (st!=null){
				rs=st.executeQuery(query);
			}

			//process the resultset
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
				if(flag)
					System.out.println("Records found and displayed");
				else
					System.out.println("No records found to displayed");
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
		
		//closing all jdbc objects and streams
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

/*
F:\adv java\JDBC>javac -d . StudentDetails.java

F:\adv java\JDBC>java com.nt.jdbc.StudentDetails
Enter city1
hyd
Enter city2
jaipur
Enter city3
punjab
SELECT * FROM STUDENT WHERE SADD IN('hyd','jaipur','punjab')
101     raja    hyd
102     nataraj hyd
130     rani    hyd
148     rathod  jaipur
157     jai     punjab
Records found and displayed
*/