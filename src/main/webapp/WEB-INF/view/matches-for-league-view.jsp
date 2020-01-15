<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
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
	
		<form:form action="${pageContext.request.contextPath}/leagues/matches"
					method="GET">

					<input type="submit" value="Mecze" class="btn btn-outline-dark"
						style="margin: 10px;" />

		</form:form>
	<div class="form-group">
			<label for="sel2">Wybierz lige:</label> <select class="form-control"
				id="sel2">

				<c:forEach var="templeague" items="${league}">

					<option>${templeague.name}</option>
				</c:forEach>
			</select>
		</div>
		
		<table>
				<tr>
					<th>Gospodarz</th>
					<th>###</th>
					<th>:</th>
					<th>###</th>
					<th>Gosc</th>
				</tr>

				<c:forEach var="tempMatch" items="${match}">
					<tr>
						<td>${tempMatch.host.name}</td>
						<td>${tempMatch.home_goals}</td>
						<td>:</td>
						<td>${tempMatch.away_goals}</td>						
						<td>${tempMatch.visitor.name}</td>						
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</div>
</body>

</html>