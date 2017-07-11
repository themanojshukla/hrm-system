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
<c:if test="${finalList != null }" >
<table border="1"
	style="width: 100%; border-top: none; border-bottom: none; border: medium;">
	<tr>
		<th style="width: 2%">Sr.</th>
		<th style="width: 13%">Name</th>
		<th style="width: 15%">Email</th>
		<th style="width: 15%">View Profile</th>
		<th colspan="2" style="width: 55%">Action</th>
		
		<!-- 
		<th style="width: 15%">Delete</th>
		<th style="width: 15%">Edit</th>
		 -->

	</tr>
	
		<%
			int i=1;
				 	List<Candidate> candidateList = (List<Candidate>) request.getAttribute("finalList");
				 	System.out.print("HJK"+candidateList.toString());
				 	for(Candidate candidate: candidateList){
				 		
		%>
		<tr>
		<td><%=i++%></td>
		<td><%=candidate.getName()%></td>
		<td><%=candidate.getEmail()%></td>
		<td><a target ="_blank" 
		href="<%=request.getContextPath()%>/hr/candidates/<%=candidate.getId()%>">View Profile</a></td>
		
		<td><Button onclick="show(<%=candidate.getId()%>)">Accept and ADD CTC Detail</button>
		<div style="display: none;" id="<%=candidate.getId()%>">
			<form action="<%=request.getContextPath() %>/hr/addCtc" method="POST" >
				Basic : <input type="text" name="basic" onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46 || event.charCode == 0' />
			<br>
			HRA : <input type="text" name="hra" onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46 || event.charCode == 0' />
			<br>
			DA : <input type="text" name="da" onkeypress='return (event.charCode >= 48 && event.charCode <= 57) || event.charCode == 46 || event.charCode == 0' />
				<input type="hidden" name="candidateID" value="<%=candidate.getId()%>)" />
			
			<br>
			<input type="submit" value="ADD CTC"/> 
			</form>
		</div>
		</td>
		<td>Delete</td>
		</tr>
		<%-- <% String status = candidate.getStatus();
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
		%> --%>
		
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
<c:if test="${finalList == null }" >
<h3 style="color: red">NO SUCH CANDIDATES FOUND</h3>
</c:if>
<script type="text/javascript">
function show(id){
	var x = document.getElementById(id);
    if (x.style.display === 'none') {
    	x.style.display = 'block';
    } else {
    	x.style.display = 'none';
    }
	
}
</script>
<%@include file="footer.jsp"%>