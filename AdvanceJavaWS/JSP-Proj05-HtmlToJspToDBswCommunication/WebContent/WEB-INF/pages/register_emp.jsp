<%@page import="java.sql.*"%>

<%! 
   private static final String INSERT_QUERY="INSERT INTO EMPLOYEE VALUES(EID_SEQ11.NEXTVAL,?,?,?)";
   private static final String GET_EMPS="SELECT ENO,ENAME,JOB,SAL FROM EMPLOYEE";

   ServletConfig cfg=null;

   Connection con=null;
   PreparedStatement ps1=null,ps2=null;
   ResultSetMetaData rsmd=null;
   ResultSet rs=null;
   int count=0;
   public void jspInit(){
       cfg=getServletConfig();
   
     try{
        Class.forName(cfg.getInitParameter("driver"));
        con=DriverManager.getConnection(cfg.getInitParameter("url"),cfg.getInitParameter("user"),cfg.getInitParameter("pass"));
        ps1=con.prepareStatement(INSERT_QUERY);
        ps2=con.prepareStatement(GET_EMPS);
     }
     catch(Exception e){
    	 e.printStackTrace();
     }
   }
%>

<%
    String s1=null;
    s1=request.getParameter("s1");
    if(s1.equalsIgnoreCase("register")){
        String ename=null,job=null;
        int sal=0;
        //read form data
        ename=request.getParameter("ename");
        job=request.getParameter("job");
        sal=Integer.parseInt(request.getParameter("sal"));
        //set query param values
        ps1.setString(1,ename);
        ps1.setString(2,job);
        ps1.setInt(3,sal);
        //execute the query
    	count=ps1.executeUpdate();
        if(count==0){%>
        	<h1 style="text-align: center;color: red;">Employee Registration Failed</h1>
      <%}
        else{%>
             <h1 style="text-align: center;color: green;">Employee Registration Succeded</h1>
<%    }
    }
    else{
        //execute the query
       rs=ps2.executeQuery();
        rsmd=rs.getMetaData();
        count=rsmd.getColumnCount();
        %>
        <h1 style="text-align: center;color: blue;">Employee Details</h1>
        <table border="1" align="center" style="color:red; background-color: yellow;">
           <tr>
          <%  for(int i=1;i<=count;i++){ %>
        	        <td><%=rsmd.getColumnLabel(i)%></td>
             <%}%>
           </tr>
               <%while(rs.next()) {%>
              <tr> 
                    <%for(int i=1;i<=count;i++){ %>            
                        <td><%=rs.getString(i) %></td>
                     <%} %>
              </tr>
              <%} %>
         </table>
    <%}%>
<a href="register.html">Home</a>

<%! public void jspDestroy(){
	      try{
	    	  if(ps1!=null){
	    		  ps1.close();
	    	  }
	      }
	      catch(SQLException se){
	         se.printStackTrace();
	      }
	      try{
	    	  if(ps2!=null){
	    		  ps2.close();
	    	  }
	      }
	      catch(SQLException se){
	         se.printStackTrace();
	      }
	      try{
	    	  if(con!=null){
	    		  con.close();
	    	  }
	      }
	      catch(SQLException se){
	         se.printStackTrace();
	      }
	
	}%>

