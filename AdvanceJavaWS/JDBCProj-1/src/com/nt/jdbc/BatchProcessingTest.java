package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		boolean flag=false;
		try {
			//register jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create Simple Statement obj
			if(con!=null)
				st=con.createStatement();
			
			//add queries to batch
			if(st!=null) {
				st.addBatch("INSERT INTO STUDENT VALUES(458,'jagir','japan','Electrics')");
				st.addBatch("UPDATE STUDENT SET SADD='SEC_BAD' WHERE SNO>100 AND SNO<105");
				st.addBatch("DELETE FROM STUDENT WHERE SNO>167");
			}
			
			//send and execute the query
			if(st!=null) {
				st.executeBatch();
				flag=true;
			}
			
			//process the result
			if(flag)
				System.out.println("queries are executed");
			else
				System.out.println("queries are not executed");
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
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace(); 
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace(); 
			}
		}//finally
	}//main

}//class
