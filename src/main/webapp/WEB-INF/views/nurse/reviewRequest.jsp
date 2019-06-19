<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
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
<body>
	<h2>Review requests</h2>
	<form method="post">
		<table class="reference">
			<tr>
			<th colspan="5" style="font-size: 25px; text-align: center;">Approved Requests
			</th>
			</tr>
			<tr>
				<th width="20%">Product Name</th>
				<th width="20%">Sender</th>
				<th width="20%">Receiver</th>
				<th width="20%">Comments</th>
				<th width="30%">Date and Time</th>
			</tr>
			<c:choose>
				<c:when test="${confirmRequests.size() == 0 }">
					<tr>
						<td colspan="6">
							<h3 style="color: grey; text-align: center;">No requests!!</h3>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="request" items="${confirmRequests}">
						<tr>
							<td>${request.product.productName}</td>
							<td>${request.sender}</td>
							<td>${request.receiver}</td>
							<td>${request.comments}</td>
							<td>${request.date}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<br />
		<table class="reference">
		<tr>
			<th colspan="5" style="font-size: 25px; text-align: center;">Pending Requests
			</th>
			</tr>
			<tr>
				<th width="20%">Product Name</th>
				<th width="20%">Sender</th>
				<th width="20%">Receiver</th>
				<th width="30%">Comments</th>
				<th width="30%">Date & Time</th>
			</tr>
			<c:choose>
				<c:when test="${pendingRequests.size() == 0 }">
					<tr>
						<td colspan="6">
							<h3 style="color: grey; text-align: center;">No pending
								requests!!</h3>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="request" items="${pendingRequests}">
						<tr>
							<td>${request.product.productName}</td>
							<td>${request.sender}</td>
							<td>${request.receiver}</td>
							<td>${request.comments}</td>
							<td>${request.date}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
</body>
