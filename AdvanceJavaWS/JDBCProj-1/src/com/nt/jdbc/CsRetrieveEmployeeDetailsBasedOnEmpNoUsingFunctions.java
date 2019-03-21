package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace function f_empdetailsusingid(eno in number,name out varchar,desg out varchar,income out number) return number
as
deptnum number(2);
begin
select ename,job,sal,deptno into name,desg,income,deptnum from emp where empno=eno;
return deptnum;
end;
/
*/

public class CsRetrieveEmployeeDetailsBasedOnEmpNoUsingFunctions {
private static final String CALL_QUERY="{?=call F_EMPDETAILSUSINGID(?,?,?,?) }";	
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cst=null;
		ResultSet rs=null;
		String name=null,desg=null;
		int income=0,deptnum=0;
		try{
			//reading i/ps
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Employee no::");
				no=sc.nextInt();
			}
		
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create CallableStatement Object
			if(con!=null)
				cst=con.prepareCall(CALL_QUERY);
			
			//register OUT param with java Types
			if(cst!=null){
				cst.registerOutParameter(1,Types.INTEGER);			//deptnum
				cst.registerOutParameter(3,Types.VARCHAR);		//name
				cst.registerOutParameter(4,Types.VARCHAR);		//desg
				cst.registerOutParameter(5, Types.INTEGER);			//income
			}
			
			//setting i/p value to IN param
			if(cst!=null)
				cst.setInt(2,no);
			
			//execute the query
			if(cst!=null)
				cst.execute();
			
			//gather the result from out params 
			if(cst!=null){
				deptnum=cst.getInt(1);
				name=cst.getString(3);
				desg=cst.getString(4);
				income=cst.getInt(5);
			
				System.out.println(name+"     "+desg+"    "+income+"     "+deptnum);
			}
					
		}
		
		catch(SQLException se){
			if(se.getErrorCode()==1403){
				System.out.println("Record Not Found");
			}
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
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
