<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="NameEx.jsp">
	<center>
		Name:
		<input type="text" name="sname"/>
		<br/><br/>
		<input type="submit" value ="Show" />
		</center>
	</form>
	
		<%
		if(request.getParameter("sname")!=null){
			String sname= request.getParameter("sname");
			out.println("Student name: " +sname);	
		}
		%>

</body>
</html>