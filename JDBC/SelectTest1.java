import java.sql.*;		//jdbc api
import java.util.Scanner;	//Scanner class
public class SelectTest1{
	
	public static void main(String[] args) throws Exception{
		
		int start =0,end=0;
		boolean flag=false;
		//JDBC driver class loaded and registered with DriverManager service automtically
	//	Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establishing the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		
		//creating Scanner object 
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter starting value::");
		start=sc.nextInt();
		
		System.out.println("enter ending value::");
		end=sc.nextInt();
		
		//create Statement object
		Statement st = con.createStatement();
		
		String query= "SELECT * FROM STUDENT WHERE SNO>="+start+" AND SNO<="+end;
		System.out.println(query);
		//execute query
		ResultSet rs = st.executeQuery(query);
		
		
		//process the resultset
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getInt("SNO")+" "+rs.getString(2)+" "+rs.getString(3));
		}
		if(flag)
			System.out.println("Records found");
		else
			System.out.println("Records not found");
		//close all JBDC stream objects
		rs.close();
		st.close();
		con.close();
	}
}