package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/*create or replace function f_selectNonselectEmp(depno in number,cnt out number) return sys_refcursor
as
studetails sys_refcursor;
begin
open studetails for select empno,ename,job,sal,deptno from emp1 where deptno=depno;
delete from emp1 where deptno=depno;
cnt:=SQL%ROWCOUNT;
return studetails;
end;
/


*/
public class CsSelectAndNonSelectOnEmpTableUsingFunction {
private static final String CALL_QUERY="{ ?=call F_SELECTNONSELECTEMP(?,?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cst=null;
		ResultSet rs=null;
		int count=0;
		boolean flag=false;
		try{
				//read i/p's
				sc=new Scanner(System.in);
				if(sc!=null){
					System.out.println("Enter deptno no");
					no=sc.nextInt();
				}
				
				//register jdbc driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//establish the connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
				
				//create CallableStatement object
				if(con!=null)
					cst=con.prepareCall(CALL_QUERY);
		
				//register OUT param with java Types
				if(cst!=null){
					cst.registerOutParameter(1,OracleTypes.CURSOR);
					cst.registerOutParameter(3, Types.INTEGER);
				}
				
				//setting i/p value to IN param
				if(cst!=null)
					cst.setInt(2,no);
		
				//execute the query
				if(cst!=null)
					cst.execute();
				
				//gather result from out params
				if(cst!=null){
						rs=(ResultSet)cst.getObject(1);
						count=cst.getInt(3);
				}
				
				//process the result
				if(rs!=null){
					while(rs.next()){
						flag=true;
						System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getInt(4)+"  "+rs.getInt(5));
					}
					if(!flag)
						System.out.println("No records found");
				}
				
				System.out.println("Effected number of records::"+count);
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
			//close jdbc obj
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
