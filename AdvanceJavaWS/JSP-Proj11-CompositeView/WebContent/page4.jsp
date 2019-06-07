<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<table style="height: 100%;width: 100%">
   <tr style="height: 20%">
      <td colspan="3"><jsp:include page="headerurl"/></td>
   <tr>
    <tr style="height: 70%">
        <td style="width: 30%"> 
             <%@ include file="menu.html" %>
        </td>
        <td style="width: 40%"> 
             <jsp:include page="entertainment.jsp"/>
             <br>
              <a href="page1.jsp">home</a>
        </td>
        <td style="width: 30%"> 
             <table rows="2" cols="1">
                <tr>
                   <td><jsp:include page="horoscope.jsp"/></td>
                </tr>
                <tr>
                   <td><jsp:include page="weather_report.jsp"/></td>
                </tr>
             </table>
        </td>
    </tr>
    <tr>
         <td colspan="3"><jsp:include page="footer.jsp"/></td>
     </tr>
</table>