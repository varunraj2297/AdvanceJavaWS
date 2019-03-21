package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBatchProcessingTicketReservationWithTxManagement {

	public static void main(String[] args) {
		Scanner sc=null;
		String gender=null,src_place=null,dest_place=null;
		Connection con=null;
		PreparedStatement ps=null;
		int groupSize=0;
		int result[]=null;
		boolean flag=false;
		String name=null;
		
		try {
			//reading i/ps
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Group Size::");
				groupSize=sc.nextInt();
				
				System.out.println("Enter Boarding place::");
				src_place=sc.next();

				System.out.println("Enter Destination place::");
				dest_place=sc.next();
			}
			//register the Jdbc Driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//create preparedStatement object
			if(con!=null)
				ps=con.prepareStatement("INSERT INTO TICKET_RESERVATION VALUES(TICKET_SEQ.NEXTVAL,?,SEAT_SEQ.NEXTVAL,?,?,?)");

			//add queries to batch
			for(int i=1;i<=groupSize;i++) {
					//set values to query param
					if(ps!=null) {
							//reading i/ps
							if(sc!=null) {
									System.out.println("Enter gender:");
									gender=sc.next();
									
									System.out.println("Enter Name::");
									name=sc.next();
					
									
							}
							ps.setString(1,gender );
							ps.setString(2,src_place);
							ps.setString(3,dest_place);
							ps.setString(4, name);
					}
					ps.addBatch();
			}
			//execute the query
			if(ps!=null)
				result=ps.executeBatch();
			
			//process the result
			if(con!=null) {
				con.setAutoCommit(false);
				for(int i=0;i<result.length;i++) {
					if(result[i]==0) {
						flag=true;
						break;
					}
				}
				if(flag) {
					con.rollback();
					System.out.println("tickets are not booked");
				}
				else {
					con.commit();
					System.out.println("tickets are booked");
				}

			}
		}//try
		
		catch(SQLException se) {
			try {
				con.rollback();
				System.out.println("tickets are not booked");
			}
			catch(SQLException se1) {
				se1.printStackTrace();
			}
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace(); 
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace(); 
			}
		}//finally

	}//main

}//class
