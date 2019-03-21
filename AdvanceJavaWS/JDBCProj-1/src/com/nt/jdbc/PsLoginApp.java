package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp {
	private static final String AUTH_QUERY="SELECT COUNT(*) FROM USERLIST WHERE USERNAME=? AND PASSWORD=?";
	public static void main(String[] args) {
		Scanner sc=null;
		String username=null,password=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try{
			//reading inputs
			sc=new Scanner(System.in);
			
			if(sc!=null){
				System.out.println("Enter username::");
				username=sc.nextLine();
				System.out.println("Enter password::");
				password=sc.nextLine();
			}
			//registering jdbc connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			//create prepared statement object
			if(con!=null){
				ps=con.prepareStatement(AUTH_QUERY);
			}
			//passing inputs to query parameters
			if(ps!=null){
				ps.setString(1, username);
				ps.setString(2, password);
				
				//executing the  query
				rs=ps.executeQuery();
			}
			
			//process the result set
			if(rs!=null){
				rs.next();
				count=rs.getInt(1);
			}
			if(count==0)
				System.out.println("Invalid Credentials");
			else
				System.out.println("Valid Credentials");
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
		}
				
	}

}
