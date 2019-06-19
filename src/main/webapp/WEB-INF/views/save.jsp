<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<h1>Thank you ${userAccount.person.firstName }....</h1>
<a href="new.htm">Click here </a>to log in...
<c:redirect url="new.htm">Click here to log in...</c:redirect> 
</body>
