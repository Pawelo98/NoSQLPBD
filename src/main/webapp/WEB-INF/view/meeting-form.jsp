<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
	<title>Add Meeting</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	<div class="container" style="padding-top: 20px">
	  <div class="row">
	    <div class="col-sm">
	      <h3>Zarządzanie spotkaniem</h3>
	    </div>
	    <div class="col-sm">
	      <h2>Club Manager</h2>
	    </div>
	    <div class="col-sm">
	    
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-sm" style="padding-top: 13px">
				      zalogowany:
				      
				      <security:authentication property="principal.username"/>
				      
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
		<h3 style="padding-top: 20px; padding-bottom: 20px;">Zarządzanie spotkaniem</h3>
		
		<form:form action="saveMeeting" modelAttribute="meeting" method="POST">
			
			<form:hidden path="meeting_id" />
			<form:hidden path="initiator.username" />
			<table>
				<tbody>	
					<tr>
						<td><label>Budynek:</label></td>
						<td><form:select path="building.building_id">
								
								<c:forEach var="tempclub" items="${buildings}">
								<form:option value="${tempclub.building_id}" label="${tempclub.name}" />
								</c:forEach>

							</form:select></td>
					</tr>		
					<tr>
						<td><label>Inicjator:</label></td>
						<td><form:input path="initiator.username" disabled="true"/></td>
					</tr>	
					<tr>
						<td><label>Pokój:</label></td>
						<td><form:input path="room" /></td>
					</tr>
					<tr>
						<td><label>Czas trwania:</label></td>
						<td><form:input path="estimated_length" /></td>
					</tr>
					<tr>
						<td><label>Data:</label></td>
						<td><form:input path="meeting_date" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Zapisz" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<div style="clear; both;"></div>
	
	<form:form action="${pageContext.request.contextPath}/meetings" style="margin-left: 20px"
	   method="GET">

		<input type="submit" value="Powrót" />
		
	</form:form>
	
	</div>
</body>

</html>