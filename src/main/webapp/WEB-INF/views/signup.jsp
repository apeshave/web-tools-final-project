<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<head>
<style>
.nonehidden
{
	display: block;
}

.hidden
{
	display: none;
}

body {
	text-align: center;
	font-size: 18px;
	font-family: serif;
}

.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

.err {
	color: #ff0000;
}

.textbox {
	
width: 200px;
height: 18px;
border-radius: 5px;
border: 1px solid #CCC;
padding: 8px;
font-weight: 200;
font-size: 15px;
font-family: Verdana;
box-shadow: 1px 1px 5px #CCC;

}
</style>
<script>
function checkIt() {
	 var status = confirm('Are you sure and continue with the data?');
	 if(status == true)
		 return true;
	 else
		 return false;
	}
</script>
</head>
<body>
<script>
$(document).ready(function() {

	var vaa = sessionScope.get("item");
	//$('#' + sessionScope.get("item")).show();
	$('#'+vaa).toggleClass('nonehidden');
	$('#' + vaa).show();
});
</script>

<select id="selection" name="abc">
<option value="Select">Please Select...</option>
<option value = "Doctor">Doctor</option>
<option value = "Nurse">Nurse</option>
<option value = "Manufacturer">Manufacturer</option>
</select>

<script>
$('#selection').on('change', function() {
var val = $(this).val();
$('#Doctor').hide();
$('#Nurse').hide();
$('#Manufacturer').hide();
$('#' + val).show();
});
</script>


<%-- <form:form modelAttribute="collectiveData" commandName="collectiveData">  --%>
<div id="Doctor" class="hidden">
<table>
<form:form modelAttribute="doctor" onsubmit="return checkIt()" action = "doctorSave.htm" method="POST">
<tr>
<td align="left" width="30%" style="margin-right: 50px" >
	<table>
		<tr>
			<td>Username:</td>
			<td><form:input cssClass="textbox" path="userAccount.username" placeholder="Username" />
			<br />  <form:errors path="userAccount.username" cssClass="err"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:password cssClass="textbox" path="userAccount.password" placeholder="Password" />
			<br /><form:errors path="userAccount.password" cssClass="error"/></td>
		</tr>
	</table>
</td>
<td align="left" width="40%">
	<table>
		<tr>
			<td>First Name : </td>
				<td><form:input cssClass="textbox" path="person.firstName" placeholder="First Name"/> 
				    <br /><form:errors path="person.firstName" cssClass="error" />
				</td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td> <form:input cssClass="textbox" path="person.lastName" placeholder="Last Name"/>  
			<br /><form:errors path="person.lastName" cssClass="error" /> 
			</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input type="email" cssClass="textbox" path="person.email" placeholder="Email"/></td> 
		</tr>
		<tr>
			<td> Contact #:</td>
			<td> <form:input cssClass="textbox" path="person.contactNo" placeholder="Contact No" /></td></tr>
	</table>
</td>
<td align="left" width="30%">
	<table>
		<tr>
			<td>Line 1:</td>
			<td><form:input cssClass="textbox" path="person.address.line1" placeholder="Line 1" />  
			<br /><form:errors path="person.address.line1" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Line 2:</td>
			<td><form:input cssClass="textbox" path="person.address.line2" placeholder="Line 2" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><form:input cssClass="textbox" path="person.address.city" placeholder="City" />  
			<br /><form:errors path="person.address.city" cssClass="error" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><form:input cssClass="textbox" path="person.address.state" placeholder="State" /> 
			<br /><form:errors path="person.address.state" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td> <form:input cssClass="textbox" path="person.address.country" placeholder="County" />
			<br /><form:errors path="person.address.country" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Zip Code:</td>
			<td> <form:input cssClass="textbox" path="person.address.zip" placeholder="Zip Code" /> 
			</td> 		
		</tr>
	</table>			
</td>
</tr>				
<tr>
	<td colspan="2"><input type="submit" value="Submit"/></td>
</tr>
</form:form>
</table>
</div>
<div id="Nurse" class="hidden">
<table>
<form:form modelAttribute="nurse" action = "nurseSave.htm" method="POST">
<tr>
<td align="left" width="30%" style="margin-right: 50px" >
	<table>
		<tr>
			<td>Username:</td>
			<td><form:input cssClass="textbox" path="userAccount.username" placeholder="Username" />
			<br />  <form:errors path="userAccount.username" cssClass="err"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:password cssClass="textbox" path="userAccount.password" placeholder="Password" />
			<br /><form:errors path="userAccount.password" cssClass="error"/></td>
		</tr>
	</table>
</td>
<td align="left" width="40%">
	<table>
		<tr>
			<td>First Name : </td>
				<td><form:input cssClass="textbox" path="person.firstName" placeholder="First Name"/> 
				<br /><form:errors path="person.firstName" cssClass="error" />
				</td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td> <form:input cssClass="textbox" path="person.lastName" placeholder="Last Name"/>  
			<br /><form:errors path="person.lastName" cssClass="error" /> 
			</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input type="email" cssClass="textbox" path="person.email" placeholder="Email"/></td> 
		</tr>
		<tr>
			<td> Contact #:</td>
			<td> <form:input cssClass="textbox" path="person.contactNo" placeholder="Contact No" /></td></tr>
	</table>
</td>
<td align="left" width="30%">
	<table>
		<tr>
			<td>Line 1:</td>
			<td><form:input cssClass="textbox" path="person.address.line1" placeholder="Line 1" />  
			<br /><form:errors path="person.address.line1" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Line 2:</td>
			<td><form:input cssClass="textbox" path="person.address.line2" placeholder="Line 2" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><form:input cssClass="textbox" path="person.address.city" placeholder="City" />  
			<br /><form:errors path="person.address.city" cssClass="error" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><form:input cssClass="textbox" path="person.address.state" placeholder="State" /> 
			<br /><form:errors path="person.address.state" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td> <form:input cssClass="textbox" path="person.address.country" placeholder="County" />
			<br /><form:errors path="person.address.country" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Zip Code:</td>
			<td> <form:input cssClass="textbox" path="person.address.zip" placeholder="Zip Code" /> 
			</td> 
		</tr>
	</table>			
</td>
</tr>			
<tr>
	<td colspan="2"><input type="submit" onclick="checkIt()"/></td>
</tr>
</form:form>
</table>
</div>
<div id="Manufacturer" class="hidden">
<table>
<form:form  modelAttribute="manufacturer"  action = "manufacturerSave.htm" method="POST">
<tr>
<td align="left" width="30%" style="margin-right: 50px" >
	
	<table>
		<tr>
			<td>Username:</td>
			<td><form:input cssClass="textbox" path="userAccount.username" placeholder="Username" />
			<br />  <form:errors path="userAccount.username" cssClass="err"/></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><form:password cssClass="textbox" path="userAccount.password" placeholder="Password" />
			<br /><form:errors path="userAccount.password" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Manufactuer Name:</td>
			<td><form:input cssClass="textbox" path="manufacturerName" placeholder="Manufacturer Name" />
			<br /><form:errors path="manufacturerName" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Contact Person Name:</td>
			<td><form:input cssClass="textbox" path="contactPersonName" placeholder="Contact Person Name" />
			<br /><form:errors path="contactPersonName" cssClass="error"/></td>
		</tr>
	</table>
</td>
<td align="left" width="40%">
	<table>
		<tr>
			<td>First Name : </td>
				<td><form:input cssClass="textbox" path="person.firstName" placeholder="First Name"/> 
				<br /><form:errors path="person.firstName" cssClass="error" />
				</td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td> <form:input cssClass="textbox" path="person.lastName" placeholder="Last Name"/>  
			<br /><form:errors path="person.lastName" cssClass="error" /> 
			</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><form:input type="email" cssClass="textbox" path="person.email" placeholder="Email"/></td> 
		</tr>
		<tr>
			<td> Contact #:</td>
			<td> <form:input cssClass="textbox" path="person.contactNo" placeholder="Contact No" /></td></tr>
	</table>
</td>
<td align="left" width="30%">
	<table>
		<tr>
			<td>Contact Person Address<br />Line 1:</td>
			<td><form:input cssClass="textbox" path="person.address.line1" placeholder="Line 1" />  
			<br /><form:errors path="person.address.line1" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Line 2:</td>
			<td><form:input cssClass="textbox" path="person.address.line2" placeholder="Line 2" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><form:input cssClass="textbox" path="person.address.city" placeholder="City" />  
			<br /><form:errors path="person.address.city" cssClass="error" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><form:input cssClass="textbox" path="person.address.state" placeholder="State" /> 
			<br /><form:errors path="person.address.state" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td> <form:input cssClass="textbox" path="person.address.country" placeholder="County" />
			<br /><form:errors path="person.address.country" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Zip Code:</td>
			<td> <form:input cssClass="textbox" path="person.address.zip" placeholder="Zip Code" /> 
			</td> 
		</tr>
	</table>			
</td>
<td>
<table>
		<tr>
			<td>Company Office Address<br />Line 1:</td>
			<td><form:input cssClass="textbox" path="officeAddress.line1" placeholder="Line 1" />  
			<br /><form:errors path="officeAddress.line1" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Line 2:</td>
			<td><form:input cssClass="textbox" path="officeAddress.line2" placeholder="Line 2" /></td>
		</tr>
		<tr>
			<td>City:</td>
			<td><form:input cssClass="textbox" path="officeAddress.city" placeholder="City" />  
			<br /><form:errors path="officeAddress.city" cssClass="error" /></td>
		</tr>
		<tr>
			<td>State:</td>
			<td><form:input cssClass="textbox" path="officeAddress.state" placeholder="State" /> 
			<br /><form:errors path="officeAddress.state" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td> <form:input cssClass="textbox" path="officeAddress.country" placeholder="County" />
			<br /><form:errors path="officeAddress.country" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Zip Code:</td>
			<td> <form:input cssClass="textbox" path="officeAddress.zip" placeholder="Zip Code" /> 
			</td> 
		</tr>
	</table>			
</td>
</tr>
<tr>
	<td colspan="2"><input type="submit" onclick="checkIt()"/></td>
</tr>
</form:form>		
</table> 
</div>
</body>