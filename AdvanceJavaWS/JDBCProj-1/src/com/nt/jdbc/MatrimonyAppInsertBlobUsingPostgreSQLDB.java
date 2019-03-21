package com.nt.jdbc;

/*CREATE TABLE public.jodimakers_matrimony
(
    id smallint NOT NULL,
    name character varying(20),
    gender character varying(6),
    age smallint,
    photo bytea,
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.jodimakers_matrimony
    OWNER to postgres;
	    
	    
	    CREATE SEQUENCE public."Matrimony_id_seq"
    INCREMENT 1
    START 1;

ALTER SEQUENCE public."Matrimony_id_seq"
    OWNER TO postgres;
*/
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MatrimonyAppInsertBlobUsingPostgreSQLDB {
private static final String INSERT_MATRIMONY_BLOB="INSERT INTO JODIMAKERS_MATRIMONY VALUES(NEXTVAL('matrimony_idseq'),?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,gender=null,photopath=null;
		int age=0;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		FileInputStream fis=null;
		int result=0;
		
		try{
			//reading i/p's
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter Applicant name::");
				name=sc.nextLine();
				System.out.println("Enter Applicant gender::");
				gender=sc.next();
				System.out.println("Enter Applicant age::");
				age=sc.nextInt();
				System.out.println("Enter photo path::");
				sc.nextLine();
				photopath=sc.nextLine();
			}
			//registering jdbc driver class
			Class.forName("org.postgresql.Driver");
			//establishing the connection
			con=DriverManager.getConnection("jdbc:postgresql:///varun","postgres","Raj*2297");
			//create PreparedStatement
			if(con!=null)
				ps=con.prepareStatement(INSERT_MATRIMONY_BLOB);
			
			
			if(ps!=null){
				//reading external file
				file=new File(photopath);
				fis=new FileInputStream(file);
	
				//setting i/p values to query
				ps.setString(1, name);
				ps.setString(2, gender);
				ps.setInt(3, age);
				ps.setBinaryStream(4, fis);
				//ps.setBlob(4, fis);
				
				//executing the query
				result=ps.executeUpdate();
			}
			//process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
			
		}//try
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
				if(fis!=null)
					fis.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				if(ps!=null)
					ps.close();
			}catch(SQLException se){
				se.printStackTrace();
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

/*
output:
Enter Applicant name::
rashi
Enter Applicant gender::
female
Enter Applicant age::
30
Enter photo path::
C:/Users/hp/Downloads/rashi.jpg
Record inserted

Enter Applicant name::
rashmika
Enter Applicant gender::
female
Enter Applicant age::
29
Enter photo path::
C:/Users/hp/Downloads/Rashmika.jpg
Record inserted

*/