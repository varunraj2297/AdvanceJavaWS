package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SelectTestWithTWS {

	public static void main(String[] args) {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr")){
			if(con!=null)
				try(Statement st=con.createStatement()){
					if(st!=null)
						try(ResultSet rs=st.executeQuery("SELECT * FROM STUDENT")){
							while(rs.next())
								System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"      "+rs.getString(3)+"       "+rs.getString(4));
						}//try
				}//try
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
	}//main
}//class
