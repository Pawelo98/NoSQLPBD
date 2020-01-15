<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
	<title>Accounts Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<style>
		html, body{
			margin-left:15px; margin-right:15px; 
			padding:0px; 
			font-family:Verdana, Arial, Helvetica, sans-serif;
		}
		
		table {   
			border-collapse:collapse;
			border-bottom:1px solid gray;
			font-family: Tahoma,Verdana,Segoe,sans-serif;
			width:72%;
		}
		 
		th {
			border-bottom:1px solid gray;
			background:none repeat scroll 0 0 #09c332;
			padding:10px;
			color: #FFFFFF;
		}
		
		tr {
			border-top:1px solid gray;
			text-align:center;	
		}
		 
		tr:nth-child(even) {background: #FFFFFF}
		tr:nth-child(odd) {background: #BBBBBB}	
		 
		#wrapper {width: 100%; margin-top: 0px; }
		#header {width: 70%; background: #09c332; margin-top: 0px; padding:15px 0px 15px 15px;}
		#header h2 {width: 100%; margin:auto; color: #FFFFFF;}
		#container {width: 100%; margin:auto}
		#container h3 {color: #000;}
		#container #content {margin-top: 20px;}
		
		.add-button {
			border: 1px solid #666; 
			border-radius: 5px; 
			padding: 4px; 
			font-size: 12px;
			font-weight: bold;
			width: 160px; 
			padding: 5px 10px; 
			margin-left: 375px;
			margin-top: 20px;
			
			margin-bottom: 15px;
			background: #cccccc;
		}
	</style>
</head>

<body>
	<div class="container" style="padding-top: 20px">
	  <div class="row">
	    <div class="col-sm">
	      <h3>Użytkownicy</h3>
	    </div>
	    <div class="col-sm">
			<h2>
   				Club Manager
   			</h2>
	    </div>
	    <div class="col-sm">
	    
	    	<div class="container">
	    		<div class="row">
	    			<div style="width: 250px;">
				      zalogowany:
				      
				      <security:authentication property="principal.username"/>
				      
				      </div>
				      <div style="width: 100px;">
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
	
	<input type="button" value="Dodaj użytkownika" onclick="window.location.href='user/addUser'; return false;"
		class='add-button'/>
	
	<div style="width: 50%; margin: 0 auto; padding-top: 20px; margin-bottom: 20px;">
		Lista użytkowników aplikacji:
		<table>
			<tr>
				<th> Nazwa </th>
				<th> Aktywny </th>
				<th> Data rejestracji </th>
				<th> Imię </th>
				<th> Nazwisko </th>
				<th> Adres </th>
				<th> Klub </th>
				<th> Akcje </th>
			</tr>
			
			<c:forEach var="tempUser" items="${users}">
			
				<c:url var="updateLink" value="user/editUser">
					<c:param name="username" value="${tempUser.username}"/>
				</c:url>
				<tr>
					<td> ${tempUser.username} </td>
					<td> ${tempUser.enabled} </td>
					<td> ${tempUser.registrationDate} </td>
					<td> ${tempUser.name} </td>
					<td> ${tempUser.surname} </td>
					<td> ${tempUser.address} </td>
					<td> ${tempUser.club.name} </td>
					<td> <a href="${updateLink}">Zmień</a>
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