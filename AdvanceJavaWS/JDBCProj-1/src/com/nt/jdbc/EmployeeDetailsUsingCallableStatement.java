package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_emp_details(no in number,name out varchar,desgn out varchar,salary out number)
 as
 begin
   select ename,job,sal into name,desgn,salary from emp where empno=no;
 end;
/
*/
public class EmployeeDetailsUsingCallableStatement {
private static final String EMP_DETAILS="{CALL P_EMP_DETAILS(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cst=null;
		try{
			//reading i/ps
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter employee no:::");
				no=sc.nextInt();
			}
			
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create CallableStatement obj
			if(con!=null)
				cst=con.prepareCall(EMP_DETAILS);
			
			//register out params
			
			if(cst!=null){
				cst.registerOutParameter(2, Types.VARCHAR);
				cst.registerOutParameter(3, Types.VARCHAR);
				cst.registerOutParameter(4, Types.INTEGER);
			}
			
			//setting i/p values to IN params
			
			if(cst!=null)
				cst.setInt(1,no);
			
			//execute the query
			if(cst!=null)
				cst.execute();
			
			//gather the results
			if(cst!=null){
				System.out.println("ENAME::"+cst.getString(2));
				System.out.println("JOB::"+cst.getString(3));
				System.out.println("SALARY::"+cst.getInt(4));
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
	
	}//main

}//class
