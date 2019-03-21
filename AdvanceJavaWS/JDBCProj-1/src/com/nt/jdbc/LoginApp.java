package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {
	
	public static void main(String[] args) {
			Scanner sc=null;
			String username=null,password=null;
			Connection con=null;
			Statement st=null;
			String query=null;
			ResultSet rs=null;
			int count=0;
			//reading inputs
			try{
				sc=new Scanner(System.in);
				if(sc!=null){
					System.out.println("Enter username::");
					username=sc.nextLine();		//gives raja 
					System.out.println("Enter password::");
					password=sc.nextLine();		//gives rani
				}
				//converting inputs into sql required format
				username="'"+username+"'";
				password="'"+password+"'";
				//registering jdbc driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establishing the connection 
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
				//creating statement object
				if(con!=null)
					st=con.createStatement();
				//preparing sql query
					//SELECT COUNT(*) FROM USERLIST WHERE USERNAME='raja' AND PASSWORD='rani'
				query="SELECT COUNT(*) FROM USERLIST WHERE USERNAME="+username+" AND PASSWORD="+password;
				//sending and executing query
				if(st!=null)
					rs=st.executeQuery(query);
				//process the result set
				if(rs!=null){
					rs.next();
					count=rs.getInt(1);
				}
				if(count==0)
					System.out.println("Invalid Credentials");
				else
					System.out.println("Valid Credentials");
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
				//closing jdbc objects
				try{
					if(rs!=null)
						rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
				
				try{
					if(st!=null)
						st.close();
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
	}//main

}//class




/*output:-

Enter username::
raja
Enter password::
rani
Valid Credentials


Enter username::
raja
Enter password::
rani1
Invalid Credentials

*/