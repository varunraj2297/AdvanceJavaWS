package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonAgeCalculatorUsingMySql {
//private static final String PERSON_AGE_CALC="SELECT (CURDATE()-DOB)/365 FROM PERSON_TAB WHERE PID=?";
private static final String PERSON_AGE_CALC="SELECT YEAR(CURDATE())-YEAR(DOB) FROM PERSON_TAB WHERE PID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Person id no:::");
				no=sc.nextInt();
			}
			//registering jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:mysql:///varun","root","Raj*2297");
			//creating prepared statement
			if(con!=null)
				ps=con.prepareStatement(PERSON_AGE_CALC);
			
			//setting i/p to query param and executing query
			if(ps!=null){
				ps.setInt(1, no);
				rs=ps.executeQuery();
			}
			
			//process the resultset
			if(rs!=null)
				if(rs.next())
					System.out.println("Person age is: "+rs.getFloat(1));
				else
					System.out.println("Person not found");
		}//try
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
	
			try{
				if(sc!=null)
					sc.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}//finally

	}
}

/*output:
 Enter Person id no:::
1
Person age is: 29.0


Enter Person id no:::
2
Person age is: 24.0

*/

