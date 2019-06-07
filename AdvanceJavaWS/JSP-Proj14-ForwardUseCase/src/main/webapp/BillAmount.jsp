<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
    String itemName=request.getParameter("itemName");
    float itemPrice=Float.parseFloat(request.getParameter("itemPrice"));
    float itemQuantity=Float.parseFloat(request.getParameter("itemQuantity"));
    
    float billAmount=itemPrice*itemQuantity;
    if(billAmount>=50000.0f){ 
    %>
          <jsp:forward page="Discount.jsp">
               <jsp:param name="billAmount" value="<%=billAmount %>" />
          </jsp:forward>
   <%}else{%>
            <center>
            <h1 style="color:red;text-align:center">Bill Amount</h1>
            <b>item Name::</b><%=itemName%><br><br>
            <b>item Price::</b><%=itemPrice%><br><br>
            <b>item Quantity::</b><%=itemQuantity%><br><br>
            <b>---------------------------------------------------------------</b><br>
            <b>Final Amount::</b><%=billAmount%><br><br>
            <h2 style="color:red;text-align:center">Thank you <img height="100px" src="namaste.jpg"> Visit Again.</h2><br><br>
             <a href="form.html"><img height="100px" src="home.jpg"></a>
             </center>
      <%}%>     