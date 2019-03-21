package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/*
SQL> create table person_tab(pid number(10) primary key,pname varchar2(20),dob date,doj date,dom date);

Table created.

SQL> create sequence pid_seq start with 1 increment by 1;

Sequence created.
*/
public class DateInsertionIntoOracleTable {
private static final String INSERT_PERSON_DETAILS="INSERT INTO PERSON_TAB VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,dob=null,doj=null,dom=null;
		Connection con=null;
		PreparedStatement ps=null;
		SimpleDateFormat sdob=null,sdoj=null;
		java.util.Date  udob=null,udoj=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		int result=0;
		try{
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter name::");
				name=sc.nextLine();
				System.out.println("Enter DOB(dd-MM-yyyy)");
				dob=sc.next();
				System.out.println("Enter DOJ(MM-dd-yyyy)");
				doj=sc.next();
				System.out.println("Enter DOM(yyyy-MM-dd)");
				dom=sc.next();
			}
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_PERSON_DETAILS);
			
			//converting string to sql date format
			sdob=new SimpleDateFormat("dd-MM-yyyy");
			udob=sdob.parse(dob);
			sqdob=new java.sql.Date(udob.getTime());
			
			sdoj=new SimpleDateFormat("MM-dd-yyyy");
			udoj=sdoj.parse(doj);
			sqdoj=new java.sql.Date(udoj.getTime());
			
			sqdom=java.sql.Date.valueOf(dom);
			
			//setting inputs to Query params
			if(ps!=null){
				ps.setString(1,name);
				ps.setDate(2,sqdob);
				ps.setDate(3,sqdoj);
				ps.setDate(4,sqdom);
			}
			//executing the query
			if(ps!=null)
				result=ps.executeUpdate();
			//process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
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
			//close all jdbc objects
			try{
				if(ps!=null)
					ps.close();
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

/*output:-
 select * from person_tab;

       PID PNAME                DOB       DOJ       DOM
---------- -------------------- --------- --------- ---------
         1 karan                20-NOV-90 10-DEC-14 23-DEC-16
         2 viran                09-FEB-91 24-SEP-14 23-AUG-17
         3 jack                 21-MAY-91 13-JAN-17 02-MAR-18
         4 jani                 10-DEC-92 13-SEP-18 12-JAN-19
 */
