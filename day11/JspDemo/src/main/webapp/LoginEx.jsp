<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="get" action="LoginEx.jsp">
<center>
user name:
<input type="text" name="userName"/><br/>
password:
<input type="text" name="passCode"/><br/>
<input type="submit" name="Login"/><br/>
</center>
</form>
<%
if(request.getParameter("userName")!=null&&request.getParameter("passCode")!=null){
	String user,pwd;
	user=request.getParameter("userName");
	pwd=request.getParameter("passCode");
	if(user.equals("Infinite")&&pwd.equals("Infinite")){
%>
<jsp:forward page="Menu.jsp"></jsp:forward>
<jsp:include page="LoginEx.jsp"></jsp:include>

<%
	}else{
out.println("invalid credentials");
	}
}
%>
</body>
</html>