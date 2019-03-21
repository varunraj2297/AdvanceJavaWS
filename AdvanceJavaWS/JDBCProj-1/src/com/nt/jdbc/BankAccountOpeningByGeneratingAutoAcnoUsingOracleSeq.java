package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BankAccountOpeningByGeneratingAutoAcnoUsingOracleSeq {
private static final String INSERT_QUERY="INSERT INTO BANKACCOUNT(ACNO,HOLDERNAME,ADDRS,BALANCE) VALUES(BANKACNOSEQUENCE.NEXTVAL,?,?,?)";
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
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
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

/*output:
Enter Applicant name::
pani
Enter address::
pune
Enter balance::
1000
Account created

SQL> select * from bankaccount;

ACNO HOLDERNAME ADDRS         BALANCE
---------- ---------- ---------- ----------
 100 raja       hyd             90000
 101 rani       hyd             90000
 103 jani       delhi            9000
 105 pani       pune             1000
*/