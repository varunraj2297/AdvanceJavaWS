package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/*create or replace procedure p_allempdetails(dno in number,details out sys_refcursor)
as
begin
open details for select * from emp where deptno=dno;
end;
/
*/

public class CsSys_RefCusorAllEmpDetailsUsingProcedure {
private static final String CALL_QUERY="{ CALL P_ALLEMPDETAILS(?,?) }";

	public static void main(String[] args) {
		Scanner sc=null;
		int dno=0;
		Connection con=null;
		CallableStatement cst=null;
		ResultSet rs=null;
		// TODO Auto-generated method stub
		try{
			//reading i/ps
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter department no::");
				dno=sc.nextInt();
			}
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create CallableStatement object
			if(con!=null)
				cst=con.prepareCall(CALL_QUERY);
			
			//register OUT param
			if(cst!=null)
				cst.registerOutParameter(2, OracleTypes.CURSOR);
	
			//setting values to IN param
			if(cst!=null)
				cst.setInt(1, dno);
			
			//execute the query
			if(cst!=null)
				cst.execute();
			//gather the result fro OUT param
			if(cst!=null)
				rs=(ResultSet)cst.getObject(2);
			
			//process the result
			if(rs!=null){
				while(rs.next())
					System.out.println(rs.getInt(1)+"     "+rs.getString("ENAME")+"      "+rs.getInt(6)+"    "+rs.getInt("DEPTNO"));
			}
			else
				System.out.println("Record not found");
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
		}
	}

}
