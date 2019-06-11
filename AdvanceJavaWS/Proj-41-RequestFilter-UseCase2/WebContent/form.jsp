<h1 style="color: red;text-align: center;">Addition</h1>
<form action="sumurl" method="POST">
    value1  ::<input type="text" name="t1"><br>
    value2  ::<input type="text" name="t2"><br>
    <input type="submit" value="add">
    <h1 style="color: red;text-align: center;">Request Count :: ${count}</h1>   
    <%!HttpSession ses=null;
           int cnt=0;%>
    <%ses=request.getSession(); 
          cnt=(int)ses.getAttribute("count");
    %>
    <h1 style="color: red;text-align: center;">Request Count :: <%=cnt %></h1>
</form>