package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataTransferBetweenTwoDatabases {
private static final String ORACLE_INSERT_QUERY="INSERT INTO BANKACCOUNT VALUES(?,?,?,?)";
private static final String MYSQL_SELECT_QUERY="SELECT ACNO,HOLDERNAME,ADDRS,BALANCE FROM BANKACCOUNT";
	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int acno=0,balance=0,result=0,count=0;
		String holderName=null,addrs=null;
		try{
				//registering driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				//establishing the connection
				oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
				mysqlCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","Raj*2297");
		
				//creating statement object
				if(mysqlCon!=null)
					st=mysqlCon.createStatement();
				
				//creating prepared statement object
				if(oraCon!=null)
					ps=oraCon.prepareStatement(ORACLE_INSERT_QUERY);
				
				//sending and executing select query
				if(st!=null)
					rs=st.executeQuery(MYSQL_SELECT_QUERY);
				
				//processing resultset and setting input values to query params
				if(ps!=null && rs!=null){
						while(rs.next()){
							acno=rs.getInt(1);
							holderName=rs.getString(2);
							addrs=rs.getString(3);
							balance=rs.getInt(4);
							
							ps.setInt(1, acno);
							ps.setString(2, holderName);
							ps.setString(3, addrs);
							ps.setInt(4, balance);
							
							result=ps.executeUpdate();
							if(result!=0){
								System.out.println("record inserted");
								count++;
								result=0;
							}
							else
								System.out.println("record not inserted");
						}
						System.out.println(count+" records are inserted");
				}
							
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			//closing jdbc objs
			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}//finally

	}//main

}//class

/*output
 record inserted
record inserted
record inserted
3 records are inserted

//in sql
 Connected to:
Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production

SQL> select * from bankaccount;

      ACNO HOLDERNAME ADDRS         BALANCE
---------- ---------- ---------- ----------
       100 raja       hyd             90000
       101 rani       hyd             90000
       103 jani       delhi            9000
*/
