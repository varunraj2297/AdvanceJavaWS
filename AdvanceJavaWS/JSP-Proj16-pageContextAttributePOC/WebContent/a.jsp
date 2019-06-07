<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    /*  pageContext.setAttribute("attr1","val1");  //default scope is page scope no need to mention
     pageContext.setAttribute("attr2","val2",pageContext.REQUEST_SCOPE);
     pageContext.setAttribute("attr3","val3",pageContext.SESSION_SCOPE);
     pageContext.setAttribute("attr4","val4",pageContext.APPLICATION_SCOPE); */
     
     pageContext.setAttribute("attr1","val1");  //default scope is page scope no need to mention
     request.setAttribute("attr2","val2");
     session.setAttribute("attr3","val3");
     application.setAttribute("attr4","val4"); 
%>
     <jsp:forward page="b.jsp"/>
   