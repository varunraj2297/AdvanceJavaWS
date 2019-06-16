<h1 style="color: red;text-align: center">Mega Car Sale</h1>
<jsp:useBean id="rotator" class="com.nt.service.AdvertisementService" scope="application"/>
<%
rotator.nextAdvertisement();
response.setHeader("refresh", "3");
%>
<center>
<a href="<jsp:getProperty property="link" name="rotator"/>">
    <img src="<jsp:getProperty property="image" name="rotator"/>" width="700px" height="400px ">
</a>
</center>


