<%@page import="com.hrsystem.model.Recruiter"%>
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
<c:if test="${newCandidateList != null }">
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 8%">Name</th>
		<th style="width: 10%">Email</th>
		<th style="width: 45%">
		Resume
		</th>
		<th style="width: 25%">Assign Recruiter for Screening</th>
		<!-- 
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
		 -->
	</tr>
	
		<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("newCandidateList");
				 	List<Recruiter> recruiterList = (List<Recruiter>) request.getAttribute("recruiterList");
				 	for(Candidate candidate: candidateList){
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName()%></td>
		<td><%=candidate.getEmail()%></td>
		<td>
		<div style="overflow:scroll; height:20%;">
		<%=new String(candidate.getResume())%>
		</div>
		</td>
		<!--<td><a href="<%=request.getContextPath() %>/hr/viewResume?id=<%=candidate.getId()%>">Resume</a></td> -->
		<td>
		<form action="<%=request.getContextPath() %>/hr/assignRecruiter" method="POST">
		<input type="hidden" name="status" value="NEW" />
		<input type="hidden" name="candidateId" value="<%=candidate.getId()%>" >
		<select name="recruiterId">
		<% for(Recruiter recruiter: recruiterList){ %>
    <option value="<%=recruiter.getId() %>"><%=recruiter.getName() %> -- <%=recruiter.getSkills() %> </option>
    <%} %>
    </select>
    <input type="submit" value="ASSIGN ">
    </form>
		</td>
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
<c:if test="${newCandidateList == null }">
<h3 style="color:red">NO NEW CANDIDATE FOUND</h3>
</c:if>
<%@include file="footer.jsp"%>