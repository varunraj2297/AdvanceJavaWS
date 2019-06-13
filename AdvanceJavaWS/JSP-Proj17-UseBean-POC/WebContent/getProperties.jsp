<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="st" class="com.nt.bean.StudentBean" scope="application"/>
Sno:::<jsp:getProperty property="sno" name="st" /><br>
Sname:::<jsp:getProperty property="sname" name="st" /><br>
Result:::<jsp:getProperty property="result" name="st" /><br>
Average:::<jsp:getProperty property="avg" name="st" /><br>




