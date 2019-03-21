package com.nt.jdbc;

/*
 ===========================================================================================================
 Name        : EmployeeDetails
 Author      : Team-A
 Version     : 1.1
 Description : Program to get the details of the employees based on passed designation(with Coding Standards) 
 ============================================================================================================
 */
 import java.util.Scanner;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.Statement;
 import java.sql.ResultSet;
 import java.sql.SQLException;

 public class EmployeeDetails{
	
	public static void main(String ...args){
		Scanner sc=null;
		String job1=null;
		String job2=null;
		String job3=null;
		Connection con=null;
		Statement st =null;
		String condition=null;
		String query=null;
		ResultSet rs=null;
		boolean flag=true;

		try{
			//creating Scanner obj
			sc= new Scanner(System.in);

			//Assigning jobs to job variable
			if(sc!=null){
				System.out.println("Enter job1");
				job1=sc.next();
				
				System.out.println("Enter job2");
				job2=sc.next();

				System.out.println("Enter job3");
				job3=sc.next();

			}

			//registering jdbc driver software (optional)
			Class.forName("oracle.jdbc.OracleDriver");
	
			//establishing Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating Statement object
			if(con!=null)
				st=con.createStatement();
			
			//preparing sql condition ('MANAGER','CLERK','ANALYST')
			condition="IN('"+job1+"','"+job2+"','"+job3+"')";
				
			//storing Query in into a query String variable
						//SELECT * FROM EMP WHERE JOB IN('MANAGER','CLERK','ANALYST');
			query="SELECT * FROM EMP WHERE JOB "+condition;
			System.out.println(query);

			//sending and executing query
			if(st!=null){
				rs=st.executeQuery(query);
			}
			
			//processing the resultset
			if(rs!=null){
				while (rs.next()){
					flag=true;
					System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getInt(6)+"\t\t"+rs.getInt(8));
				}
				if(flag)
					System.out.println("Records Found and Displayed");
				else
					System.out.println("No Records Found to Display");
			}
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

		//closing all jdbc objects and streams
		finally{
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
		}//finally

	}//main
 }//class

/*
F:\adv java\JDBC>javac -d . EmployeeDetails.java

F:\adv java\JDBC>java com.nt.jdbc.EmployeeDetails
Enter job1
MANAGER
Enter job2
CLERK
Enter job3
ANAYLST
SELECT * FROM EMP WHERE JOB IN('MANAGER','CLERK','ANAYLST')
7698            BLAKE           MANAGER         2850            30
7782            CLARK           MANAGER         2450            10
7566            JONES           MANAGER         2975            20
7369            SMITH           CLERK           800             20
7876            ADAMS           CLERK           1100            20
7900            JAMES           CLERK           950             30
7934            MILLER          CLERK           1300            10
Records Found and Displayed
*/