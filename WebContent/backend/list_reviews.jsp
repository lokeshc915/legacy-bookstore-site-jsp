<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BookStore Administration</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/dashboard.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
	
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>
	
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="index.jsp">Overview</a></li>
					<li><a href="list_users">Users</a></li>
					<li><a href="list_categories">Categories</a></li>
					<li><a href="list_books">Books</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="list_customers">Customers</a></li>
					<li><a href="list_reviews">Reviews</a></li>
					<li><a href="list_orders">Orders</a></li>
					<li><a href="list_articles">Articles</a></li>
					<li><a href="list_article_details">Article Details</a></li>
				</ul>
			</div>

		</div>

		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<div class="row placeholders">
				<div class="create-new-user">
					<span class="fa fa-comments icon-review"></span>
						<h4>Review</h4> <span class="text-muted">Create New Review</span>
				</div>
			</div>
			
			<c:if test = "${message != null}">
				<div class="alert alert-success" role="alert">			  	
		         	<c:out value = "${message}"/>   
				</div>
			</c:if>

			<h2 class="sub-header">List All Reviews</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
						    <th>Index</th>
							<th>Review_Id</th>
							<th>Book</th>
							<th>Customer</th>
							<th>Rating</th>
							<th>Headline</th>
							<th>Comment</th>
							<th>Review Date</th>
							<th>Action</th>
						</tr>
					</thead>
					<% int i=1; %>
					<tbody>
						<c:forEach var="review" items="${reviewList}">

							<tr>
							    <td><%=i++%></td>
								<td><c:out value="${review.id}" /></td>
								<td><c:out value="${review.bookReview.title}" /></td>
								<td><c:out value="${review.customerReview.name}" /> 
								    <c:out value="${review.customerReview.surname}" />
								</td>
								<td><c:out value="${review.rating}" /></td>
								<td><c:out value="${review.headline}" /></td>
								<td><c:out value="${review.reviewComment}" /></td>
								<td><fmt:formatDate value="${review.reviewDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>  
								<td>
									<form action="edit_delete_review" action="GET" class="form-inline">
									
									    <input id="reviewId" name="reviewId" type=hidden  value="${review.id}">
									    
										<button type="submit" name="button" class="btn btn-warning" value="Edit">Edit</button>
										
										<!-- onclick="javascript:confirmDelete(${user.id})" -->
										<button type="submit" name="button" class="btn btn-danger" value="Delete" >Delete</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>