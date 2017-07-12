<%@include file="header.jsp"%>
<table  style="width: 100%;">
<tr>
<td style="width: 30%; text-align: center;"><span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/hr">Main Page</a>
		</span> </td>
		<td></td>
		<td></td>
		</tr>
</table>
<c:if test="${candidateInfo != null }">
<table border="1" style="width: 100%">
<%
Candidate candidate = (Candidate)request.getAttribute("candidateInfo");
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
Name : <%=candidate.getName() %><br><br>

Email : <%=candidate.getEmail() %><br><br>

Mobile : <%=candidate.getMobile() %><br><br>

Date OF Birth : <%=candidate.getDateOfBirth() %><br><br>

Age :  <%=candidate.getAge() %><br><br>

Address : <%=candidate.getAddress() %><br><br>

</th>
<td>
<%=new String(candidate.getResume()) %>
</td>
</tr>
</table>
</c:if>
<c:if test="${candidateInfo == null }">

<h3 style="color: red">NO SUCH CANDIDATE FOUND</h3>
</c:if>
<%@include file="footer.jsp"%>