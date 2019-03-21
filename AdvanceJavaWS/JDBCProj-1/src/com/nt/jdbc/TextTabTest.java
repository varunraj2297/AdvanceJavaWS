package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TextTabTest {

	public static void main(String[] args) {
		try {
			//register jdbc Driver s/w
			Class.forName("com.hxtt.sql.text.TextDriver");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}

		try(Connection con=DriverManager.getConnection("jdbc:text:///F:/advjava?_CSV_Separator=\t;csvfileExtension=tsv")){
			try(Statement st=con.createStatement()){
				try(ResultSet rs=st.executeQuery("SELECT * FROM STUDENTTEXTTSV")) {
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
