package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllFormComponentsTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=null;
					
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		
		//getting parameter values from req obj and displaying on the web browser
		pw.println("<h1 style='color:red;text-align:center'>Gathered data from url query string</h1><br>");
		pw.println("Personal Details::<br>");
		pw.println("First Name::"+req.getParameter("fname")+"<br>");
		pw.println("Middle Name::"+req.getParameter("mname")+"<br>");
		pw.println("Last Name::"+req.getParameter("lname")+"<br>");
		pw.println("Father Name::"+req.getParameter("fathername")+"<br>");
		pw.println("Mother Name::"+req.getParameter("mothername")+"<br>");
		pw.println("Address::"+req.getParameter("addrs")+"<br>");
		pw.println("Permanent Address::"+req.getParameter("paddrs")+"<br>");
		pw.println("Landline::"+req.getParameter("landline")+"<br>");
		pw.println("Mobile::"+req.getParameter("mobile")+"<br>");
		pw.println("Date of Birth::"+req.getParameter("dob")+"<br>");
		pw.println("Place of Birth::"+req.getParameter("pob")+"<br>");
		pw.println("Gender::"+req.getParameter("gender")+"<br>");
		pw.println("Qualification::"+req.getParameter("qlfy")+"<br>");
		pw.println("Year of Passing::"+req.getParameter("yop")+"<br>");
		pw.println("Languages known::");
		for(String language:req.getParameterValues("language"))
			pw.println(language+" , ");
		pw.println("<br>");
		pw.println("Hobbies::");
		for(String hobby:req.getParameterValues("hobbies"))
			pw.println(hobby+" , ");
		pw.println("<br>");
		pw.println("About Yourself::"+req.getParameter("aboutyou")+"<br>");
		pw.println("Nationality::"+req.getParameter("nation")+"<br>");
		pw.println("Aadhar Card Number::"+req.getParameter("aadhar")+"<br>");
		pw.println("Pan Card Number::"+req.getParameter("pan")+"<br>");
		pw.println("Education Details:-<br>");
		pw.println("Sno::1<br>");
		pw.println("Qualification::"+req.getParameter("edu1qlfy")+"<br>");
		pw.println("Institute::"+req.getParameter("edu1inst")+"<br>");
		pw.println("Year of Passing::"+req.getParameter("edu1yop")+"<br>");
		pw.println("Marks::"+req.getParameter("edu1marks")+"<br>");
		pw.println("Sno::2<br>");
		pw.println("Qualification::"+req.getParameter("edu2qlfy")+"<br>");
		pw.println("Institute::"+req.getParameter("edu2inst")+"<br>");
		pw.println("Year of Passing::"+req.getParameter("edu2yop")+"<br>");
		pw.println("Marks::"+req.getParameter("edu2marks")+"<br>");
		pw.println("Sno::3<br>");
		pw.println("Qualification::"+req.getParameter("edu3qlfy")+"<br>");
		pw.println("Institute::"+req.getParameter("edu3inst")+"<br>");
		pw.println("Year of Passing::"+req.getParameter("edu3yop")+"<br>");
		pw.println("Marks::"+req.getParameter("edu3marks")+"<br>");
		pw.println("Work Experience<br>");
		pw.println("Sno::1<br>");
		pw.println("Company Address::"+req.getParameter("company1addrs")+"<br>");
		pw.println("Role::"+req.getParameter("company1role")+"<br>");
		pw.println("From::"+req.getParameter("company1join")+"<br>");
		pw.println("to::"+req.getParameter("company1resign")+"<br>");
		pw.println("Sno::2<br>");
		pw.println("Company Address::"+req.getParameter("company2addrs")+"<br>");
		pw.println("Role::"+req.getParameter("company2role")+"<br>");
		pw.println("From::"+req.getParameter("company2join")+"<br>");
		pw.println("to::"+req.getParameter("company2resign")+"<br>");
		pw.println("Sno::3<br>");
		pw.println("Company Address::"+req.getParameter("company3addrs")+"<br>");
		pw.println("Role::"+req.getParameter("company3role")+"<br>");
		pw.println("From::"+req.getParameter("company3join")+"<br>");
		pw.println("to::"+req.getParameter("company3resign")+"<br>");
		pw.println("Other Details<br>");
		pw.println("Job Type::"+req.getParameter("jobtype")+"<br>");
		pw.println("Date of Joining::"+req.getParameter("doj")+"<br>");
		pw.println("Time of Joining"+req.getParameter("doj")+"<br>");
		pw.println("Job Location::"+req.getParameter("joblocation")+"<br>");
		pw.println("Relocate::"+req.getParameter("relocate")+"<br>");
		pw.println("agree::"+req.getParameter("agree")+"<br>");
		
		
		pw.println("<a href='input.html'>home</a><br>");
		
		//close print stream obj
		pw.close();
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
