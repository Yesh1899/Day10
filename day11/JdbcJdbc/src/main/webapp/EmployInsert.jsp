<%@page import="com.infinite.JdbcJdbc.Employ"%>
<%@page import="com.infinite.JdbcJdbc.EmployDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form method="get" action="EmployInsert.jsp">
		<center>
				Employ name:
				<input type="text" name="name"><br/><br/>
					Department:
				<input type="text" name="dept"><br/><br/>
					Designation:
				<input type="text" name="desig"><br/><br/>
					Basic:
				<input type="number" name="basic"><br/><br/>
				<input type="submit" value="Insert"><br/><br/>
		</center>
		</form>
		<%
		if(request.getParameter("basic")!=null){
			EmployDAO dao = new EmployDAO();
		    Employ employ= new Employ();
		    employ.setName(request.getParameter("name"));
		    employ.setDept(request.getParameter("dept"));
		    employ.setDesig(request.getParameter("desig"));
		    employ.setBasic(Integer.parseInt(request.getParameter("basic")));
		    dao.addEmploy(employ);
	
		%>
		<jsp:forward page="EmployTable.jsp"></jsp:forward>
		<%
		}
		%>
</body>
</html>