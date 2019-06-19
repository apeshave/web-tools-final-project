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
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="http://jzaefferer.github.com/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript">
$(document).ready(function(){

    $("#myForm").validate();
    
  });
</script>
</head>
<body>
	<form class="cmxform" id="myForm" action="processReturn.htm" method="post">
	<br>
	<br>
	<br>
		<table class="reference">
			<tr>
				<th>Product</th>
				<th>Manufacturer</th>
				<th>Quantity</th>
				<th>Return</th>
			</tr>
			<c:forEach var="item" items="${myItems}">
				<tr>
					<td>${item.product.productName }</td>
					<td>${item.product.manufacturerName }</td>
					<td>${item.quantity }</td>
					<td><input type="checkbox" name="selection" value="${item.id}" class="required"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="center"><input type="submit" value="return"></td>
			</tr>
		</table>
	</form>
</body>
