import java.sql.*;		//jdbc api
import java.util.Scanner;	//Scanner class
public class SelectTest2{
	
	public static void main(String[] args) throws Exception{
		
		String addrs;
		boolean flag=false;
		//JDBC driver class loaded and registered with DriverManager service automtically
	//	Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establishing the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		
		//creating Scanner object 
		Scanner sc = new Scanner(System.in);
				
		//create Statement object
		Statement st = con.createStatement();
		
		System.out.println("enter student address");
		//reading address
		addrs = sc.next();
		//converting into sql form
		addrs="'"+addrs+"'";
		String query= "SELECT * FROM STUDENT WHERE SADD="+addrs;
		System.out.println(query);

		//execute query
		ResultSet rs = st.executeQuery(query);
		
		
		//process the resultset
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
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