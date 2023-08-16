<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link href="${pageContext.request.contextPath}/css/profile.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" />

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

			<c:if test="${existUser != null}">
				<div class="col-md-4 well information-table">
					<div class="information-table-holder">
						<center>
							<span class="fa fa-users icon-users"></span>
							<h3>User Information</h3>
							<p class="caption">${existUser.name} ${existUser.surname}</p>
							<form action="edit_delete_user" action="GET" class="form-inline">
							    <input id="userId" name="userId" type=hidden  value="${existUser.id}">
							    <button type="submit" name="button" class="btn btn-warning" value="Edit">Edit</button>
							</form>
						</center>

					</div>

					<div class="information-feature-list">
						<ul class="list-group">
							<li class="list-group-item">Id : ${existUser.id}</li>
							<li class="list-group-item">Ad : ${existUser.name}</li>
							<li class="list-group-item">Soyad :${existUser.surname}</li>
							<li class="list-group-item">Email : ${existUser.email}</li>
						</ul>
					</div>


				</div>
			</c:if>
		</div>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>