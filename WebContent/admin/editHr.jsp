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
		<% HR hr= (HR)request.getAttribute("hr"); 
		
		%>
		<input type="hidden" id="hrurl"  name="hrurl" value="<%=request.getContextPath() %>/updateHr" />
		Name : <input type="text" id="hrname" name="hrname" value="<%=hr.getName() %>" /><br>
		Email : <input type="email" id="hremail" name="hremail" value="<%=hr.getEmail() %>" /><br>
		<input type="hidden" id="hrId" name="hrId"  value="<%=hr.getId() %>" /><br><br>
		 <button onclick="addHr()" >Update HR </button>
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
<script type="text/javascript">
function addHr() {
	
	var xhttp;
	  var hrname = document.getElementById("hrname").value;
	  var hremail = document.getElementById("hremail").value;
	  var hrId = document.getElementById("hrId").value;
	  var hrurl = document.getElementById("hrurl").value;
	  ClearHRFields();
	  xhttp=new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.status == 200) {
	      replaceHr(this);
	    }
	  };
	  var param="hrname="+hrname+"&hremail="+hremail+"&hrid="+hrId;
	  xhttp.open("GET", hrurl+"?"+param, true);
	  xhttp.send();
	}
	function replaceHr(xhttp) {
	  document.getElementById("hrMessageBox").innerHTML = xhttp.responseText;
	} 
	function ClearRecFields() {

	     document.getElementById("recname").value = "";
	     document.getElementById("recemail").value = "";
	     document.getElementById("recpasswd").value = "";
	     document.getElementById("recskills").value = "";
	}
	function addRec() {
		var xhttp;
		  var recname = document.getElementById("recname").value;
		  var recmail = document.getElementById("recemail").value;
		  var recpasswd = document.getElementById("recpasswd").value;
		  var recurl = document.getElementById("recurl").value;
		  ClearRecFields()
		  xhttp=new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.status == 200) {
		      replaceRec(this);
		    }
		  };
		  var param="recname="+recname+"&recemail="+recemail+"&recpasswd="+recpasswd+"$skills="+recskills;
		  xhttp.open("GET", recurl+"?"+param, true);
		  xhttp.send();
		}
		function replaceRec(xhttp) {
		  document.getElementById("recMessageBox").innerHTML = xhttp.responseText;
		} 
		
</script>
<%@include file="footer.jsp"%>