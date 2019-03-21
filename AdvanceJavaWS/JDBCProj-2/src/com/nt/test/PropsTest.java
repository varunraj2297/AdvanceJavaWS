package com.nt.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try {
			is=new FileInputStream("src/com/nt/commons/personalDetails.properties");
			props=new Properties();
			props.load(is);
			System.out.println("Name::"+props.getProperty("name"));
			System.out.println("Age::"+props.getProperty("age"));
			System.out.println("Course::"+props.getProperty("course"));	
		}//try 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/*
 O/P:-
Name::varun
Age::22
Course::java
*/