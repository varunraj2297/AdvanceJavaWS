public class ConnTest7 {
	
	public static void main(String[] args) throws java.sql.SQLException,ClassNotFoundException{
		
		Class.forName(args[0]);
		
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

//javac ConnTest7.java

//java ConnTest7 oracle.jdbc.driver.OracleDriver