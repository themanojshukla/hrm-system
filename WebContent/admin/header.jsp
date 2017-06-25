<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin | HRM-System</title>
</head>
<c:if test="${adminName == null }">
<%
response.sendRedirect(request.getContextPath()+"/admin");
%>
</c:if>

<body style="text-align: center;">
	<H2>Welcome to Admin Portal :: You are Looged in as <c:out value="${adminName }" /> &nbsp;&nbsp;&nbsp; 
	<a href="<%=request.getContextPath() %>/admin/logout" >LOGOUT </a>
	</H2>
	<hr />