<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
	<title>Credentials Page</title>
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
	      <h2>Dane konta</h2>
	    </div>
	    <div class="col-sm">
			<h2>
   				Club Manager
   			</h2>
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
</div>
<div id="container" style="padding-top: 20px">
	<h3 style="width: 50%; margin: 0 auto; padding-top: 20px; margin-bottom: 20px; align-text: center;">Zmień dane konta:</h3>
	
	<form:form action="saveCredentials" modelAttribute="user" method="POST" style="width: 50%; margin: 0 auto; padding-top: 20px; margin-bottom: 20px;">
		<form:hidden path="username"/>
		<form:hidden path="enabled"/>
		<form:hidden path="registrationDate"/>
			<table>
				<tbody>
					<tr style="width: 50%; margin: 0 auto;">
						<td><label>Nazwa:</label></td>
						<td><form:input path="username" disabled="true"/></td>
					</tr>
					
					<tr style="width: 50%; margin: 0 auto;">
						<td><label>Hasło:</label></td>
						<td><form:input path="password"/></td>
					</tr>
					
					<tr style="width: 50%; margin: 0 auto;">
						<td><label>Imię:</label></td>
						<td><form:input path="name"/></td>
					</tr>
					
					<tr style="width: 50%; margin: 0 auto;">
						<td><label>Nazwisko:</label></td>
						<td><form:input path="surname"/></td>
					</tr>
					
					<tr style="width: 50%; margin: 0 auto;">
						<td><label>Adres:</label></td>
						<td><form:input path="address"/></td>
					</tr>
					
					<tr style="width: 50%; margin: 0 auto;">
						<td><label>Rejestracja:</label></td>
						<td><form:input path="registrationDate" disabled="true" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])"/></td>
					</tr>
					
					<tr>
						<td><label>Klub:</label></td>
						<td><form:select path="club.club_id">
								
								<c:forEach var="tempclub" items="${club}">
								<form:option value="${tempclub.club_id}" label="${tempclub.name}" />
								</c:forEach>

							</form:select></td>
					</tr>
					
					<tr style="width: 50%; margin: 0 auto;">
						<td><label></label></td>
						<td><input type="submit" value="Zapisz" class="save"/></td>
					</tr>
				</tbody>
			</table>
		
	</form:form>
	
	<div style="clear; both;"></div>
	
	<form:form action="${pageContext.request.contextPath}" style="margin-left: 20px"
	   method="GET">

		<input type="submit" value="Powrót" />
		
	</form:form>
</div>
	
</body>

</html>