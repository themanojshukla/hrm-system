<%@page import="com.hrsystem.model.Recruiter"%>
<%@include file="header.jsp"%>
<table  style="width: 100%;">
<tr>
<td style="width: 30%; text-align: center;"><span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/admin">Add New HRs</a>
		</span> </td>
		<td style="width: 30%; text-align: center;"> <span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/admin">Add New Recruiter</a>
		</span></td>
		<td style="width: 40%; text-align: center;"> <span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/hrList">List of
					HRs</a>
		</span></td>
		</tr>
</table>
<hr>
<c:out value="${deleteMessage }"/>
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 28%">Name</th>
		<th style="width: 25%">Email</th>
		<th style="width: 10%">Skills</th>
		<th style="width: 10%">Delete</th>
		<th style="width: 10%">Edit</th>
	</tr>
	
		<%
			int i=1;
				 	List<Recruiter> recList = (List<Recruiter>) request.getAttribute("recList");
				 		for(Recruiter rec: recList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=rec.getName()%></td>
		<td><%=rec.getEmail()%></td>
		<td><%=rec.getSkills()%></td>
		<td>
		<form method="POST" action="<%=request.getContextPath() %>/deleteRec">
		<input type="hidden" name="recId" value="<%=rec.getId() %>">
		<input type="submit" value="DELETE" />
		</form>
		</td>
		<td>
		<form method="POST" action="<%=request.getContextPath() %>/editRec">
		<input type="hidden" name="hrId" value="<%=rec.getId() %>">
		<input type="submit" value="EDIT" />
		</form>
		</td>
		</tr>
		<%
			}
		%>
</table>
<%@include file="footer.jsp"%>