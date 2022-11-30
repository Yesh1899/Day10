<%@page import="com.infinite.JdbcJdbc.Employ"%>
<%@page import="java.util.List"%>
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
<%
EmployDAO dao = new EmployDAO();
List<Employ> employList= dao.showEmploy();
%>
<table border = "3">
<tr>
<th>Employ No</th>
<th>Employ Name</th>
<th>Department</th>
<th>Designation</th>
<th>Basic</th>
</tr>
<%
for(Employ employ:employList){

%>
<tr>
		<th><%=employ.getEmpno() %></th>
		<th><%=employ.getName() %></th>
		<th><%=employ.getDept() %></th>
		<th><%=employ.getDesig() %></th>
		<th><%=employ.getBasic() %></th>


</tr>
<%
 }
%>



</table>

</body>
</html>