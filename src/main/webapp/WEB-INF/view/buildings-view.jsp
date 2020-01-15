<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<style>
html, body{
	margin-left: 15px; 
	margin-right: 15px;
	padding: 0px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

table{
	border-collapse: collapse;
	border-bottom: 1px solid gray;
	font-family: Tahoma, Verdana, Segoe, sans-serif;
	width: 100%;
	padding: 20px;
}

th{
	border-bottom: 1px solid gray;
	background: none repeat scroll 0 0 #09c332;
	padding: 10px;
	color: #FFFFFF;
}

tr{
	border-top: 1px solid gray;
	text-align: center;
}

tr:nth-child(even) {background: #FFFFFF}
tr:nth-child(odd) {background: #BBBBBB}

.add-button{
	border: 1px solid #666;
	border-radius: 5px;
	padding: 4px;
	font-size: 12px;
	font-weight: bold;
	width: 120px;
	padding: 5px 10px;
	
	margin-bottom: 15px;
	background: #cccccc;
}
</style>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm">Budynki</div>
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

					<input type="submit" value="Wróć" class="btn btn-outline-dark"
						style="margin: 10px;" />

				</form:form>
			</div>

			<div class="btn-group">
				<input type="button" value="Dodaj"
					onclick="window.location.href='showFormForAddBuilding'; return false;"
					class="btn btn-secondary" style="margin: 10px;" />
			</div>


		</div>
	</div>
<form:form action="${pageContext.request.contextPath}/clubs/buildings"
			method="GET">
			
			<div class="form-group">
				<label for="sel1">Wybierz klub:</label> <select class="form-control"
					id="sel11" name="selected">

					<c:forEach var="tempclub" items="${clubss}">
						<option value="${tempclub.club_id}">${tempclub.name}</option>
					</c:forEach>

				</select>
			</div>

			<input type="submit" value="Wyświetl budynki" class="btn btn-outline-dark"
				style="margin: 10px;" />
			

		</form:form>
	
		<div id="content">



			<table>
				<tr>
					<th>Nazwa</th>
					<th>Powierzchnia</th>
					<th>Adres</th>
					<th>Typ</th>
					<th>Klub</th>
					<th>Edycja</th>

				</tr>


				<c:forEach var="tempbuilding" items="${buildingFromDB}">

					<c:url var="updateLink" value="/clubs/showFormForUpdateBuilding">
						<c:param name="buildingId" value="${tempbuilding.building_id}" />
					</c:url>
					
					<c:url var="deleteLink" value="/clubs/deleteBuilding">
						<c:param name="buildingId" value="${tempbuilding.building_id}" />
					</c:url>

					<tr>
						<td>${tempbuilding.name}</td>
						<td>${tempbuilding.surface}</td>
						<td>${tempbuilding.address}</td>
						<td>${tempbuilding.type}</td>
						<td>${tempbuilding.club.name}</td>
						<td><a href="${updateLink}">Edytuj</a></td>


					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>