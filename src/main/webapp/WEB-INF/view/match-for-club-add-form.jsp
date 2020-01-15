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
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm">Mecze</div>
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
		<h3>Dodaj mecz</h3>

		<form:form action="saveMatch" modelAttribute="match" method="POST">

			<form:hidden path="match_id" />

			<table>
				<tbody>
					<tr>
						<td><label>Gospodzarz:</label></td>
						<td><form:select path="host.club_id">

								<c:forEach var="tempclub" items="${club}">
									<form:option value="${tempclub.club_id}"
										label="${tempclub.name}" />
								</c:forEach>

							</form:select></td>
					</tr>

					<tr>
						<td><label>Gość:</label></td>
						<td><form:select path="visitor.club_id">

								<c:forEach var="tempclub" items="${club}">
									<form:option value="${tempclub.club_id}"
										label="${tempclub.name}" />
								</c:forEach>

							</form:select></td>
					</tr>

					<tr>
						<td><label>Bramki gospodarza:</label></td>
						<td><form:input path="home_goals" /></td>
					</tr>
					<tr>
						<td><label>Bramki gościa:</label></td>
						<td><form:input path="away_goals" /></td>
					</tr>

					<tr>
						<td><label>Data(YYYY-MM-DD):</label></td>
						<td><form:input path="game_date"
								pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" /></td>
					</tr>

					<tr>
						<td><label>Zwycięzca:</label></td>
						<td><form:select path="winner">
								<form:option value="One" label="Gospodarz" />
								<form:option value="Two" label="Gość" />
								<form:option value="Zero" label="Remis" />
							</form:select></td>
					</tr>

					<tr>
						<td><label>Liga:</label></td>
						<td><form:select path="league.league_id">

								<c:forEach var="templeague" items="${leagues}">
									<form:option value="${templeague.league_id}"
										label="${templeague.name}" />
								</c:forEach>

							</form:select></td>
					</tr>

					<tr>
						<td><label>Sędzia#1:</label></td>

						<td><select name="sedzia1">

								<c:forEach var="tempRef" items="${ref}">
									<option value="${tempRef.referee_id}"
										label="${tempRef.surname} ${tempRef.name}" />
								</c:forEach>
								<option value="${mRef1.referee_id}" selected>${mRef1.surname}
									${mRef1.name}</option>

						</select></td>
					</tr>
					<tr>
						<td><label>Sędzia#2:</label></td>
						<td><select name="sedzia2">

								<c:forEach var="tempRef" items="${ref}">
									<option value="${tempRef.referee_id}"
										label="${tempRef.surname} ${tempRef.name}" />
								</c:forEach>
								<option value="${mRef2.referee_id}" selected>${mRef2.surname}
									${mRef2.name}</option>

						</select></td>

					</tr>

					<tr>
						<td><label>Sędzia#3:</label></td>
						<td><select name="sedzia3">

								<c:forEach var="tempRef" items="${ref}">
									<option value="${tempRef.referee_id}"
										label="${tempRef.surname} ${tempRef.name}" />
								</c:forEach>
								<option value="${mRef3.referee_id}" selected>${mRef3.surname}
									${mRef3.name}</option>

						</select></td>
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
			<form:form action="${pageContext.request.contextPath}/clubs/matchesManagement"
				style="margin-left: 20px" method="GET" >

				<input  type="submit" value="Powrót" class="btn btn-outline-dark" />

			</form:form>
		</p>

	</div>

</body>

</html>