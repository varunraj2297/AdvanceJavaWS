package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelTableTest {

	public static void main(String[] args) {
		try {
			//register jdbc Driver s/w
			Class.forName("com.hxtt.sql.excel.ExcelDriver");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		try(Connection con=DriverManager.getConnection("jdbc:excel:///F:/advjava")){
			try(Statement st=con.createStatement()){
				try(ResultSet rs=st.executeQuery("SELECT SNO,SNAME,SADD FROM STUDENT.SHEET1")) {
					while(rs.next())
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				}
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
