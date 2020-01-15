<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
	<title>Matches Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
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
			width: 72%;
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
		
				
	</style>
</head>

<body>
	<div class="container">
	  <div class="row">
	    <div class="col-sm">
	      Matches
	    </div>
	    <div class="col-sm">
	      Club Manager
	    </div>
	    <div class="col-sm">
	    
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-sm">
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
	
	<h3>Budynki klubu</h3>
	<table>
				<tr>
					<th>Nazwa</th>
					<th>Powierzchnia</th>
					<th>Adres</th>
					<th>Typ</th>
					
				</tr>
				<c:forEach var="tempbuilding" items="${buildings}">

					
					<tr>
						<td>${tempbuilding.name}</td>
						<td>${tempbuilding.surface}</td>
						<td>${tempbuilding.address}</td>
						<td>${tempbuilding.type}</td>
						

					</tr>
				</c:forEach>
			</table>
	</div>
	
	<form:form action="${pageContext.request.contextPath}" style="margin-left: 35px; padding-top: 20px; padding-bottom: 10px"
	   method="GET">

		<input type="submit" value="Powrót" />
		
	</form:form>
</body>

</html>