package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JobPortalCLOBInsertResumeUsingMySQL {
private static final String INSERT_CLOB_QUERY="INSERT INTO NAUKRI_JOBPORTAL(JNAME,QLFY,RESUME) VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,qlfy=null,filepath=null;
		Connection con=null;
		PreparedStatement ps =null;
		Reader reader=null;
		File file=null;
		int result=0;
		try{
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Applicant name::");
				name=sc.nextLine();
				System.out.println("Enter Applicant qualification::");
				qlfy=sc.next();
				System.out.println("Enter file location path::");
				sc.nextLine();
				filepath=sc.nextLine();
			}//if
			//registering driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:mysql:///varun", "root","Raj*2297");
			//create PreparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_CLOB_QUERY);
			//setting i/p values to sql query params			
			if(ps!=null){
				ps.setString(1, name);
				ps.setString(2, qlfy);
				file=new File(filepath);
				reader=new FileReader(file);
				ps.setCharacterStream(3, reader);
			}
			//setting the query
			if(ps!=null)
				result=ps.executeUpdate();
			//process the result
			if(result==0)
				System.out.println("Details not uploaded");
			else
				System.out.println("Details are uploaded sucessfully");
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
			//close all jdbc objs
			try{
				if(reader!=null)
					reader.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			try{
				if(ps!=null)
				ps.close();
			}catch(SQLException ioe){
				ioe.printStackTrace();
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
