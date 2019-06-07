<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
     float billAmount=Float.parseFloat(request.getParameter("billAmount"));
     float discount=billAmount*0.2f;
     float finalAmount=billAmount-discount;
%>
    <center>
          <h1 style="color:red;text-align:center">Bill Amount</h1>
            <b>item Name::</b><%=request.getParameter("itemName")%><br><br>
            <b>item Price::</b><%=request.getParameter("itemPrice")%><br><br>
            <b>item Quantity::</b><%=request.getParameter("itemQuantity")%><br><br>
            <b>Bill Amount::</b><%=billAmount%><br><br>
            <b>Discount::</b><%=discount %><br><br>
            <b>-------------------------------------------------</b><br><br>
            <b>Final Amount::</b><%=finalAmount %><br><br>
            
            <h2 style="color:red;text-align:center">Thank you <img height="100px" src="namaste.jpg"> Visit Again.</h2><br><br>
             <a href="form.html"><img height="100px" src="home.jpg"></a>
         </center>