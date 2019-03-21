package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*create or replace procedure p_auth(user in varchar,pass in varchar,result out varchar) 
as
cnt number(1);
begin
select count(*) into cnt from userlist where username=user and password=pass;
if(cnt<>0) then
result:='VALID CREDENTIALS';
else
result:='INVALID CREDENTIALS';
end if;
end;
/*/

public class AuthenticationUsingCallableStatement {
private static final String AUTH="{ CALL P_AUTH(?,?,?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		String user=null,pass=null;
		Connection con=null;
		CallableStatement cst=null;
		
		try{
			
			//reading i/ps
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter username::");
				user=sc.nextLine();
				System.out.println("Enter password::");
				pass=sc.nextLine();
			}
			
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create callable statement object
			if(con!=null)
				cst=con.prepareCall(AUTH);
			
			//registering out param
			if(cst!=null)
				cst.registerOutParameter(3,Types.VARCHAR);
			
			//setting i/p values to IN params
			if(cst!=null){
				cst.setString(1, user);
				cst.setString(2, pass);
			}
			//execute the query
			if(cst!=null)
				cst.execute();
			
			//gather the result
			if(cst!=null)
				System.out.println("Result is::: "+cst.getString(3));
			
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
