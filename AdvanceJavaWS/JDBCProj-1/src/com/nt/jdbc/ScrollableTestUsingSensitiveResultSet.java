package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTestUsingSensitiveResultSet {

	private static final String GET_ALL_STUDS="SELECT SNO,SNAME,SADD,SCOURSE FROM STUDENT";	//Dont mention * mention column name otherwise it throws SQLExecption when u r using rs.refreshRow() [error::Unsupported feature: refreshRow]
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs= null;
		// TODO Auto-generated method stub
		try {
			//register jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//send and execute the Query 
			if(st!=null)
				rs=st.executeQuery(GET_ALL_STUDS);
			
			//process the ResultSet
			if(rs!=null) {
				System.out.println("Top to Bottom\n");
				Thread.sleep(10000);
				while(rs.next()) {
					rs.refreshRow();
					System.out.println(rs.getRow()+"    "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				}
				
				/*System.out.println("Bottom to Top\n");
				
				while(rs.previous())
					System.out.println(rs.getRow()+"    "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				rs.beforeFirst();
				System.out.println("Before First record");
				System.out.println(rs.getRow()   );//+"     "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				if(rs.isBeforeFirst()) {
					System.out.println( "Before First record");
					System.out.println(rs.getRow() );   //+"       "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				}
				
		
				rs.afterLast();
				System.out.println("After Last record");
				System.out.println(rs.getRow() );  //+"     "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				if(rs.isAfterLast()) {
					System.out.println( "After Last record");
					System.out.println(rs.getRow() );  //+"       "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				}
				
				
				
				rs.first();
				System.out.println("First record");
				System.out.println(rs.getRow()+"     "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				if(rs.isFirst()) {
					System.out.println( "First record");
					System.out.println(rs.getRow()+"       "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				}
				
				rs.last();
				System.out.println("last record");
				System.out.println(rs.getRow()+"      "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				if(rs.isLast()) {
					System.out.println( "last record");
					System.out.println(rs.getRow()+"       "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				}
				
				rs.relative(-5);
				System.out.println("from current postion 5 records back");
				System.out.println(rs.getRow()+"      "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				rs.relative(2);
				System.out.println("from current postion 2 records front");
				System.out.println(rs.getRow()+"      "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				rs.absolute(6);
				System.out.println("from top 6th record");
				System.out.println(rs.getRow()+"      "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
				
				rs.absolute(4);
				System.out.println("from bottom 4th record");
				System.out.println(rs.getRow()+"      "+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
			*/	
			}
			
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
		}//finally
	}//main

}//class
