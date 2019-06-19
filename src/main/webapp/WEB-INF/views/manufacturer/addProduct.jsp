<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<br><br>
	<br><br>
	<form:form action="saveProduct.htm" method="post" commandName="product">
		<table>
			<tr>
				<td><form:input cssClass="rounded" path="productName"
						placeholder="productName" /></td>
				<td><form:input cssClass="rounded" path="description"
						placeholder="description" /></td>
			</tr>
			<tr>
				<td>
  				<form:errors path="productName" cssStyle="color:red;" />  
				</td>
			</tr>
		</table>
		<input type="submit" value="Add">
	</form:form>
</body>
