<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.hrsystem.model.Candidate" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recruiter | HRM-System</title>
</head>
<c:if test="${recName == null || recID == null }">
<%
System.out.print("jsp red");
response.sendRedirect(request.getContextPath()+"/rec/");
%>
</c:if>

<body style="text-align: center;">
	<H2>Welcome to Recruiter Portal :: You are Logged in as <c:out value="${recName }" /> &nbsp;&nbsp;&nbsp; 
	<a href="<%=request.getContextPath() %>/recruiterLogout" >LOGOUT </a>
	</H2>
	<hr />