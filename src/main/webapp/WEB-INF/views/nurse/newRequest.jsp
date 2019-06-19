<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
	<form action = "createNurseRequest.htm" method = "post">
		<h3>Nurse Request</h3>
		<table>
			<tr>
				<td>Select Product:</td>
				<td><select name="selectedProduct">
						<c:forEach var="product" items="${products}">
							<option value="${product.productName}">${product.productName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Select Doctor:</td>
				<td><select name="selectedDoctor">
						<c:forEach var="doctor" items="${doctors}">
							<option value="${doctor.userAccount.username}">${doctor.person.firstName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Select Quantity:</td>
				<td><input type="text" name="quantity"
					placeholder="Enter Quantity" /></td>
			</tr>
			<tr>
				<td>Enter Comments(in Any)</td>
				<td><textarea name="comments" rows="5" cols="60"
						placeholder="Enter Comments"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">Date: ${date}</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
