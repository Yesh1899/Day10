<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="beanBank" class="com.infinite.jdbc2.Bank" scope="page"></jsp:useBean>
	<jsp:setProperty property="*" name="beanBank"/>
Account No:<jsp:getProperty property="accountNo" name="beanBank"/><br><br/>
	First Name:<jsp:getProperty property="firstName" name="beanBank"/><br><br/>
	Last Name:<jsp:getProperty property="lastName" name="beanBank"/><br><br/>
	City:<jsp:getProperty property="city" name="beanBank"/><br><br/>
	State:<jsp:getProperty property="state" name="beanBank"/><br><br/>
	Amount:<jsp:getProperty property="amount" name="beanBank"/><br><br/>
	Cheq Facil:<jsp:getProperty property="cheqFacil" name="beanBank"/><br><br/>
	Account Type:<jsp:getProperty property="accountType" name="beanBank"/><br><br/>

</body>
</html>