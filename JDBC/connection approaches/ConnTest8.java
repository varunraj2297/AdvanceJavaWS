public class ConnTest8 {
	
	public static void main(String[] args) throws java.sql.SQLException,ClassNotFoundException{
		
		String driver=System.getProperty("mydriver");
		System.out.println(driver);
		Class.forName(driver);
		
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

//>javac ConnTest8.java

//>java -Dmydriver=oracle.jdbc.driver.OracleDriver ConnTest8
