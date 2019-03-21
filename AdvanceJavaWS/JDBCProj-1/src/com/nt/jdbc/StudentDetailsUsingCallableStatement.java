package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/*create or replace procedure p_stu_details(no in number,name out varchar,addr out varchar)
as
begin
select sname,sadd into name,addr from student;
end;
*/

import java.util.Scanner;

public class StudentDetailsUsingCallableStatement {
private static final String STU_DETAILS="{CALL P_STU_DETAILS(?,?,?) }"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cst=null;
		try{
			//read i/ps
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter the student no::");
				no=sc.nextInt();
			}
			
			//register driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating the callable statement object
			if(con!=null)
				cst=con.prepareCall(STU_DETAILS);
			
			//register out param
			if(cst!=null){
				cst.registerOutParameter(2, Types.VARCHAR);
				cst.registerOutParameter(3,Types.VARCHAR);
			}
			
			//setting i/p values to IN param
			if(cst!=null)
				cst.setInt(1, no);
			
			//execute the query
			if(cst!=null)
				cst.execute();
			
			//gather the results
			if(cst!=null){
				System.out.println("Student Name :::"+cst.getString(2));
				System.out.println("Student Address:::"+cst.getString(3));
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
		
		finally{
			//close jdbc objs
			try{
				if(cst!=null)
					cst.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}//finally

	}

}
