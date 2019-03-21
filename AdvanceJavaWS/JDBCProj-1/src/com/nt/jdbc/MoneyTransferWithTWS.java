package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MoneyTransferWithTWS {

	public static void main(String[] args) {
		Scanner sc=null;
		int src_acc=0,dest_acc=0;
		float amount=0.0f;
		int result[]=null;
		boolean flag=false;
		
		try {
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter source account no");
				src_acc=sc.nextInt();
				System.out.println("enter destination account no");
				dest_acc=sc.nextInt();
				System.out.println("enter amount");
				amount=sc.nextFloat();
			}
			//register jdbc driver Class
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr")){
				if(con!=null) 
						try(Statement st=con.createStatement()){
							//add queries to batch
							if(st!=null) {
								st.addBatch("UPDATE MONEY_TRANSFER SET BAL=BAL-"+amount+"WHERE ACCNO="+src_acc);
								st.addBatch("UPDATE MONEY_TRANSFER SET BAL=BAL+"+amount+"WHERE ACCNO="+dest_acc);
							}
							if(st!=null)
								result=st.executeBatch();
				
							//process the result
							if(con!=null) {
									con.setAutoCommit(false);
				
							for(int i=0;i<result.length;i++) {
								System.out.println(result[i]);
								if(result[i]==0) {
										flag=true;
										break;
								}
							}
							if(flag) {
								con.rollback();
								System.out.println("Transfer is failed   money not transfered");
							}
							else {
								con.commit();
								System.out.println("Transfer is succeded   money transfered");
							}
						}
				}//try
			catch(SQLException se) {
				try {
					con.rollback();
					System.out.println("Transfer is failed   money not transfered");
				}
				catch(SQLException se1) {
					se1.printStackTrace();
				}
				se.printStackTrace();
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		
	}//main

}//class
