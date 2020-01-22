<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="pl-PL">

<head>
<title>Meetings Page</title>

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
	width: 140px;
	padding: 5px 10px;
	
	margin-bottom: 15px;
	background: #cccccc;
}
</style>

</head>

<body>
	<div class="container" style="padding-top: 20px">
		<div class="row">
			<div class="col-sm">
			<h3>Spotkania</h3>
			</div>
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
	<div class="container">
		<div class="row">
			<div class="col-sm" style="padding: 30px">
				<h3>Spotkania, które prowadzisz</h3>
			</div>
			<div class="col-sm" style="padding: 30px">
				<input type="button" value="Dodaj spotkanie" class="add-button" style="float: right;"
				onclick="window.location.href='meetingFormAdd'; return false;" />
			</div>
		</div>
		<table id="tableID">
			<tr>
				<th>Data spotkania</th>
				<th>Pokój</th>
				<th>Czas trwania</th>
				<th>Akcje</th>
			</tr>
			<c:forEach var="tempMeeting" items="${meetings}">
				<c:url var="updateLink" value="/meetingFormUpdate">
					<c:param name="meetingId" value="${tempMeeting.meeting_id}" />
				</c:url>
				<c:url var="inviteLink" value="/sendInvite">
					<c:param name="meetingId" value="${tempMeeting.meeting_id}" />
				</c:url>
				<tr>
					<td>${tempMeeting.meeting_date}</td>
					<td>${tempMeeting.room}</td>
					<td>${tempMeeting.estimated_length} h</td>
					<td>
						<a href="${updateLink}">Aktualizuj</a> |
						<a href="${inviteLink}">Wyślij zaproszenie</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div style="padding: 30px">
			<h3>Spotkania, na które jesteś zaproszony</h3>
		</div>
		<table id="tableID2">
			<tr>
				<th>Data spotkania</th>
				<th>Inicjator spotkania</th>
				<th>Pokój</th>
				<th>Czas trwania</th>
			</tr>
			<c:forEach var="tempInvite" items="${invites}">
				<tr>
					<td>${tempInvite.meeting_date}</td>
					<td>${tempInvite.initiator}</td>
					<td>${tempInvite.room}</td>
					<td>${tempInvite.estimated_length} h</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="clear; both;"></div>
	
	<form:form action="${pageContext.request.contextPath}" style="margin-left: 20px"
	   method="GET">

		<input type="submit" value="Powrót" />
		
	</form:form>
</body>

</html>