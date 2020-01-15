<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
<title>Clubs Page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta charset="utf-8">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm">Clubs</div>
			<div class="col-sm">Club Manager</div>
			<div class="col-sm">

				<div class="container">
					<div class="row">
						<div class="col-sm">
							zalogowany:

							<security:authentication property="principal.username" />

						</div>
						<div class="col-sm">
							<form:form action="${pageContext.request.contextPath}/logout"
								method="POST">

								<input type="submit" value="Wyloguj" />

							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="conteiner">
		<div class="row" style="margin: 10px;">
			<div class="btn-group">
				<form:form action="${pageContext.request.contextPath}/clubs"
					method="GET">

					<input type="submit" value="Lista klubów"
						class="btn btn-outline-dark" style="margin: 10px;" />

				</form:form>

				<form:form
					action="${pageContext.request.contextPath}/clubs/buildingsManagement"
					method="GET">

					<input type="submit" value="Budynki" class="btn btn-outline-dark"
						style="margin: 10px;" />

				</form:form>

				<form:form action="${pageContext.request.contextPath}/clubs/workersManagement"
					method="GET">

					<input type="submit" value="Pracownicy"
						class="btn btn-outline-dark" style="margin: 10px;" />

				</form:form>

				<form:form action="${pageContext.request.contextPath}/clubs/matchesManagement"
					method="GET">

					<input type="submit" value="Mecze" class="btn btn-outline-dark"
						style="margin: 10px;" />

				</form:form>
			</div>

			<div class="btn-group">
				<form:form action="${pageContext.request.contextPath}/" method="GET">

					<input type="submit" value="Wróć" class="btn btn-outline-dark"
						style="margin: 10px;" />

				</form:form>
			</div>


		</div>
				


	</div>
</body>

</html>