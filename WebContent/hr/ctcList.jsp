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
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 18%">Name</th>
		<th style="width: 20%">Email</th>
		<th style="width: 10%">Age</th>
		<th style="width: 20%">Mobile</th>
		<th style="width: 30%">View Profile</th>
		
		<!-- 
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
		 -->

	</tr>
	
		<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("ctcList");
				 	for(Candidate candidate: candidateList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName() %></td>
		<td><%=candidate.getEmail() %></td>
		<td><%=candidate.getAge() %></td>
		<td><%=candidate.getMobile() %></td>
		<td>
			<a target ="_blank" 
		href="<%=request.getContextPath()%>/hr/candidates/ctcs/<%=candidate.getId()%>">View Profile</a>
		</td>
		</tr>
		<%
		  }
		%>
</table>
<%@include file="footer.jsp"%>