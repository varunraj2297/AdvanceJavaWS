package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class SquareOfANumUsingCallableStatement {
private static final String PRO_QUERY="{ call sqr_pro(?,?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cst=null;
		int result=0;
		// TODO Auto-generated method stub
		try{
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter number::");
				no=sc.nextInt();
			}
			//register jdbc driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			
			//creating CallableStatement obj
			if(con!=null)
				cst=con.prepareCall(PRO_QUERY);
			if(cst!=null){
			//register out param
				cst.registerOutParameter(2,Types.INTEGER);
			//setting i/p values to IN param
				cst.setInt(1,no);
			//executing the query
				cst.execute();
			//process the result
				result=cst.getInt(2);
				System.out.println("Square of "+no+" is "+result);
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
				if(cst!=null)
					cst.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}//finally

	}//main
}//class
