import java.sql.*;		//jdbc api
import java.util.Scanner;	//Scanner class
public class DepartmentDetails
{
	public static void main(String args[]) throws SQLException{

		String query;
		int deptno;
		boolean flag=false;
		//JDBC Driver Class is loaded and registered with DriverManager service automatically

		//establishing the connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");

		//creating Statement class object
		Statement st = con.createStatement();

		//Creating Scanner object
		Scanner sc = new Scanner(System.in);

		//reading department no from keyboard and storing it into deptno
		System.out.println("enter department number::");
		deptno = sc.nextInt();

		//storing query into a String variable
		query = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID="+deptno;

		//displaying the query
		System.out.println(query);

		//executing query
		ResultSet rs = st.executeQuery(query);
		
		//processing resultset
		while(rs.next())
		{
			flag=true;
			System.out.println(rs.getInt(1)+"		"+rs.getString("DEPARTMENT_NAME")+"		"+rs.getInt("MANAGER_ID")+"		"+rs.getInt(4));
		}

		if(flag)
			System.out.println("Record Found");
		else
			System.out.println("Record not Found");

		//closing JDBC streams

		rs.close();
		st.close();
		con.close();
	}
}