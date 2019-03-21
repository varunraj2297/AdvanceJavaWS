package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
/*
CREATE TABLE public.person_tab
(
    pid integer NOT NULL,
    pname character varying(20),
    dob date,
    doj date,
    dom date,
    PRIMARY KEY (pid)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
  
  CREATE SEQUENCE public.pid_seq
    INCREMENT 1
    START 1;
 */

public class DateInsertionIntoPostGreSQLTable {
private static final String INSERT_PERSON_DETAILS="INSERT INTO PERSON_TAB VALUES(nextval('PID_SEQ'),?,?,?,?)";
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
			Class.forName("org.postgresql.Driver");
			//establishing the connection
					//		jdbc:postgresql://localhost:5432/varun","postgres","Raj*2297"
			con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
			
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
1	"karan"	"1991-11-22"	"2016-04-21"	"2017-12-04"
2	"harsha"	"1992-06-14"	"2017-11-04"	"2019-04-06"
3	"gohan"	"1991-09-09"	"2017-06-12"	"2018-03-01"
 */
