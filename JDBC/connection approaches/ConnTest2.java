class ConnTest2 
{
	public static void main(String[] args) throws java.sql.SQLException{
		
		//creating JDBC driver class object and registering JDBC driver class object with Drivermanager Service
		java.sql.DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		//establishing the connection
		java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Raj*2297");
		
		if (con==null){
			System.out.println("connection not established");
		}		
		
		else{
			System.out.println("connection established");
		}
	}
}
