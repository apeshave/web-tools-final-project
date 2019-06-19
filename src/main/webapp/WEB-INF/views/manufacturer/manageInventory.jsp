<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<style type="text/css">
.rounded {
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
</head>
<body>
	<br>
	<br><br>
	<h2>Add Inventory</h2>
	<br />
	<c:choose>
	<c:when test="${products.size() == 0 }">
	<h3>No Products available to manufacture.</h3>
	</c:when>
	<c:otherwise>
	<form:form modelAttribute="inventoryItem" action="manufacture.htm" method="post">
		<table>
			<tr>
				<td>Select Product:</td>
				<td><select name="pro">
						<c:forEach var="prod" items="${products}">
							<option value="${prod.productName}">${prod.productName}</foption>
						</c:forEach>
				</select></td>
				<td>Select Quantity:</td>
				<td>
				<form:input path="quantity" cssClass="rounded" placeholder="Quantity"/><br />
				<font color="red">${error}</font>
				<font color="red">
				</font>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Manufacture">
				</td>			
			</tr>
		</table>
	</form:form>
	</c:otherwise>
	</c:choose>
	</body>
