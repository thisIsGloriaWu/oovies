<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Top Loved Movies</title>
	<link rel="stylesheet" href="https://bootswatch.com/5/morph/bootstrap.min.css">
</head>
<body>
	<jsp:include page="NavBar.jsp" />
	<button onclick="history.back()" class="btn btn-primary mt-3 ms-3">Back</button>
	<div class="container my-5">
		<c:if test="${sessionScope.loggedIn == true}">
			<div class="container-fluid text-end">
		    	<label class="ms-3 mt-3">Welcome <%= username %></label>
		    </div>
		</c:if>
		
		<h2 class="mb-2">Top 10 Loved Movies </h2>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">MovieId</th>
					<th scope="col">Title</th>
					<th scope="col">ReleaseDate</th>
					<th scope="col">Rating</th>
					<th scope="col">Duration</th>
					<th scope="col">Summary</th>
					<th scope="col">Genre</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${topLovedMovies}" var="movie" >
					<tr>
						<td><a href="moviedetails?id=${movie.getMovieId()}"><c:out value="${movie.getMovieId()}" /></a></td>							<td><c:out value="${movie.getTitle()}" /></td>
						<td><c:out value="${movie.getReleaseDate()}" /></td>
						<td><c:out value="${movie.getRating()}" /></td>
						<td><c:out value="${movie.getDuration()}" /></td>
						<td><c:out value="${movie.getSummary()}" /></td>
						<td><c:out value="${movie.getGenre()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>