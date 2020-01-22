<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="pl-PL">

<head>
<title>Home Page</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<meta http-equiv="Content-Language" content="pl">
<meta charset="UTF-8">

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

<script>
	var table = document.getElementById('tableID');
	var tbody = table.getElementsByTagName('tbody')[0];
	var cells = tbody.getElementsByTagName('td');

	for (var i = 0, len = cells.length; i < len; i++) {
		if (cells[i].tempMatch.winner == 1) {
			cells[i].style.backgroundColor = 'green';
		} else if (cells[i].tempMatch.winner == 2) {
			cells[i].style.backgroundColor = 'red';
		}
	}
</script>

</head>

<body>
	<div class="container" style="padding: 20px">
		<div class="row">
			<div class="col-sm"><h3>Główna</h3></div>
			<div class="col-sm">
				<h2>Club Manager</h2>
			</div>
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
	<div class="row">
		<div class="col-5" style="padding: 30px">
			<div>
				<h3>Poprzednie mecze</h3>
			</div>
			<table id="tableID">
				<tr>
					<th>Gospodarz</th>
					<th></th>
					<th>Gość</th>
					<th>Bramki gosp.</th>
					<th></th>
					<th>Bramki gość</th>
					<th>Data meczu</th>
				</tr>
				<c:forEach var="tempMatch" items="${matchesDemo}">
					<tr>
						<td>${tempMatch.host}</td>
						<td>vs.</td>
						<td>${tempMatch.visitor}</td>
						<td>${tempMatch.home_goals}</td>
						<td>:</td>
						<td>${tempMatch.away_goals}</td>
						<td>${tempMatch.game_date}</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top: 30px">
				<h3>Spotkania, które prowadziłeś</h3>
			</div>
			<table id="tableID">
				<tr>
					<th>Data spotkania</th>
					<th>Pokój</th>
					<th>Czas trwania</th>
				</tr>
				<c:forEach var="tempMeeting" items="${meetingsPast}">
					<tr>
						<td>${tempMeeting.meeting_date}</td>
						<td>${tempMeeting.room}</td>
						<td>${tempMeeting.estimated_length} h</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top: 30px">
				<h3>Spotkania, na które byłeś zaproszony</h3>
			</div>
			<table id="tableID2">
				<tr>
					<th>Data spotkania</th>
					<th>Inicjator spotkania</th>
					<th>Pokój</th>
					<th>Czas trwania</th>
				</tr>
				<c:forEach var="tempInvite" items="${invitesPast}">
					<tr>
						<td>${tempInvite.meeting_date}</td>
						<td>${tempInvite.initiator}</td>
						<td>${tempInvite.room}</td>
						<td>${tempInvite.estimated_length} h</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="col-3" style="padding: 20px">
			<div class="row">
				<div class="col-sm">

					<security:authorize access="hasRole('ROLE_PHYSICAL')">
					
						<security:authentication var="principal" property="principal" />
						<form:form action="${pageContext.request.contextPath}/showUser"
							method="GET">
							
							<input type="submit" name="username" value="${principal.username}" class="col-10"
								style="margin-top: 30px; height: 40px"/>

						</form:form>

					</security:authorize>

					<security:authorize access="hasRole('ROLE_PHYSICAL')">

						<form:form action="${pageContext.request.contextPath}/meetings"
							method="GET">

							<input type="submit" value="Zarządzaj spotkaniami" class="col-10"
								style="margin-top: 30px; height: 40px" />

						</form:form>

					</security:authorize>


					<security:authorize access="hasRole('ROLE_ADMINISTRATIVE')">


						<form:form action="${pageContext.request.contextPath}/referees"
							method="GET">

							<input type="submit" value="Zarządzaj sędziami" class="col-10"
								style="margin-top: 30px; height: 40px" />

						</form:form>

					</security:authorize>



					<security:authorize access="hasRole('ROLE_ADMINISTRATIVE')">

						<form:form action="${pageContext.request.contextPath}/clubs"
							method="GET">

							<input type="submit" value="Zarządzaj klubami" class="col-10"
								style="margin-top: 30px; height: 40px" />

						</form:form>

					</security:authorize>

					<!--<security:authorize access="hasRole('ROLE_ADMINISTRATIVE')">

						<form:form action="${pageContext.request.contextPath}/leagues"
							method="GET">

							<input type="submit" value="Zarządzaj ligami" class="col-10"
								style="margin-top: 30px; height: 40px" />

						</form:form>

					</security:authorize>-->

					<security:authorize access="hasRole('ROLE_ADMINISTRATIVE')">

						<form:form action="${pageContext.request.contextPath}/matches"
							method="GET">

							<input type="submit" value="Budynki klubu" class="col-10"
								style="margin-top: 30px; height: 40px" />

						</form:form>

					</security:authorize>

					<security:authorize access="hasRole('ROLE_ADMIN')">

						<form:form action="${pageContext.request.contextPath}/admins"
							method="GET">

							<input type="submit" value="Zarządzaj użytkownikami"
								class="col-10" style="margin-top: 30px; height: 40px" />

						</form:form>

					</security:authorize>
				</div>
			</div>
		</div>
		<div class="col-4" style="padding: 30px">
			<div>
				<h3>Przyszłe mecze</h3>
			</div>
			<table>
				<tr>
					<th>Gospodarz</th>
					<th>Gość</th>
					<th>Data meczu</th>
				</tr>
				<c:forEach var="tempMatch" items="${matchesFuture}">
					<tr>
						<td>${tempMatch.host}</td>
						<td>${tempMatch.visitor}</td>
						<td>${tempMatch.game_date}</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top: 30px">
				<h3>Spotkania, które będziesz prowadzić</h3>
			</div>
			<table id="tableID3">
				<tr>
					<th>Data spotkania</th>
					<th>Pokój</th>
					<th>Czas trwania</th>
				</tr>
				<c:forEach var="tempMeeting" items="${meetingsFuture}">
					<tr>
						<td>${tempMeeting.meeting_date}</td>
						<td>${tempMeeting.room}</td>
						<td>${tempMeeting.estimated_length} h</td>
					</tr>
				</c:forEach>
			</table>
			<div style="padding-top: 30px">
				<h3>Spotkania, na które jesteś zaproszony</h3>
			</div>
			<table id="tableID4">
				<tr>
					<th>Data spotkania</th>
					<th>Inicjator spotkania</th>
					<th>Czas trwania</th>
				</tr>
				<c:forEach var="tempInvite" items="${invitesFuture}">
					<tr>
						<td>${tempInvite.meeting_date}</td>
						<td>${tempInvite.initiator}</td>
						<td>${tempInvite.estimated_length} h</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>