<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.hrsystem.model.Candidate" %>
<%@ page import="com.hrsystem.model.Recruiter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HR | HRM-System</title>
</head>
<c:if test="${hrName == null || hrID == null }">
<%
response.sendRedirect(request.getContextPath()+"/hr/");
%>
</c:if>

<body style="text-align: center;">
	<H2>Welcome to HR Portal :: You are Logged in as <c:out value="${hrName }" /> &nbsp;&nbsp;&nbsp; 
	<a href="<%=request.getContextPath() %>/hrLogout" >LOGOUT </a>
	</H2>
	<hr />