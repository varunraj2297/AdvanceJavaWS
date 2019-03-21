package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null; 
		Connection con=null;
		Statement st=null;
		boolean flag=false;
		ResultSet rs=null;
		int count=0;
		
		try{
				//reading inputs
				sc =new Scanner(System.in);
				if(sc!=null){
					System.out.println("Enter sql query");
					query=sc.nextLine();
				}
				//establishing the connection

				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

				//creating Statement obj
				if(con!=null)
						st=con.createStatement();
				
				//checking whether to perform select or non-select operation
				
				if(st!=null){
					flag=st.execute(query);
					//performing select or non select operation
					if(flag){
						rs=st.getResultSet();
						//process the result set
						if(rs!=null)
							while(rs.next())
								System.out.println(rs.getInt(1)+"      "+rs.getString(2)+"      "+rs.getString(3));	
					}
					else{
						count=st.getUpdateCount();
						if(count==0)
							System.out.println("no records found");
						else
							System.out.println(count+" rows affected");
					}
						
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				//closing jdbc objs
				try{
					if(st!=null)
						st.close();
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
	}
}
/*

output1:
Enter sql query
select * from emp1 where sal>5000
7839      KING      PRESIDENT

output2:
Enter sql query
update emp1 set sal=sal+100
11 rows affected


*/