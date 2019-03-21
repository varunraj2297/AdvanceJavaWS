package com.nt.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class JobPortalRetrieveCLOBResumeUsingPostGreSQL {
private static final String RETRIEVE_DETAILS_QUERY="SELECT JSNO,JNAME,QLFY,RESUME FROM NAUKRI_JOBPORTAL WHERE JSNO=?"; 
	public static void main(String[] args) {
		Scanner sc=null;
		int jsno=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null,qlfy=null;
		Reader reader=null;
		Writer writer=null;
		char[] buffer=null;
		int charsRead=0;
		try{
			//read i/ps
			sc= new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Applicant no::");
				jsno=sc.nextInt();
			}
			//register jdbc driver class
			Class.forName("org.postgresql.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:postgresql:///varun", "postgres","Raj*2297");
			//creating PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(RETRIEVE_DETAILS_QUERY);
			//setting i/p values to query param
			if(ps!=null)
				ps.setInt(1, jsno);
			//sending and executing the query
			if(ps!=null)
				rs=ps.executeQuery();
			
			//process the ResultSet
			if(rs!=null){
				if(rs.next()){
					name=rs.getString(2);
					qlfy=rs.getString(3);
					System.out.println(jsno+"   "+name+"   "+qlfy);
					reader=rs.getCharacterStream(4);
					buffer=new char[1024];   //1kb
					writer=new FileWriter("abc.txt");
					while((charsRead=reader.read(buffer))!=-1)
						writer.write(buffer, 0, charsRead);
					
					System.out.println("Data Retreived and displayed");
					
				}
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
			//close all jdbc objs
			try{
				if(reader!=null)
					reader.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			try{
				if(writer!=null)
					writer.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			try{
				if(rs!=null)
				rs.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			try{
				if(ps!=null)
				ps.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
	
			try{
					if(con!=null)
						con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
	
			try{
				if(sc!=null)
					sc.close();
			}catch(Exception e){
				e.printStackTrace();
			}

		}//finally

	}//main

}//class
