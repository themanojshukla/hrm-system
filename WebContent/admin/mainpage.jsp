<%@include file="header.jsp"%>
<table border="1" style=" width:100%;  border-top: none; border-bottom:none;  border: medium;">
<tr>
<th>View List</th>
<th>Add New HR</th>
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
		<span style='color: <c:out value="${color }"  />'><c:out value="${recAddMessage }" /></span>
		<% session.removeAttribute("recAddMessage"); session.removeAttribute("color");%>
		<form id="addHr" action="<%=request.getContextPath() %>/addRec" method="POST">
		
		Name : <input type="text"  name="recname" /><br>
		Skills : <input type="text" name="recskills" /><br>
		Email : <input type="email" name="recemail" /><br>
		Password : <input type="password" name="recpasswd" /><br><br>
		<input type="reset" value="Reset" /> &nbsp; <input type="submit" value="Add New Recruiter" />
		
		</form>
		</td>
	</tr>	
</table>

<%@include file="footer.jsp"%>