package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class LibraryBookIssueAndReturnDatesInsertionToPostGreSqlTable {
/*CREATE TABLE public.libraryregister
(
    studno integer NOT NULL,
    studname character varying(20),
    bookname character varying(20),
    issuedate date,
    returndate date,
    PRIMARY KEY (studno)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

 
	CREATE SEQUENCE public.libstudno_seq
    INCREMENT 1
    START 1;

*/
	private static final String INSERT_LIBRARY_DETAILS="INSERT INTO LIBRARYREGISTER VALUES(NEXTVAL('LIBSTUDNO_SEQ'),?,?,?,?)";
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
			Class.forName("org.postgresql.Driver");
			
			//establishing the connection
						//		jdbc:postgresql://localhost:5432/varun","postgres","Raj*2297"
			con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
			
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
 
1	"varun"	"java"	"2018-12-12"	"2019-01-21"
2	"vijay"	".NET"	"2018-12-14"	"2019-01-29"
3	"charan"	"Oracle"	"2018-12-21"	"2019-01-25"

*/
