<%@include file="header.jsp"%>
<table  style="width: 100%;">
<tr>
<td style="width: 50%; text-align: center;"><span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/admin">Add New HRs</a>
		</span> </td>
		<td style="width: 50%; text-align: center;"> <span style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/recruiterList">List of
					Recruiters</a>
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
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
	</tr>
	
		<%
			int i=1;
				 	List<HR> hrList = (List<HR>) request.getAttribute("hrList");
				 		for(HR hr: hrList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=hr.getName()%></td>
		<td><%=hr.getEmail()%></td>
		<td>
		<form method="POST" action="<%=request.getContextPath() %>/deleteHr">
		<input type="hidden" name="hrId" value="<%=hr.getId() %>">
		<input type="submit" value="DELETE" />
		</form>
		</td>
		<td>
		<form method="POST" action="<%=request.getContextPath() %>/editHr">
		<input type="hidden" name="hrId" value="<%=hr.getId() %>">
		<input type="submit" value="EDIT" />
		</form>
		</td>
		</tr>
		<%
			}
		%>
</table>
<%@include file="footer.jsp"%>