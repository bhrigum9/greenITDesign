<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Design4Green project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
  <link rel="stylesheet" href="resources/css/design4green.css">
	
 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

  

<div class="container">
  <h2 style="text-align:center;">Search for your Doctor</h2>  
  <c:if test="${not empty msg}">
	<div class="alert alert-warning" role="alert">${msg}</div>
  </c:if>
  	<!-- search criteria -->
	<div class="panel panel-default">
  	  <div class="panel-heading">
        <h3 class="panel-title">Search Criteria</h3>
      </div>
      <div class="panel-body">	
		<form method="POST" action="SearchServlet"> 
          <div class="row">
	  		<div class="col-sm-1">
        		<label for="sel1" >Field:</label>
        	</div>
        	<div class="col-sm-3">
  				<select class="col-sm-4 form-control" id="field" name="field" onchange="checkSelected(this);">
				    <option value="firstName">First Name</option>
				    <option value="lasstName">Last Name</option>
 				    <option value="address">Address</option>
 				    <option value="phoneNumber">Phone Number</option>
 				    <option value="email">Email</option>
 				    <option value="speciality">Specialty</option>
	  			</select>
		  	</div>
		  	<div class="col-sm-3">
		  		<input id="search"  name="search" type="text" class="form-control" />
  		   </div>
  		   <div class="col-sm-3">
  		     <input class="btn btn-sample pull-left" type="submit" value="Search">
  		   </div>
  		   </div>
        </form>
      </div>
    </div>
	
	<!-- RESULTS -->
	<div class="panel panel-default">
  	  <div class="panel-heading">
        <h3 class="panel-title">Search Results</h3>
      </div>
      <div class="panel panel-body">
        <table class="table">
			<thead>
				<tr>
					<th></th>
					<th>Name</th>
					<th>Specialty</th>
					<th>Email</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Opening Timings</th>  
				</tr>
			</thead>
  
			<c:forEach var="identity" items="${identities}">
			  <tr>
			    <td>
			    	<c:if test= "${fn:length(identity.image) gt 0}">
			    	  <img src="${identity.image}">
					</c:if>
			    </td>
				<td>${identity.firstName} ${identity.lastName}</td>
				<td>${identity.speciality}</td>
				<td>${identity.emailId}</td>
				<td>${identity.phone }</td>
				<td>${identity.address} ${identity.city}</td>
				<td>${identity.gender}</td>
				<td>${identity.openings.days}</td>
			  </tr>
			</c:forEach>
		</table>
      </div>
    </div>
	

</div>

</body>
</html>
