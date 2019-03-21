package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/*create or replace function f_studetailsusinginitchars(initchars in varchar) return sys_refcursor
as
studetails sys_refcursor;  
begin
open studetails for select * from student where sname like initchars;
return studetails;
end;
/
*/
		
public class CsSys_RefCursorStudentDetailsBasedOnIntialCharsUsingFunction {
private static final String CALL_QUERY="{  ?=call F_STUDETAILSUSINGINITCHARS(?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		String initChars=null;
		Connection con=null;
		CallableStatement cst=null;
		ResultSet rs=null;
		try{
			//read i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter initial character");
				initChars=sc.next()+"%";				//appending wild card character to the i/p
			}
			
			//register jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating CallableStatement
			if(con!=null){
				cst=con.prepareCall(CALL_QUERY);
			}
			
			//register out param with jdbc types
			if(cst!=null)
				cst.registerOutParameter(1,OracleTypes.CURSOR);
			
			//setting i/p value to IN param
			if(cst!=null)
				cst.setString(2,initChars);
			
			//execute the query
			if(cst!=null)
				cst.execute();
			
			//gather the results from OUT param
			if(cst!=null)
				rs=(ResultSet)cst.getObject(1);
			
			//process the result
			if(rs!=null){
				if(rs.next())
					System.out.println(rs.getInt(1)+"     "+rs.getString(2)+"    "+rs.getString(3));
				else
					System.out.println("record not found");
			}
			
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
