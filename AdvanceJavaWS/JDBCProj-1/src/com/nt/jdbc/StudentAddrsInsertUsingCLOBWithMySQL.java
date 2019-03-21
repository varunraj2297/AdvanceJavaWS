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

/*
 CREATE TABLE `varun`.`student_details` (
  `sno` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NULL,
  `addrs` TINYTEXT NULL,
  PRIMARY KEY (`sno`));

 */
public class StudentAddrsInsertUsingCLOBWithMySQL {
private static final String INSERT_STUDENTDETAILS="INSERT INTO STUDENT_DETAILS(NAME,ADDRS) VALUES(?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		Reader reader=null;
		int result=0;
		try{
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter the name::");
				name=sc.nextLine();
			}
			//registering jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:mysql:///varun","root","Raj*2297");
		
			//creating prepared statement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENTDETAILS);
			
			//setting i/p's to query param
			if(ps!=null){
				//external files
				file=new File("E:\\SAddrs.txt");
				reader=new FileReader(file);
				ps.setString(1, name);
				ps.setCharacterStream(2,reader);
			}
			//executing the query
			if(ps!=null){
				result=ps.executeUpdate();
			}
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
			//closing jdbc objs
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
