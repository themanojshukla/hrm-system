<%@page import="com.hrsystem.model.HR"%>
<%@include file="header.jsp"%>
<table  style="width: 100%;">

<tr>
<td style="width: 30%; text-align: center;"><span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/rec">Main Page</a>
		</span> </td>
		<td></td>
		<td></td>
		</tr>
</table>
<c:if test="${candidateInfo != null}">
<table border="1" style="width: 100%">
<%
String email = null,
name = null,
mobile = null,
dateOFBirth = null,
age = null,
address = null,
res=null;
Candidate candidate= null;

Object o = request.getAttribute("candidateInfo");
candidate = (Candidate)o;
	name = candidate.getName();
	email = candidate.getEmail();
	mobile = ""+candidate.getMobile();
	dateOFBirth = ""+candidate.getDateOfBirth();
	age = ""+candidate.getAge();
	address = ""+candidate.getAddress();
	res = new String(candidate.getResume());

%>

<tr>
<th style="width: 35%">
Basic Info
</th>
<th style="width: 65%">
Resume
</th>
</tr>

<tr>
<th>
Name : <%=name %><br><br>

Email : <%=email %><br><br>

Mobile : <%=mobile %><br><br>

Date OF Birth : <%=dateOFBirth %><br><br>

Age :  <%=age %><br><br>

Address : <%=address %><br><br>

</th>
<td>

<%=res %>
</td>
</tr>

</table>
<form action="<%=request.getContextPath() %>/rec/candidates" method="post">
<input type="hidden" name="for" value="<%=request.getAttribute("for") %>"/>
<input type="hidden" name="candidateId" value="<%=candidate.getId() %>"/>
<% if("allowed".equals(request.getAttribute("allowed"))){ 

%>
<input type="radio" name="accept" value="YES"> Approve 
<input checked="checked" type="radio" name="accept" value="NO"> Reject
<br>
<input type="submit" value="Submit Decision"> 

<%} else {
	%>
<input type="radio" disabled="disabled" name="accept" value="YES"> Approve 
<input type="radio" disabled="disabled" name="accept" value="NO"> Reject
<br>
<input type="submit" disabled="disabled" value="Submit Decision">
<% 
}
/* else{
	out.print("<h3 style='color:red'>SO SUCH CANDIDATE FOUND.</h3>");
}
 */%>
 </c:if>
<c:if test="${candidateInfo == null}">
 <h3 style='color:red'>SO SUCH CANDIDATE FOUND.</h3>
 </c:if>
</form>
<%@include file="footer.jsp"%>