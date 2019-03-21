package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class ScrollableTestUsingResultSetMetaData {

	private static final String GET_ALL_STUDS="SELECT SNO,SNAME,SADD,SCOURSE FROM STUDENT";
	
	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs= null;
		ResultSetMetaData rsmd=null;
		int colCount=0;
		
		// TODO Auto-generated method stub
		try {
			//register jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//send and execute the Query 
			if(st!=null)
				rs=st.executeQuery(GET_ALL_STUDS);
			
			//create ResultSetMetaData Object
			if(rs!=null) {
				rsmd=rs.getMetaData();
			
			//process the ResultSet
				colCount=rsmd.getColumnCount();
				for(int i=1;i<=colCount;i++)
					System.out.print(rsmd.getColumnLabel(i)+"   ");
				while(rs.next())
					System.out.print("\n"+rs.getInt(1)+"     "+rs.getString(2)+"      "+rs.getString(3)+"          "+rs.getString(4));
		
					
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
