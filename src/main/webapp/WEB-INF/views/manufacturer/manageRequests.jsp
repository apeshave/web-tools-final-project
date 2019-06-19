<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<style type="text/css">
 
table.reference,table.tecspec {
	border-collapse: collapse;
	width: 100%;
}

table.reference tr:nth-child(odd) {
	background-color: #F6F4F0;
}

table.reference tr:nth-child(even) {
	background-color: #ffffff;
}

table.reference tr.fixzebra {
	background-color: #F6F4F0;
}

table.reference th {
	color: #ffffff;
	background-color: #555555;
	border: 1px solid #555555;
	font-size: 15px;
	padding: 3px;
	vertical-align: top;
	text-align: left;
}

table.reference th a:link,table.reference th a:visited {
	color: #ffffff;
}

table.reference th a:hover,table.reference th a:active {
	color: #EE872A;
}

table.reference td {
	border: 1px solid #d4d4d4;
	padding: 5px;
	padding-top: 7px;
	padding-bottom: 7px;
	vertical-align: top;
}

table.reference td.example_code {
	vertical-align: bottom;
}
</style>
</head>
<br><br>
	<br>
<form action="process.htm" method="post">
	<h2>Manage Products</h2>
	<table class="reference">
		<tr>
			<th width="20%">Select for Process:</th>
			<th width="20%">Request Name</th>
			<th width="20%">Sender</th>
			<th width="20%">Receiver</th>
			<th width="30%">Comments</th>
		</tr>
		<c:choose>
		<c:when test="${requests.size() == 0 }">
		<tr>
		<td colspan="5">
		<h3 style="color: grey;text-align: center;">No requests!!</h3>
		</td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach var="request" items="${requests}">
			<tr>
				<td><input type="checkbox" name="selection" value="${request.id}">Process
				</td>
				<td>${request.product.productName}</td>
				<td>${request.sender}</td>
				<td>${request.receiver}</td>
				<td>${request.comments}</td>
			</tr>
		</c:forEach>
		<tr>
		<td colspan="2">
		<input type="submit" value="Process">
		</td>
		</tr>
		</c:otherwise>
		</c:choose>
	</table>
</form>
