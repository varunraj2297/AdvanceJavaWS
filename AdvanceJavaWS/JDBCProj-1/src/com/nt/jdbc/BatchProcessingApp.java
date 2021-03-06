package com.nt.jdbc;
/*SQL> desc emp;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
EMPNO                                              NUMBER(10)
EMPNAME                                            VARCHAR2(50)
JOB                                                VARCHAR2(30)
SAL                                                NUMBER(10)
ADDR                                               VARCHAR2(50)

SQL> desc student;
Name                                      Null?    Type
----------------------------------------- -------- ----------------------------
SNO                                       NOT NULL NUMBER(5)
SNAME                                              VARCHAR2(20)
STREAM                                             VARCHAR2(20)
PLACE                                              VARCHAR2(20)*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchProcessingApp {
	private static final String INSERT_QUERY_FOR_STUDENT="INSERT INTO STUDENT_TAB VALUES(?,?,?,?)";
	private static final String UPDATE_QUERY_FOR_EMP="UPDATE  EMP_TAB SET ADDR=? WHERE EMPNO=?";
	private static final String DELETE_QUERY_FOR_STUDENT="DELETE FROM STUDENT_TAB WHERE SNO=?";
	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr", "hr")){
					con.setAutoCommit(false);
	         		try(Scanner sc=new Scanner(System.in)){
	         			try(PreparedStatement ps1=con.prepareStatement(INSERT_QUERY_FOR_STUDENT)){
	         		        int[] result=null;
	         				System.out.println(" enter the values for the STUDENT table :: ");
	         		        System.out.println("----------------------------------------------------------------");
	         		        System.out.println(" enter the student no : ");
	         		        int no=sc.nextInt();
	         		        System.out.println(" enter the student name : ");
	         		        String name=sc.next();
	         		       System.out.println(" enter the student branch of his study in engineering  : ");
	         		        String stream=sc.next();
	         		       System.out.println(" enter the student address : ");
	         		        String address =sc.next();
	         		        //set the query params to the student table
	         			        ps1.setInt(1, no);
	         			        ps1.setString(2,name);
	         			        ps1.setString(3, stream);
	         			        ps1.setString(4, address);
	         			       //add to the batch   
	         			        ps1.addBatch();
	         			       //execute the batch
	         			        result=ps1.executeBatch();
	         			        if(result!=null)
	         			        	System.out.println("records are inserted in student table ");
	         			        else
	         			        	System.out.println("records are not inserted in student table");
	         			        //process the batch
	         			        int sum=0;
	         			        System.out.println(result[0]);
	         			        for(int i=0;i<result.length;++i)
	         			        	sum=sum+result[i];
	         			        System.out.println("no of records effected : "+sum);
	         			}//try(ps1)
	         			try(PreparedStatement ps2=con.prepareStatement(UPDATE_QUERY_FOR_EMP)){
	         				  System.out.println("=====================================================");
		         		       System.out.println(" enter the values for the EMP table :::: ");
		         		       System.out.println(" enter the employee number to which you want to update the record : ");
		         		       int empno=sc.nextInt();
		         		       System.out.println(" enter the new address of employee with empno = "+empno+" : ");
		         		       String empaddrs=sc.next();
	         			     //set the query params to the emp table
		         		      ps2.setString(1, empaddrs); 
		         		       ps2.setInt(2, empno);
	         			     //add to the batch   
	         			        ps2.addBatch();
	         			      //execute the batch
	         			        int[] result=ps2.executeBatch();
	         			        if(result!=null)
	         			        	System.out.println("records are  updated in emp table");
	         			        else
	         			        	System.out.println("records are not affected");
	         			        //process the batch
	         			        int sum=0;
	         			        for(int i=0;i<result.length;++i)
	         			        	sum=sum+result[i];
	         			        System.out.println("no of records effected : "+sum);
	         		}//try(ps2)
	         			try(PreparedStatement ps3=con.prepareStatement(DELETE_QUERY_FOR_STUDENT)){
                             System.out.println("======================================================");
                             System.out.println(" enter the deatils to delete a student record from student table : ");
                             System.out.println(" enter the student number to be deleted : ");
                             int sno=sc.nextInt();
                             //set query params
                             ps3.setInt(1, sno);
                             //add to batch
                             ps3.addBatch();
                             //execute the batch
                            int[] result= ps3.executeBatch();
                            if(result!=null)
         			        	System.out.println("records are deleted in emp table");
         			        else
         			        	System.out.println("records are not affected");
         			        //process the batch
         			        int sum=0;
         			        for(int i=0;i<result.length;++i)
         			        	sum=sum+result[i];
         			        System.out.println("no of records effected : "+sum);
	         			}//try(ps3)
	         		}//try(sc)
		}//try(con)
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}//main()

}//class
