<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<body>
<c:choose>
<c:when test="${product != null }">
<h3 style="size: 90px">Product added successfully...</h3>
<h3>Product Name: ${product.productName}</h3>
</c:when>
<c:when test="${inventory != null }">
<h3 style="size: 120px">Inventory added successfully...</h3>
<h3 style="size: 120px">Product Name: ${inventory.product.productName}</h3><br>
<h3 style="size: 120px">Quantity: ${inventory.quantity }</h3>
</c:when>
<c:otherwise>
<br/>
<br/>
<h3  style="size: 100px">Welcome ${sessionScope.user.username }</h3>
</c:otherwise>
</c:choose>
</body>