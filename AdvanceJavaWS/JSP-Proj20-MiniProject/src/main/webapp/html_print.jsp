<%@page import="java.util.ArrayList"%>
<%@page import="com.nt.dto.BookDetailsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%
   List<BookDetailsDTO> listDtos=null;
   listDtos=(List<BookDetailsDTO>)request.getAttribute("booksList");
 
   if(listDtos!=null){
%>
<h1 style="color: red;text-align: center;"> Search Results </h1>
<table  border="1" align="center" style="background-color: cyan;">
    <tr><th>SerNo</th><th>BookId</th><th>BookName</th><th>Author</th><th>Price</th><th>Status</th><th>Category</th></tr>
    <%for(BookDetailsDTO dto:listDtos){ %>
     <tr>
             <td><%=dto.getSerNo() %></td>
            <td><%=dto.getBookId() %></td>
             <td><%=dto.getBookName() %></td>
             <td><%=dto.getAuthor() %></td>
             <td><%=dto.getPrice() %></td>
             <td><%=dto.getStatus() %></td>
             <td><%=dto.getCategory() %></td>
     </tr>
    <%} %>    
</table>
<%}
   else{
%>
      <h1>No Records Found</h1>
      <%} %>
      
      <a href="home.html">home</a>
      <a href="javascript:showPrint()">print</a>

      <script type="text/javascript">
          function showPrint(){
        	  frames.focus();
        	  frames.print();
          }
      
      </script>