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
<style>
		form {
			margin-top: 10px;
		}
		
		label {
			font-size: 16px; 
			width: 100px; 
			display: block; 
			text-align: right;
			margin-right: 10px;
			margin-top: 8px;
			margin-bottom: 8px;
		}
		
		input {
			width: 250px;
			border: 1px solid #666; 
			border-radius: 5px; 
			padding: 4px; 
			font-size: 16px;
		}
		
		.save {
			font-weight: bold;
			width: 130px; 
			padding: 5px 10px; 
			margin-top: 30px;
			background: #cccccc;
		}
		
		table {   
			border-style:none;
			width:50%;
		}
		
		tr:nth-child(even) {background: #FFFFFF}
		tr:nth-child(odd) {background: #FFFFFF}
		
		tr {
			border-style:none;
			text-align:left;	
		}
	</style>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm">Pracownicy</div>
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

	<div id="container">
		<h3>Dodaj pracownika</h3>

		<form:form action="saveWorker" modelAttribute="worker" method="POST">

			<form:hidden path="worker_id" />

			<table>
				<tbody>
					<tr>
						<td><label>Imię:</label></td>
						<td><form:input path="name" /></td>
					</tr>

					<tr>
						<td><label>Nazwisko:</label></td>
						<td><form:input path="surname" /></td>
					</tr>

					<tr>
						<td><label>Zarobki:</label></td>
						<td><form:input path="earnings" /></td>
					</tr>
					
					<tr>
						<td><label>Dział:</label></td>
						<td><form:select path="department">
								<form:option value="Greenkeepers" label="Greenkeepers" />
								<form:option value="Accountants" label="Accountants" />
								<form:option value="Masseurs" label="Masseurs" />
								<form:option value="Cleaners" label="Cleaners" />
								<form:option value="Players" label="Players" />

							</form:select></td>
					</tr>




					<tr>
						<td><label>Numer koszulki:</label></td>
						<td><form:input path="shirtNumber" /></td>
					</tr>

					<%-- tr>
						<td><label>Lepsza noga:</label></td>
						<td><form:select path="strongFoot">
								<form:option value="Right" label="Right" />
								<form:option value="Left" label="Left" />
								<form:option value="Both" label="Both" />
								<form:option value="" label="Nie piłkarz" />

							</form:select></td>
					</tr --%>

					<tr>
						<td><label>Wzrost:</label></td>
						<td><form:input path="height" /></td>
					</tr>

					<tr>
						<td><label>Waga:</label></td>
						<td><form:input path="weight" /></td>
					</tr>

					<%-- >tr>
						<td><label>Pozycja:</label></td>
						<td><form:select path="position">
								<form:option value="Goalkeeper" label="Goalkeeper" />
								<form:option value="Defender" label="Defender" />
								<form:option value="Midfielder" label="Midfielder" />
								<form:option value="Striker" label="Striker" />
								<form:option value="" label="Nie piłkarz" />

							</form:select></td>
					</tr--%>

					<tr>
						<td><label>Klub:</label></td>
						<td><form:select path="club.club_id">
								
								<c:forEach var="tempclub" items="${club}">
								<form:option value="${tempclub.club_id}" label="${tempclub.name}" />
								</c:forEach>

							</form:select></td>
					</tr>



					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Dodaj"
							class="btn btn-secondary" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>

		<div style=""></div>

		<p>
				<form:form action="${pageContext.request.contextPath}/clubs/workersManagement"
				style="margin-left: 20px" method="GET" >

				<input id="goBack" type="submit" value="Powrót" class="btn btn-outline-dark"/>

			</form:form>
		</p>

	</div>

</body>

</html>