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
				<a href="<%=request.getContextPath()%>/recruiterList">List of
					Recruiters</a>
		</span>
		</td>

		<td style="width: 40%">
		<div id="hrMessageBox">
		<div id="addHr" >
		<span style="color : <%=session.getAttribute("color") %>"><%=session.getAttribute("editHrMessage") %></span>
		<% session.removeAttribute("color"); session.removeAttribute("editHrMessage");  %>
		<% HR hr= (HR)session.getAttribute("hr"); 
		
		%>
		<form method="POST" action="<%=request.getContextPath() %>/updateHr">
		New Name : <input type="text" id="hrname" name="hrname" value="<%=hr.getName() %>" /><br>
		New Email : <input type="email" id="hremail" name="hremail" value="<%=hr.getEmail() %>" /><br>
		<input type="hidden" id="hrId" name="hrId"  value="<%=hr.getId() %>" /><br><br>
		<% session.removeAttribute("hr"); %>
		<input type="submit" value="Update HR" />
		 </form>
		</div>
		</div>
		</td >

		<td style="width: 45%">
		<div id="addRec">
		<div id="recMessageBox"></div>
		Name : <input type="text"  name="recname" /><br>
		Skills : <input type="text" name="recskills" /><br>
		Email : <input type="email" name="recemail" /><br>
		Password : <input type="password" name="recpaawd" /><br><br>
		<button  onclick="ClearRecFields()">Reset</button> &nbsp; <button onclick="addRec()" >Add to Recruiter</button>
		
		</div>
		</td>
	</tr>
	
</table>
<%@include file="footer.jsp"%>