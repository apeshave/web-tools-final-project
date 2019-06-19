<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<form action="signup.htm" method="post">
<table>
<tr>
<td>
Select your type:
</td>
<td>
<select name="selection">
<option value="${role}"></option>
<c:forEach var="role" items="${roles}">
<option value="${role}">${role}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>
<input type="submit">
</td>
</tr>
</table>
</form>
</body>
