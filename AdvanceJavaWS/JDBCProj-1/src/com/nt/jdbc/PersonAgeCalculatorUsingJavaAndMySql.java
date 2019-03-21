package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorUsingJavaAndMySql {
private static final String GET_DOB="SELECT DOB FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		java.sql.Date sqdob=null;
		java.util.Date udob=null,udot=null;

		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Person id:::");
				no=sc.nextInt();
			}
			//registering jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:mysql:///varun","root","Raj*2297");
			//creating prepared statement
			if(con!=null)
				ps=con.prepareStatement(GET_DOB);
			//setting i/p to query param and executing query
			if(ps!=null){
				ps.setInt(1, no);
				
				rs=ps.executeQuery();
			}
			//converting java.sql.Date date format to date string 
			if(rs!=null)
				if(rs.next()){
					sqdob=rs.getDate(1);
					udob=sqdob;
					udot=new java.util.Date();
					System.out.println("Person age is::"+(((float)udot.getTime()-udob.getTime())/((long)1000*60*60*24*365)));
				}
				else
					System.out.println("person not found");
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
			//closing jdbc objs
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
/*output
Enter Person id:::
1
Person age is::28.216045

Enter Person id:::
2
Person age is::23.914677
*/
