<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%String bookName=request.getParameter("bkName");
         float bookPrice=Float.parseFloat((request.getParameter("bkPrice")) );%>
         <b>Book Name::<b><%=bookName  %><br>
         <b>Book Price ::<b><%=bookPrice  %>