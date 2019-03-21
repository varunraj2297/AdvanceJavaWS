package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement st=null;
		int count=0;
		
		try{
	
				//establishing the connection
	
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	
				//creating Statement obj
				if(con!=null)
						st=con.createStatement();
				
				
				//sending and executing query
				if(st!=null)
					count=st.executeUpdate("CREATE TABLE TEMP1(SNO NUMBER(10))");
				
				if(count==0)
					System.out.println("table created");
				else
					System.out.println("table not created");
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
	
		}//finally
	}//main
}//class
