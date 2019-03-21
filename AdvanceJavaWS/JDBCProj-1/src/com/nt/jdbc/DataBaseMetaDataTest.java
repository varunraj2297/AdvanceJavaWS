package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseMetaDataTest {


	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		try {
			//register the jdbc driver s/w
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create DataBaseMetaData obj
				if(con!=null)
					dbmd=con.getMetaData();
			
				if(dbmd!=null) {
						System.out.println("DataBase Name :: "+dbmd.getDriverName());
						System.out.println("Product Name ::" +dbmd.getDatabaseProductName());
						System.out.println("Product Version::"+dbmd.getDatabaseProductVersion());
						System.out.println(" DataBase MajorVersion ::"+dbmd.getDatabaseMajorVersion());
						System.out.println(" DataBase MinorVersion ::"+dbmd.getDatabaseMinorVersion());
						System.out.println(" JDBC MajorVersion ::"+dbmd.getJDBCMajorVersion());
						System.out.println(" JDBC MinorVersion ::"+dbmd.getJDBCMinorVersion());
						System.out.println(" JDBC Driver MajorVersion ::"+dbmd.getDriverMajorVersion());
						System.out.println(" JDBC Driver MinorVersion ::"+dbmd.getDriverMinorVersion());
						System.out.println(" All SQL keywords ::"+dbmd.getSQLKeywords());
						System.out.println(" All numeric functions ::"+dbmd.getNumericFunctions());
						System.out.println(" All System functions ::"+dbmd.getSystemFunctions());
						System.out.println(" Max table name Length ::"+dbmd.getMaxTableNameLength());
						System.out.println(" Max column name Length ::"+dbmd.getMaxColumnNameLength());
						System.out.println(" Max Row size "+dbmd.getMaxRowSize());
						System.out.println(" Max Cols in Select Query ::"+dbmd.getMaxColumnsInSelect());
						System.out.println(" Max Cols in DataBase table ::"+dbmd.getMaxColumnsInTable());
						System.out.println(" Max Connections to DataBase ::"+dbmd.getMaxConnections());
			
						ResultSet rs=dbmd.getTables(null, null, "STU%", null);
						while(rs.next()) {
							System.out.println(rs.getString(3));
						}
				}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		finally {
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
