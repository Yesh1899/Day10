<%@page import="com.infinite.jdbc2.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<form method="get" action="DepositAccount.jsp">
		<center></center>
		Account No:
	<input type="number" name="accountNo"/><br><br/>
		TranAmount:
	<input type="number" name="tranAmount"/><br><br/>
		<input type="submit" value="Show"/><br><br/>
	
	
	</center>
	
	
	
	
	
	</form>
		<%
		if (request.getParameter("accountNo")!=null) {
			BankDAO dao = new BankDAO();
	%>
		<jsp:useBean id="beanBank" class="com.infinite.jdbc2.Bank" scope="page" />
		<jsp:setProperty property="accountNo" name="beanBank"/>
		<jsp:setProperty property="tranAmount" name="beanBank"/>
	<%=dao.depositAccount(beanBank.getAccountNo(),beanBank.getAmount())%>
	<%
		}
	%>

</body>
</html>