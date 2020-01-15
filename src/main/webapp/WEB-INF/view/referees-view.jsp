<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
	<title>Referees Page</title>
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
	<div class="container" style="padding-top: 20px">
	  <div class="row">
	    <div class="col-sm">
	      <h3>Sędziowie</h3>
	    </div>
	    <div class="col-sm">
	      <h2>Club Manager</h2>
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
	<div class ="row">
				<div class="col-sm" style="padding: 20px;">
				<input type="button" value="Dodaj sędziego" onclick="window.location.href='showFormForAddReferee'; return false;"
				class="add-button"
				/>
					<table>
						<tr>
							<th>Id</th>
							<th>Imię</th>
							<th>Nazwisko</th>
							<th>Narodowość</th>
							<th>Aktualizacja</th>
						</tr>
						<c:forEach var="tempReferee" items="${referees}">
						<c:url var="updateLink" value="/showFormForRefereeUpdate">
							<c:param name="refereeId" value="${tempReferee.referee_id}" />
						</c:url>
						
							<tr>
								<td> ${tempReferee.referee_id} </td>
								<td> ${tempReferee.name} </td>
								<td> ${tempReferee.surname} </td>
								<td> ${tempReferee.nationality} </td>
								<td>
									<a href="${updateLink}">Aktualizuj</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
	</div>
	<div style="clear; both;"></div>
	
	<form:form action="${pageContext.request.contextPath}" style="margin-left: 20px"
	   method="GET">

		<input type="submit" value="Powrót" />
		
	</form:form>
</body>

</html>