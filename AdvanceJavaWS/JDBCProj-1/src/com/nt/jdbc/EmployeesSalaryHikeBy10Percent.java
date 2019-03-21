package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeesSalaryHikeBy10Percent {

	public static void main(String[] args) {
		Scanner sc=null;
		int sal1=0,sal2=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter lower salary:");
				sal1=sc.nextInt();
				System.out.println("Enter higher salary:");
				sal2=sc.nextInt();
			}
			
			//establishing the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

			//creating Statement obj
			if(con!=null)
					st=con.createStatement();
			
			//prepare query
						//UPDATE EMP1 SET SAL=SAL+0.1*SAL WHERE SAL IN(800,1000)
			query="UPDATE EMP1 SET SAL=SAL+0.1*SAL WHERE SAL BETWEEN "+sal1+" AND "+sal2;
			System.out.println(query);
			
			//sending and executing query
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process result
			if(count==0)
				System.out.println("no records updated");
			else
				System.out.println(count+" no of records are updated");
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			//closing jdbc objs
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
Enter lower salary:
1000
Enter higher salary:
3000
UPDATE EMP1 SET SAL=SAL+0.1*SAL WHERE SAL BETWEEN 1000 AND 3000
8 no of records are updated
*/