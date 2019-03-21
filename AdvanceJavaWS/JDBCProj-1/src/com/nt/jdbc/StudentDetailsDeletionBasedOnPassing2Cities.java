package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDetailsDeletionBasedOnPassing2Cities {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st =null;
		String city1=null,city2=null,query=null;
		int count=0;
		
		// TODO Auto-generated method stub
		try{
			
				//reading inputs
				sc=new Scanner(System.in);
	
				if(sc!=null){
						System.out.println("Enter city1:");
						city1=sc.next();
						System.out.println("Enter city2");
						city2=sc.next();
				}
	
				//registering jdbc driver software
	
				Class.forName("oracle.jdbc.OracleDriver");
	
				//establishing the connection
	
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	
				//creating Statement obj
				if(con!=null)
						st=con.createStatement();
				
				//preparing sql query
							//DELETE FROM STUDENT WHERE SADD IN('jaipur','punjab');
				query="DELETE FROM STUDENT WHERE SADD IN('"+city1+"','"+city2+"')";
				System.out.println(query);
				
				//send and execute query 
				if(st!=null)
					count=st.executeUpdate(query);
				
				if(count==0)
					System.out.println("No records found");
				else
					System.out.println(count+" no of records are deleted");
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
 Enter city1:
jaipur
Enter city2
punjab
DELETE FROM STUDENT WHERE SADD IN('hyd','pune')
2 no of records are deleted
*/