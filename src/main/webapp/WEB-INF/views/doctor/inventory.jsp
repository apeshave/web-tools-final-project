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
<body>
	<h2>Inventory</h2>
	<table class="reference">
		<tr>
			<th>Product</th>
			<th>Manufacturer</th>
			<th>Availability</th>
			<th>Quantity</th>
			<th>Owner</th>
			
		</tr>
		<c:forEach var="item" items="${inventory}">
			<tr>
				<td>${item.product.productName}</td>
				<td>${item.product.manufacturerName}</td>
				<td>${item.availability}</td>
				<td>${item.quantity}
				<td>${item.owner}</td>
			</tr>
		</c:forEach>
	</table>
</body>
