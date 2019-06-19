<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<form action="send.htm" method="post">
		<table border="1" style="font-size: 20px">
			<tr>
				<td colspan="2">Make a request:</td>
			</tr>
			<tr>
				<td>Select Product:</td>
				<td><select name="product">
						<c:forEach var="item" items="${products}">
							<option value="${item.productName}">${item.productName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input name="quantity"
					placeholder="Enter Quantity" /><br /></td>
			</tr>
			<tr>
				<td colspan="2"><b>Date: ${date}</b></td>
			</tr>
			<tr>
				<td>Comments(if any):</td>
				<td><textarea placeholder="Comments in any..." 
				name="comments" draggable="true" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"></td>
			</tr>
		</table>
	</form>
</body>
