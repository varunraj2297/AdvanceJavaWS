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

public class StudentAddrsRetrieveUsingCLOBWithPostGreSQL {
private static final String STUDENT_ADDRS_RETRIEVE="SELECT SNO,NAME,ADDRS FROM STUDENT_DETAILS WHERE SNO=? ";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader r=null;
		Writer w=null;
		char[] buffer=null;
		int bytesRead=0;
		ResultSet rs=null;
		String name=null;
		try{
			//read i/p's
			sc=new Scanner(System.in);
			int sno=0;
			if(sc!=null){
				System.out.println("Enter Student no::");
				sno=sc.nextInt();
			}
			//register jdbc driver class
			Class.forName("org.postgresql.Driver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
			//creating prepared statement object
			if(con!=null)
				ps=con.prepareStatement(STUDENT_ADDRS_RETRIEVE);
			
			//setting input value to query param
			if(ps!=null)
				ps.setInt(1, sno);
			
			//sending and executing query
			if(ps!=null)
				rs=ps.executeQuery();

			//process the result set
			if(rs!=null){
				if(rs.next()){
					name=rs.getString(2);
					System.out.println(sno+"   "+name);
					r=rs.getCharacterStream(3);
					buffer=new char[4096];		//4kb
					w=new FileWriter("addrs.txt");
					while((bytesRead=r.read(buffer))!=-1)
						w.write(buffer, 0,bytesRead);
					
					System.out.println("details displayed addrs is retrieved");
				}
				else
					System.out.println("record not found");
			}
				
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
			//closing jdbc objs
			try{
				if(w!=null)
					w.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			try{
				if(r!=null)
					r.close();
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
