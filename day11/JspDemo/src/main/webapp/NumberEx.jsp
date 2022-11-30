<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="NumberEx.jsp">
	<center>
		firstNumber:
		<input type="number" name="firstno"/>
		<br/><br/>
		secondNumber:
		<input type="number" name="secondno"/>
		<br/><br/>
		<input type="submit" value ="Calculation" />
		</center>
	</form>
	
		<%
		if(request.getParameter("firstno")!=null&&request.getParameter("secondno")!=null){
			int firstNo,secondNo,result;
			firstNo=Integer.parseInt(request.getParameter("firstno"));
			secondNo=Integer.parseInt(request.getParameter("secondno"));
			result=firstNo+secondNo;
			out.println("sum is " +result +"<br/>");
		}
		%>

</body>
</html>