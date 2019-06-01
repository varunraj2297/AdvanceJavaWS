<%@page import="java.util.Date"%>
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%!public static  String generateWishMessage(String user){
             java.util.Calendar cal=java.util.Calendar.getInstance();
             int hours=cal.get(java.util.Calendar.HOUR_OF_DAY);
             if(hours<12)
            	 return "GM "+user;
             else  if(hours<16)
            	 return "GA "+user;
             else  if(hours<20)
            	 return "GE "+user;
             else 
            	 return "GN"+user;
}
%>

<h1 style="color: red;text-align: center;">Welcome To JSP</h1>
<br><br>
<b>Date and Time:::</b>
<%=new java.util.Date()%>
<hr>

<%String user="raja";%>
Wish Message::<%=generateWishMessage(user)%>