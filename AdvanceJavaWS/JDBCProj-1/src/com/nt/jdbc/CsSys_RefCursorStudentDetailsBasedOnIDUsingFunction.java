package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/*create or replace function f_studetailsusingid(add in varchar) return sys_refcursor
as
details sys_refcursor;
begin
open details for select sno,sadd,sname from student where sadd=add;
return details;
end;
/
*/
public class CsSys_RefCursorStudentDetailsBasedOnIDUsingFunction {
private static final String CALL_QUERY=" { ?=call F_STUDETAILSUSINGID(?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		String add=null;
		Connection con=null;
		CallableStatement cst=null;
		ResultSet rs=null;
		try{
			//read i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student add::");
				add=sc.next().toLowerCase();
			}
				
			//register jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create Callable Statement Object
			if(con!=null)
				cst=con.prepareCall(CALL_QUERY);
			
			//register OUT param with jdbc Types
			if(cst!=null)
				cst.registerOutParameter(1,OracleTypes.CURSOR);
			
			//set i/p value to IN param
			if(cst!=null)
				cst.setString(2, add);
			
			//execute the query
			if(cst!=null)
				cst.execute();
			
			//gather the result from OUT param
			if(cst!=null)
				rs=(ResultSet)cst.getObject(1);
			
			//process the result
			if(rs!=null)
				while(rs.next())
					System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"     "+rs.getString(3));
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
				if(se.getErrorCode()==1403)
					System.out.println("No Records Found");
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
