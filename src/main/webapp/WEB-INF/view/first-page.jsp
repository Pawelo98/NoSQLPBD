<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<title>Club Manager App (development)</title>
</head>

<body>
	Logging coming soon
	
		<form:form action="${pageContext.request.contextPath}/logout"
			   method="POST">

		<input type="submit" value="Logout" />
		
	</form:form>
</body>

</html>