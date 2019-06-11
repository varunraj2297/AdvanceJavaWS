package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessingTestWithTxManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		int result[]=null;
		boolean flag=false;
		try {
			//register jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create Simple Statement obj
			if(con!=null) {
				st=con.createStatement();
			
				con.setAutoCommit(false);
			}
			//add queries to batch
			if(st!=null) {
				st.addBatch("DELETE FROM STUDENT WHERE SNO>900 and SNO<1020");
				st.addBatch("UPDATE STUDENT SET SADD='vizag' WHERE SNO>100 AND SNO<105");
				st.addBatch("INSERT INTO STUDENT VALUES(491,'gsir','sdgs','sgsgf')");
			}
			
			//send and execute the query
			if(st!=null) {
				result=st.executeBatch();
			}
			
			//process the result
			if(con!=null) {
				for(int i=0;i<result.length;i++) {
					System.out.println(result[i]);
					if(result[i]==0) {
						flag=true;
						break;
					}
				
			}
			
				if(flag) {
					con.rollback();
					System.out.println("results are rolled back");
				}
					else {
					con.commit();
					System.out.println("results are commited");
				}
			}
		}//try
		catch(SQLException se) {
			try {
				con.rollback();
				System.out.println("results are rolled back");
			}
			catch(SQLException se1) {
				se1.printStackTrace();
			}
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