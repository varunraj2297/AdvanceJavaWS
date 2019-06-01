<jsp:declaration>
<![CDATA[
             public static  String generateWishMessage(String user){
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
             ]]>
</jsp:declaration>

<h1 style="color: red;text-align: center;">Welcome To JSP</h1>
<br><br>
<b>Date and Time:::</b>
<jsp:expression>new java.util.Date()</jsp:expression>
<hr>

<jsp:scriptlet>String user="raja";</jsp:scriptlet>
Wish Message::<jsp:expression>generateWishMessage(user)</jsp:expression>