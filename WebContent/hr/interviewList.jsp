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
<c:if test="${interviewList !=null }">
		
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 18%">Name</th>
		<th style="width: 15%">Email</th>
		<th style="width: 15%">View Profile</th>
		<th style="width: 20%">Interview Status</th>
		<th style="width: 30%">Action</th>
		
		<!-- 
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
		 -->
	</tr>
		<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("interviewList");
				 	List<Recruiter> recruiterList = (List<Recruiter>) request.getAttribute("recruiterList");
				 	
				 	for(Candidate candidate: candidateList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName()%></td>
		<td><%=candidate.getEmail()%></td>
		<td><a target ="_blank" 
		href="<%=request.getContextPath()%>/hr/candidates/<%=candidate.getId()%>">View Profile</a></td>
		<% String status = candidate.getStatus();
		if(status.equals("INTERVIEW")){
		out.print("<th>Status : Pending <br><hr>"
				+"Date : " + candidate.getInterview().getDate()+"<br>"+
				"Time : " + candidate.getInterview().getTime()+"<br>"+
				"Place : " + candidate.getInterview().getPlace()+"<br>"
		+"</th>");
		out.print("<td>NONE</td>");
		}
		else if(status.equals("intPassed")){
			out.print("<th style='color: green'>Interview Passed</th>");
			%>
			<td><form action='<%=request.getContextPath() %>/hr/assignRecruiter' method='POST'>
		<input type='hidden' name='status' value='FINAL' />
		<input type='hidden' name='candidateId' value='<%=candidate.getId()%>' >
	
    <input type='submit' value='Appoint for HR-Round'>
    </form></td>
			<%
			
		}
		else if(status.equals("intFailed")){
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
</c:if>
<c:if test="${interviewList == null }">
	<h3 style="color: red">NO SUCH CANDIDATE</h3>
</c:if>
<%@include file="footer.jsp"%>