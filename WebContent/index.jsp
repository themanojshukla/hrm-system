<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HRM-System</title>
</head>
<body style="text-align: center;">
<h1>Welcom to HRM-System !!</h1><hr>
<span style="color: green">
<c:out value="${errorMessage }"></c:out>
</span>
<br><br><br><br><br><br>
<table width="100%">
<tr>
<th><a href="<%=request.getContextPath() %>/admin/">Admin Login</a></th>
<th><a href="<%=request.getContextPath() %>/hr/">HR Login</a></th>
<th><a href="<%=request.getContextPath() %>/recruiter/">Recruiter Login</a></th>
</tr>
</table>
</body>
</html>