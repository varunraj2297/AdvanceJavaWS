<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
page scope attribute value::<%=pageContext.getAttribute("attr1")%><br>
request scope attribute value::<%=pageContext.getAttribute("attr2",pageContext.REQUEST_SCOPE)%><br>
session scope attribute value::<%=pageContext.getAttribute("attr3",pageContext.SESSION_SCOPE)%><br>
application scope attribute value::<%=pageContext.getAttribute("attr4",pageContext.APPLICATION_SCOPE)%>
