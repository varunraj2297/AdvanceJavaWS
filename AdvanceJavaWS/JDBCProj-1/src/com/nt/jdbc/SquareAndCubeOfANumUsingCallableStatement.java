package com.nt.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class SquareAndCubeOfANumUsingCallableStatement {
private static final String PRO_QUERY="{ call sqr_cube_pro(?,?,?) }";
	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cst=null;
		int resultsq=0,resultcu=0;
		
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
			
			//creating callable statement obj
			if(con!=null)
				cst=con.prepareCall(PRO_QUERY);
			
			if(cst!=null){
				//registering out param
				cst.registerOutParameter(2,Types.INTEGER);
				cst.registerOutParameter(3,Types.INTEGER);
				
				//setting inputs to IN Param
				cst.setInt(1,no);
				
				//execute the query
				cst.execute();
				
				//process the result
				resultsq=cst.getInt(2);
				resultcu=cst.getInt(3);
				
				System.out.println("Square of "+no+" is ::"+resultsq);
				System.out.println("Cube of "+no+" is ::"+resultcu);
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
