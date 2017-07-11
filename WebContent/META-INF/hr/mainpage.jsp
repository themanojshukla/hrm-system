<%@include file="header.jsp"%>

<table border="1" style="text-align: center; border:1; width: 100%">

<tr>
<th style="width: 25%;">Actions</th>
<th style="width: 75%;">Add new Candidate</th>
</tr>

<tr>
<th style="width: 25%;">
<h3>
<br>
<a href="<%=request.getContextPath() %>/candidates">List of All Candidates</a>
<br><br><br>
<a href="<%=request.getContextPath() %>/candidates/new">New Candidates (Send for screening)</a>
<br><br><br>
<a href="<%=request.getContextPath() %>/candidates/screenings">Candidates already in Screening</a>
<br><br><br>
<a href="<%=request.getContextPath() %>/candidates/interviews">Candidates sent for Interview</a>
<br><br><br>
<a href="<%=request.getContextPath() %>/candidates/finalists">Final Candidates for HR-Round</a>
<br><br>
</h3>
</th>
<td style="width: 75%;">
<span style='color: <c:out value="${color }"  />'><c:out value="${candidateAddMessage }" /></span>
<% session.removeAttribute("candidateAddMessage"); %>
<br>
<form action="<%=request.getContextPath() %>/candidates" method="POST" enctype="multipart/form-data">
Enter Name : <input type="text" name="canname" /><br><br>
Enter Date of Birth : <input type="date" name="candateofbirth" /><br><br>
Enter Age : <input type="number" min="18" max="100" name="canage" /><br><br>
Enter email : <input type="email" name="canemail" /><br><br>
Enter Mobile : <input type="number" name="canmobile" /><br><br>
Enter address : <textarea  name="canaddress" placeholder="Enter Complete Address"></textarea><br><br>
Attach Resume : <input type="file" name="canresume" /><br><br>
<input type="reset" value="Reset"/> &nbsp;&nbsp;&nbsp; <input type="submit" value="Add New Candidate" /><br><br>
</form>
</td>
</tr>
</table>

<%@include file="footer.jsp"%>