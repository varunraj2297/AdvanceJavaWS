package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BankAccountOpeningByGeneratingAutoAcnoUsingMySQLIdentityColumn {
private static final String INSERT_QUERY="INSERT INTO BANKACCOUNT(HOLDERNAME,ADDRS,BALANCE) VALUES(?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null;
		float balance=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try{
			sc=new Scanner(System.in);
			//reading inputs
			if(sc!=null){
				System.out.println("Enter Applicant name::");
				name=sc.nextLine();
				System.out.println("Enter address::");
				addrs=sc.nextLine();
				System.out.println("Enter balance::");
				balance=sc.nextFloat();
			}
			//registering jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establishing the connection
				//jdbc:mysql://localhost:3306/varun","root","Raj*2297----->when db is at server must use full syntax
			con=DriverManager.getConnection("jdbc:mysql:///mysql","root","Raj*2297");	//when db is local and having default port no can use this 
			//creating PreparedStatement
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			
			if(ps!=null){
				//setting input to Query params
				ps.setString(1,name);
				ps.setString(2,addrs);
				ps.setFloat(3,balance);
				//executing query
				result=ps.executeUpdate();
			}
			
			//process the result
			if(result==0)
				System.out.println("Problem in account creation");
			else
				System.out.println("Account created");
			
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

