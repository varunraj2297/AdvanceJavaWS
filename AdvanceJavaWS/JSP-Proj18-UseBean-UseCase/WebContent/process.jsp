<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <jsp:useBean  id="dto" class="com.nt.dto.EmployeeDTO" scope="request"/>
    <jsp:setProperty property="*" name="dto"/>
    
    <jsp:useBean id="service" class="com.nt.service.EmployeeServiceImpl" scope="application"/>
    
    <%
    service.calculateGrossnNetSalaries(dto);
    %>
    
  eno::  <jsp:getProperty property="eno" name="dto"  /><br>
  ename::  <jsp:getProperty property="ename" name="dto"  /><br>
  job::   <jsp:getProperty property="job" name="dto"  /><br>
  sal::  <jsp:getProperty property="sal" name="dto"  /><br>
  grossSal::  <jsp:getProperty property="grossSal" name="dto"  /><br>
  netSal:: <jsp:getProperty property="netSal" name="dto"  />