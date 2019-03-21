import java.sql.*;


public class SelectTest 
{
	public static void main(String[] args) throws Exception
	{
		//register type4 driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish connection with database software
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		//create JDBC Statement object
		Statement st = con.createStatement();
		//execute the query and process the result set
		ResultSet rs = st.executeQuery("SELECT * FROM EMP");
		//print Database table records
		while(rs.next()!=false)
		{
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
		}
		//close all JBDC stream objects
		rs.close();
		st.close();
		con.close();
	}
}
