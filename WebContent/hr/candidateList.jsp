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
<hr>
<c:if test="${candidateList != null }">
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 20%">Name</th>
		<th style="width: 20%">Email</th>
		<th style="width: 13%">Age</th>
		<th style="width: 15%">Mobile</th>
		<th style="width: 11%">View Profile</th>
		<th style="width: 10%">Delete</th>
		<th style="width: 9%">Edit</th>
	</tr>
	
		<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("candidateList");
				 		for(Candidate candidate: candidateList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName()%></td>
		<td><%=candidate.getEmail()%></td>
		<td><%=candidate.getAge()%></td>
		<td><%=candidate.getMobile()%></td>
		<td><a type="button" target="_blank" href="<%=request.getContextPath() %>/hr/candidates/<%=candidate.getId() %>" >View Profile</a></td>
		<td>
		<form method="POST" action="<%=request.getContextPath() %>/hr/deleteCandidate">
		<input type="hidden" name="candidateId" value="<%=candidate.getId() %>">
		<input type="submit" value="DELETE" />
		</form>
		</td>
		<td>
		<form method="POST" action="<%=request.getContextPath() %>/hr/editCandidate">
		<input type="hidden" name="candidateId" value="<%=candidate.getId() %>">
		<input type="submit" value="EDIT" />
		</form>
		</td>
		</tr>
		<%
			}
		%>
</table>
</c:if>
<c:if test="${candidateList == null }">
<h3 style="color:red">NO CANDIDATE FOUND</h3>
</c:if>
<%@include file="footer.jsp"%>