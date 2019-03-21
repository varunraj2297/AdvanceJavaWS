package com.nt.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.commons.io.IOUtils;


public class MatrimonyPhotoRetriveBlobUsingPostGreSqlWithCommonsIOApi {
private static final String MATRIMONY_RETRIEVE_BLOB="SELECT ID,NAME,GENDER,AGE,PHOTO FROM JODIMAKERS_MATRIMONY WHERE ID=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		PreparedStatement ps= null;
		ResultSet rs=null;
		String name=null,gender=null;
		int age=0;
		InputStream is=null;
//		int bytesRead=0;
		OutputStream os=null;
//		byte[] buffer=null;
		
				try{
					//reading i/p
					sc=new Scanner(System.in);
					if(sc!=null){
						System.out.println("Enter Applicant id::");
						no=sc.nextInt();
					}
					//register jdbc driver
					Class.forName("org.postgresql.Driver");
					//establishing the connection 
					con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
					//create prepared statement object
					if(con!=null)
						ps=con.prepareStatement(MATRIMONY_RETRIEVE_BLOB);
					//setting i/p to sql query param
					if(ps!=null)
						ps.setInt(1, no);
						
					//send and execute the query
					if(ps!=null){
						rs=ps.executeQuery();
					}
					
					//process the result set
					if(rs!=null){
						if(rs.next()){
								name=rs.getString(2);
								gender=rs.getString(3);
								age=rs.getInt(4);
								
								System.out.println(no+"   "+name+"    "+gender+"      "+age);
								is=rs.getBinaryStream(5);
					//			buffer=new byte[4096];		//4kb
								os=new FileOutputStream("F:/pict1.jpeg");
								IOUtils.copy(is,os);
								
								System.out.println("details and photo retrived");
								
						}
						else
							System.out.println("Record not found");
					}
					
					
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
					//closing jdbc objs
					try{
						if(os!=null)
							os.close();
					}catch(IOException ioe){
						ioe.printStackTrace();
					}
					
					try{
						if(is!=null)
							is.close();
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
