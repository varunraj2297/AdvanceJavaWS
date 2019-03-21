public class ConnTest6 {
	
	public static void main(String[] args) throws java.sql.SQLException,ClassNotFoundException{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
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
