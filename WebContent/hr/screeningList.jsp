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
		<th style="width: 25%">Email</th>
		<th style="width: 25%">Screening Status</th>
		<th style="width: 30%">Action</th>
		
		<!-- 
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
		 -->

	</tr>
	
		<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("screeningList");
				 	List<Recruiter> recruiterList = (List<Recruiter>) request.getAttribute("recruiterList");
				 	for(Candidate candidate: candidateList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName()%></td>
		<td><%=candidate.getEmail()%></td>
		<% String status = candidate.getStatus();
		if(status.equals("NEW")){
		out.print("<th>Pending</th>");
		out.print("<td>NONE</td>");
		}
		else if(status.equals("scrPassed")){
			out.print("<th style='color: green'>Screening Passed</th>");
			%>
			<td><form action='<%=request.getContextPath() %>/hr/assignRecruiter' method='POST'>
		<input type='hidden' name='status' value='INTERVIEW' />
		<input type='hidden' name='candidateId' value='<%=candidate.getId()%>' >
		<select name='recruiterId'>
		<% for(Recruiter recruiter: recruiterList){ %>
    <option value='<%=recruiter.getId() %>'><%=recruiter.getName() %> -- <%=recruiter.getSkills() %> </option>
    <%} %>
    </select>
    <input type='submit' value='ASSIGN '>
    </form></td>
			<%
			
		}
		else if(status.equals("scrFailed")){
			out.print("<th style='color : red'>Rejected</th>");
			out.print("<td>DELETE</td>");
		}
		else{
			out.print("<td>Some Problem Occurred.</td>");
			out.print("<td>NONE</td>");
		}
		%>
		
		<!--
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
		 -->
		</tr>
		<%
			}
		%>
</table>
<%@include file="footer.jsp"%>