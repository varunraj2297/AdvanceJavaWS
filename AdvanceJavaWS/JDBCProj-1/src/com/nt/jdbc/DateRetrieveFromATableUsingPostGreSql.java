package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetrieveFromATableUsingPostGreSql {
private static final String RETRIEVE_PERSON_DETAILS="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_TAB";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int no=0;
		String name=null,dob=null,doj=null,dom=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		SimpleDateFormat sdf=null;
		
		try{
			//registering jdbc driver
			Class.forName("org.postgresql.Driver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
			//creating PreparedStatement
			if(con!=null)
				ps=con.prepareStatement(RETRIEVE_PERSON_DETAILS);
			//sending and executing query
			if(ps!=null)
				rs=ps.executeQuery();
			//processing the Result set
			if(rs!=null){
				while(rs.next()){
					no=rs.getInt(1);
					name=rs.getString(2);
					sqdob=rs.getDate(3);
					sqdoj=rs.getDate(4);
					sqdom=rs.getDate(5);
					
					//converting java.sql date format to  string date format
					
					udob=sqdob;
					sdf=new SimpleDateFormat("dd/MMM/yyyy");
					dob=sdf.format(udob);

					udoj=sqdoj;
					doj=sdf.format(udoj);

					udom=sqdom;
					dom=sdf.format(udom);	
					
					System.out.println(no+"   "+name+"    "+dob+"    "+doj+"     "+dom);
				}
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
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(ps!=null)
					ps.close();
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

/*
output:-
1   karan    22/Nov/1991    21/Apr/2016     04/Dec/2017
2   harsha    14/Jun/1992    04/Nov/2017     06/Apr/2019
3   gohan    09/Sep/1991    12/Jun/2017     01/Mar/2018
*/