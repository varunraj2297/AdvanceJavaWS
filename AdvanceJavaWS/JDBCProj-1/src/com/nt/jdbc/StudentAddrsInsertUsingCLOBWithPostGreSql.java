package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*
 CREATE TABLE public.student_details
(
    sno smallint NOT NULL,
    name character varying(10),
    addrs text,
    PRIMARY KEY (sno)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.student_details
    OWNER to postgres;

CREATE SEQUENCE public.sno_seq
    INCREMENT 1
    START 1;

ALTER SEQUENCE public.sno_seq
    OWNER TO postgres;
 */
public class StudentAddrsInsertUsingCLOBWithPostGreSql {
private static final String INSERT_STUDENTDETAILS="INSERT INTO STUDENT_DETAILS VALUES(NEXTVAL('SNO_SEQ'),?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		Reader reader=null;
		int result=0;
		try{
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter the name::");
				name=sc.nextLine();
			}
			//registering jdbc driver class
			Class.forName("org.postgresql.Driver");
			
			//establishing the connection
			con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
		
			//creating prepared statement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENTDETAILS);
			
			//setting i/p's to query param
			if(ps!=null){
				//external files
				file=new File("E:\\SAddrs.txt");
				reader=new FileReader(file);
				ps.setString(1, name);
				ps.setCharacterStream(2,reader);
			}
			//executing the query
			if(ps!=null){
				result=ps.executeUpdate();
			}
			//process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			//closing jdbc objs
			try{
				if(reader!=null)
					reader.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			
			try{
				if(ps!=null)
				ps.close();
			}catch(SQLException ioe){
				ioe.printStackTrace();
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

	}//main
}//class
