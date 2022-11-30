<%@page import="com.infinite.jdbc2.BankDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="CreateAccount.jsp">
		<center>
			First Name : 
			<input type="text" name="firstName" /> <br/><br/>
				Last Name : 
			<input type="text" name="lastName" /> <br/><br/>
				City : 
			<input type="text" name="city" /> <br/><br/>
				State : 
			<input type="text" name="state" /> <br/><br/>
			Amount:
	<input type="number" name="amount"/><br><br/>
			Cheq Facil : 
			<select name="cheqFacil">
				<option value="Yes">Yes</option>
				<option value="No">No</option>
			</select> <br/><br/>
			Account Type : 
			<select name="accountType">
				<option value="Current">Current</option>
				<option value="Savings">Savings</option>
			</select> <br/><br/>

			<input type="submit" value="Add Account" />
		</center>
	</form>
	<%
		if (request.getParameter("amount")!=null) {
			BankDAO dao = new BankDAO();
	%>
		<jsp:useBean id="beanBank" class="com.infinite.jdbc2.Bank" scope="page" />
		<jsp:setProperty property="*" name="beanBank"/>
	<%=dao.createAccount(beanBank) %>
	<%
		}
	%>
	
</body>
</html>