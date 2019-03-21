package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropTable {

	public static void main(String[] args) {
		Scanner sc=null;
		String tableName=null;
		Connection con=null;
		Statement st=null;
		int count=0;
		String query=null;
		
		try{
				
				//reading inputs
				sc=new Scanner(System.in);
				if(sc!=null){
					System.out.println("Enter table name");
					tableName=sc.next();
				}
				//establishing the connection
	
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	
				//creating Statement obj
				if(con!=null)
						st=con.createStatement();
				
				//preparing query
				query="DROP TABLE "+tableName;
				
				
				//sending and executing query
				if(st!=null)
					count=st.executeUpdate(query);
				
				if(count==0)
					System.out.println("table dropped");
				else
					System.out.println("table not dropped");
		}
		catch(SQLException se ){
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
