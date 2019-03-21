import java.sql.*;			//jdbc api
import java.util.Scanner;	//Scanner object

public class EmployeesDetailsBasedOnDesignation
{
	public static void main(String args[]) throws Exception{
		
		String query,designation;
		int count=0;
		//JDBC driver class loaded and registered automatically
		
		//establishing the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		System.out.println("Connection established");

		//Create Statement object

		Statement st = con.createStatement();

		//Creating Scanner object
		Scanner sc = new Scanner(System.in);

		//reading string from keyboard and storing into designation
		System.out.println("enter designation");
		designation=sc.next();

		//converting designation into sql form
		designation="'"+designation+"'";

		//storing query into a variable;

		query="SELECT * FROM EMPLOYEES WHERE JOB_ID="+designation;

		//displaying the query
		System.out.println(query);

		//execute query

		ResultSet rs = st.executeQuery(query);
	
		//process resultset
		while(rs.next())
		{
			count++;
			System.out.println(rs.getInt("EMPLOYEE_ID")+"			"+rs.getString("FIRST_NAME")+"			"+rs.getString("JOB_ID")+"			"+rs.getInt("salary"));
		}
		if(count>0)
			System.out.println(count+" records are found");
		else
			System.out.println("no records found");
			
		//closing JDBC streams
		
		rs.close();
		st.close();
		con.close();
	}
}