<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%float bkprice=899.0f; %>
<jsp:forward page="b.jsp">
   <jsp:param  name="bkPrice" value="<%=bkprice%>"/>
   <jsp:param name="bkName" value="CRJ"/>
</jsp:forward>