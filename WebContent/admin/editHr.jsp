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
				<a href="<%=request.getContextPath()%>/admin/hrs">List of
					HRs</a>
		</span> 
		<br><br><br>
		 <span
			style=" font-size: x-large;">
				<a href="<%=request.getContextPath()%>/admin/recruiters">List of
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
		<form method="POST" action="<%=request.getContextPath() %>/admin/updateHr">
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
		<div id="addRec"><span style='color: <c:out value="${color }"  />'><c:out value="${recAddMessage }" /></span>
		<% session.removeAttribute("recAddMessage"); session.removeAttribute("color");%>
		<form id="addHr" action="<%=request.getContextPath() %>/admin/addRec" method="POST">
		
		Name : <input type="text"  name="recname" /><br>
		Skills : <input type="text" name="recskills" /><br>
		Email : <input type="email" name="recemail" /><br>
		Password : <input type="password" name="recpasswd" /><br><br>
		<input type="reset" value="Reset" /> &nbsp; <input type="submit" value="Add New Recruiter" />
		
		</form>
		</div>
		</td>
	</tr>
	
</table>
<%@include file="footer.jsp"%>