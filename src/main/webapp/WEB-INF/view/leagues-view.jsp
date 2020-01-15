<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>

<html lang="en">

<head>
	<title>Leagues Page</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container">
	  <div class="row">
	    <div class="col-sm">
	      Leagues
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
	
	<div class="container">
	
		
		<form:form action="${pageContext.request.contextPath}/leagues-table"
			method="GET">
	<div class="form-group">
			<label for="sel1">Wybierz lige:</label> <select class="form-control"
				id="sel11" name="selected">

				<c:forEach var="templeague" items="${league}">

					<option value="${templeague.league_id}">${templeague.name}</option>
				</c:forEach>
			</select>
		</div>
		<input type="submit" value="Wyświetl tabelę" class="btn btn-outline-dark"
				style="border: 1px solid #666;
			border-radius: 5px;
			padding: 4px;
			font-size: 12px;
			font-weight: bold;
			width: 150px;
			padding: 5px 10px;			
			margin-bottom: 15px;
			background: #cccccc;
			font-family: Verdana, Arial, Helvetica, sans-serif;" />
			
		</form:form>
		
		
		
		</div>
		
	</div>
	
	<form:form action="${pageContext.request.contextPath}" style="margin-left: 35px; padding-top: 20px; padding-bottom: 10px"
	   method="GET">

		<input type="submit" value="Powrót" />
		
	</form:form>
</body>

</html>