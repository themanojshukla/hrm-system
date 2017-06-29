<%@page import="com.hrsystem.model.Recruiter"%>
<%@include file="header.jsp"%>
<table border="1" style=" width:100%;  border-top: none; border-bottom:none;  border: medium;">
<tr>
<th>View List</th>
<th>Edit Details of HR</th>
<th>Add New Recruiter</th>
</tr>
	<tr>
		<td style="width: 15%">
		<span
			style="font-size: x-large;">
				<a href="<%=request.getContextPath()%>/hrList">List of
					HRs</a>
		</span> 
		<br><br><br>
		 <span
			style=" font-size: x-large;">
				<a href="<%=request.getContextPath()%>/recList">List of
					Recruiters</a>
		</span>
		</td>

		<td style="width: 40%">
		<span style='color: <c:out value="${color }"  />'><c:out value="${hrAddMessage }" /></span>
		<% session.removeAttribute("hrAddMessage"); %>
		<form id="addHr" action="<%=request.getContextPath() %>/addHr" method="POST">
		Name : <input type="text" id="hrname" name="hrname" /><br>
		Email : <input type="email" id="hremail" name="hremail" /><br>
		Password : <input type="password" id="hrpasswd" name="hrpasswd" /><br><br>
		<input type="reset" value="Reset" /> &nbsp; <input type="submit" value="Add New HR" />
		</form>
		</td >


		<td style="width: 45%">
		<div id="addRec">
		<span style="color : <%=session.getAttribute("color") %>"><%=session.getAttribute("editRecMessage") %></span>
		<% session.removeAttribute("color"); session.removeAttribute("editRecMessage");  %>
		<% Recruiter recruiter= (Recruiter)session.getAttribute("recruiter"); 
		
		%>
		<form id="addHr" action="<%=request.getContextPath() %>/updateRec" method="POST">
		
		New Name : <input type="text"  name="recname" value="<%=recruiter.getName() %>"/><br>
		New Skills : <input type="text" name="recskills" value="<%=recruiter.getSkills() %>"/><br>
		New Email : <input type="email" name="recemail" value="<%=recruiter.getEmail() %>" /><br>
		<input type="hidden" id="recId" name="recId"  value="<%=recruiter.getId() %>" /><br><br>
		<% session.removeAttribute("recruiter"); %>
		
		 <input type="submit" value="Update Recruiter" />
		
		</form>
		</div>
		</td>
	</tr>
	
</table>
<%@include file="footer.jsp"%>