package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertingRecordsIntoEmpTable {

	public static void main(String[] args) {
		Scanner sc=null;
		int empno=0;
		String ename=null,job=null;
		int sal=0;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter employee no:");
				empno=sc.nextInt();
				System.out.println("Enter employee name:");
				sc.nextLine();
				ename=sc.nextLine();
				System.out.println("Enter employee job:");
				job=sc.nextLine();
				System.out.println("Enter employee salary:");
				sal=sc.nextInt();
			}
			//converting  inputs required to sql 
			ename="'"+ename+"'";
			job="'"+job+"'";
			
			//registering jdbc driver software
			
			Class.forName("oracle.jdbc.OracleDriver");

			//establishing the connection

			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

			//creating Statement obj
			if(con!=null)
					st=con.createStatement();
			
			//preparing query
					//INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(7453,'Vineeth','Just Dial',15000)
			query="INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES("+empno+","+ename+","+job+","+sal+")";
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
				System.out.println("employee with this number already inserted");
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

/*output:
Enter employee no:
7453
Enter employee name:
Vineeth
Enter employee job:
Dell
Enter employee salary:
15000
INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(7453,'Vineeth','Dell',15000)
1 record inserted

output2:
Enter employee no:
7499
Enter employee name:
sainath
Enter employee job:
justdial
Enter employee salary:
12000
INSERT INTO EMP(EMPNO,ENAME,JOB,SAL) VALUES(7499,'sainath','justdial',12000)
employee with this number already inserted
*/