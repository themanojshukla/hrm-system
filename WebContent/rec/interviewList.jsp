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
<hr>
<c:if test="${interviewList != null}">
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 22%">Name</th>
		<th style="width: 23%">Email</th>
		<th style="width: 13%">Age</th>
		<th style="width: 20%">Mobile</th>
		<th style="width: 20%">View ProfileS</th>
		<!-- 
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
		 -->

	</tr>

	<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("interviewList");
				 	System.out.println("JSP : " + candidateList);
				 	for(Candidate candidate: candidateList){
		%>
	<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName()%></td>
		<td><%=candidate.getEmail()%></td>
		<td><%=candidate.getAge()%></td>
		<td><%=candidate.getMobile()%></td>
		<td><a type="button" target="_blank"
			href="<%=request.getContextPath() %>/rec/candidates/interview/<%=candidate.getId()%>">View Profile</a></td>
	</tr>
	<%
		}
	%>
		
		
</table>
</c:if>
<c:if test="${interviewList == null}">
<h3 style="color: red">NO SCREENING CANDIDATES FOUND</h3>
</c:if>
<%@include file="footer.jsp"%>