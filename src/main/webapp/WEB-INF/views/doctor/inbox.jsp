<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
	
</script>
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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#myForm2").validate();
    $("#myForm").validate();
    
  });
</script>
</head>
<body>
	<form class="cmxform" id="myForm" action="updateInventory.htm" method="post">
		<table class="reference">
			<tr>
				<th>Request ID</th>
				<th>Product Name</th>
				<th>Manufacturer Name</th>
				<th>Quantity</th>
				<th>Request Date</th>
				<th>Resolve Date</th>
				<th>Add to Inventory</th>
			</tr>
			<c:choose>
				<c:when test="${ processedRequests.size() == 0}">
					<tr>
						<td colspan="7">
							<h3 style="color: grey; text-align: center;">No Requests...</h3>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="processedRequest" items="${processedRequests}">
						<tr>
							<td>${processedRequest.request.id}</td>
							<td>${processedRequest.inventoryItem.product.productName}</td>
							<td>${processedRequest.inventoryItem.product.manufacturer.manufacturerName}</td>
							<td>${processedRequest.inventoryItem.quantity }</td>
							<td>${processedRequest.request.date }</td>
							<td>${processedRequest.processedDate}</td>
							<td><input type="checkbox" name="selection"
								value="${processedRequest.id}" class="required"/></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7"><input name="submit" type="submit"
							value="Add Inventory"/></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
	<br />
	<form class="cmxform" id="myForm2" action="processNurseRequest.htm" method="post">
		<table class="reference">
			<tr>
				<th>Request ID</th>
				<th>Product Name</th>
				<th>Manufacturer Name</th>
				<th>Quantity</th>
				<th>Request Date</th>
				<th>Process Request</th>
			</tr>
			<c:choose>
				<c:when test="${ nurseRequests.size() == 0}">
					<tr>
						<td colspan="7">
							<h3 style="color: grey; text-align: center;">No Nurse
								Requests...</h3>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="nurseRequest" items="${nurseRequests}">
						<tr>
							<td>${nurseRequest.id}</td>
							<td>${nurseRequest.product.productName}</td>
							<td>${nurseRequest.product.manufacturer.manufacturerName}</td>
							<td>${nurseRequest.quantity }</td>
							<td>${nurseRequest.date }</td>
							<td><input type="checkbox" name="selection"
								value="${nurseRequest.id}" class="required" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="6"><input name="submit2" type="submit"
							value="Process Request" /></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
</body>