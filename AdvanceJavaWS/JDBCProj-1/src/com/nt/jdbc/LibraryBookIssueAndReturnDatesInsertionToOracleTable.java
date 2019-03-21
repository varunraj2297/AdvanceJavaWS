package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class LibraryBookIssueAndReturnDatesInsertionToOracleTable {
/*create table libraryregister(studno number(10) primary key,studname varchar2(10),bookname varchar2(10),issuedate date,returndate date)
	  
	create sequence libstudno_seq start with 1 increment by 1;
*/
	private static final String INSERT_LIBRARY_DETAILS="INSERT INTO LIBRARYREGISTER VALUES(LIBSTUDNO_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String sname=null,bookname=null,issuedate=null,returndate=null;
		SimpleDateFormat sdfissuedate=null;
		java.util.Date uissuedate=null;
		java.sql.Date sqissuedate=null,sqreturndate=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		
		try{
			//reading inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter student name");
				sname=sc.nextLine();
				System.out.println("Enter book name");
				bookname=sc.nextLine();
				System.out.println("Enter issue date(dd-MM-yyyy)");
				issuedate=sc.next();
				System.out.println("Enter return date(yyyy-MM-dd)");
				returndate=sc.next();
			}
			//registering jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(INSERT_LIBRARY_DETAILS);
			
			//converting string date format to sql date format
			sdfissuedate = new SimpleDateFormat("dd-MM-yyyy");
			uissuedate = sdfissuedate.parse(issuedate);
			sqissuedate=new java.sql.Date(uissuedate.getTime());
			//string date format of returndate is same sql date format hence we can directly convert into sql date format
			sqreturndate = java.sql.Date.valueOf(returndate);
			
			//setting input values to query params
			if(ps!=null){
				ps.setString(1,sname);
				ps.setString(2, bookname);
				ps.setDate(3, sqissuedate);
				ps.setDate(4, sqreturndate);
			}
			
			//executing the query
			if(ps!=null)
				result=ps.executeUpdate();
			
			//process the result
			if(result==0)
				System.out.println("Record Not Inserted");
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
			//close all jdbc objs
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
 select * from libraryregister;

    STUDNO STUDNAME   BOOKNAME   ISSUEDATE RETURNDAT
---------- ---------- ---------- --------- ---------
         1 varun      java       12-JAN-19 21-JAN-19
         2 sainath    python     01-JAN-19 20-JAN-19
         3 vineeth    Spring     20-DEC-18 19-JAN-19
*/
