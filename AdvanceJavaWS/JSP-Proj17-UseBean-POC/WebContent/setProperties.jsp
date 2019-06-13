<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="st" class="com.nt.bean.StudentBean" scope="application"/>
<%-- <b>Property values are created/located</b><br>
<jsp:setProperty property="sno" name="st" value="1000"/>
<jsp:setProperty property="sname" name="st" value="varun"/>
<jsp:setProperty property="result" name="st" value="pass"/>
<jsp:setProperty property="avg" name="st" value="99.99"/>
<b>Property values setted</b><br>
 --%>
 
 <%-- <b>Property values are created/located</b><br>
<jsp:setProperty property="sno" name="st" param="stno"/>
<jsp:setProperty property="sname" name="st" param="stname"/>
<jsp:setProperty property="result" name="st" param="stresult"/>
<jsp:setProperty property="avg" name="st" param="stavg"/>
<b>Property values setted</b><br> --%>

<b>Property values are created/located</b><br>
<jsp:setProperty property="*" name="st"/>
<b>Property values setted</b><br>
 



