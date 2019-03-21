package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatementNonSelectInsertInTextCSV {
	private static final String INSERT_QUERY="INSERT INTO STUDENTTEXTCSV VALUES(?,?,?)";
	public static void main(String[] args) {
			Connection con=null;
			PreparedStatement ps=null;
			Scanner sc=null;
			int count=0;
			int sno=0;
			String sname=null,sadd=null;
			int result=0;
			try{
					//reading inputs
					sc=new Scanner(System.in);
					if(sc!=null){
						System.out.println("Enter students count::");
						count=sc.nextInt();
					}
					//registering JDBC driver
					Class.forName("com.hxtt.sql.text.TextDriver");
					//establishing the connection
					con=DriverManager.getConnection("jdbc:text:///F:/advjava");
					//creating prepared statement object
					if(con!=null){
						ps=con.prepareStatement(INSERT_QUERY);
					}
					//passing inputs to query parameters
					if(ps!=null&&sc!=null){
						for(int i=1;i<=count;i++){
							System.out.println("Enter "+i+" Student number");
							sno=sc.nextInt();
							sc.nextLine();
							System.out.println("Enter "+i+" Student name");
							sname=sc.nextLine();
							System.out.println("Enter "+i+" Student address");
							sadd=sc.nextLine();
							
							ps.setInt(1, sno);
							ps.setString(2, sname);
							ps.setString(3, sadd);
						
						
							//executing the query
							result =ps.executeUpdate();
						
							if(result==0)
								System.out.println("Student-"+i+" record is not inserted");
							else
								System.out.println("Student-"+i+" record is inserted");
						}
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
				//close jdbc objs
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
