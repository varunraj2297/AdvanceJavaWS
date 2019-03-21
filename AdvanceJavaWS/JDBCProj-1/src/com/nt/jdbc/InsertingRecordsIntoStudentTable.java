package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertingRecordsIntoStudentTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		int sno=0;
		String sname=null,saddrs=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student number:");
				sno=sc.nextInt();
				System.out.println("Enter student name:");
				sc.nextLine();
				sname=sc.nextLine();
				System.out.println("Enter student address:");
				saddrs=sc.nextLine();
			}
			//converting  inputs required to sql 
			sname="'"+sname+"'";
			saddrs="'"+saddrs+"'";
			
			//registering jdbc driver software
			
			Class.forName("oracle.jdbc.OracleDriver");

			//establishing the connection

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

			//creating Statement obj
			if(con!=null)
					st=con.createStatement();
			
			//preparing query
					//INSERT INTO STUDENT VALUES(210,'sai nath','old alwal')
			query="INSERT INTO STUDENT VALUES("+sno+","+sname+","+saddrs+")";
			System.out.println(query);
			
			//sending and executing query
			if(st!=null)
				count=st.executeUpdate(query);
			
			//process result
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println("1 record inserted");
		}//try
		catch(SQLException se){
			if(se.getErrorCode()==1)
				System.out.println("student with this number already inserted");
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


/*output

Enter student number:
210
Enter student name:
sai nath
Enter student address:
old alwal
INSERT INTO STUDENT VALUES(210,'sai nath','old alwal')
1 record inserted

*/