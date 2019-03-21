package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateStudentDetails {

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String newName=null,newAddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		// TODO Auto-generated method stub
		
		try{
				//reading inputs
				sc=new Scanner(System.in);
				if(sc!=null){
					System.out.println("Enter student no");
					sno=sc.nextInt();
					System.out.println("Enter student Name:");
					sc.nextLine();
					newName=sc.nextLine();
					System.out.println("Enter Student Address:");
					newAddrs=sc.nextLine();
				}
				//converting  inputs required to sql 
				newName="'"+newName+"'";
				newAddrs="'"+newAddrs+"'";
				
				//registering jdbc driver software
				
				Class.forName("oracle.jdbc.OracleDriver");
	
				//establishing the connection
	
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	
				//creating Statement obj
				if(con!=null)
						st=con.createStatement();
				
				//preparing query
						//UPDATE STUDENT SET SNAME='RAMESH',SADD='VIZAG' WHERE SNO=169
				
				query="UPDATE STUDENT SET SNAME="+newName+",SADD="+newAddrs+" WHERE SNO="+sno;
				System.out.println(query);
				
				//sending and executing query
				if(st!=null)
					count=st.executeUpdate(query);
				
				if(count==0)
					System.out.println("record not found");
				else
					System.out.println(count+" row updated");
		}
		catch(SQLException se ){
			if(se.getErrorCode()==12899)
				System.out.println("value too large for column");
			else if(se.getErrorCode()==933)
				System.out.println("SQL command not properly ended");
			else
				System.out.println("Unknown DB error");
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


/*
Enter student no
169
Enter student Name:
naresh
Enter Student Address:
kadapa
UPDATE STUDENT SET SNAME='naresh',SADD='kadapa' WHERE SNO=169
1 row updated
*/