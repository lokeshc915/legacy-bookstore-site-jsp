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

			<div class="row placeholders">
				<div class="create-new-user">
					<a href="category_forum.jsp"><span
						class="fa fa-list-alt icon-category"></span>
						<h4>Category</h4></a> <span class="text-muted">Create New
						Category</span>
				</div>
			</div>

			<c:if test="${message != null}">
				<c:if test="${danger == 'danger'}">
					<div class="alert alert-danger" role="alert">
						<c:out value="${message}" />
					</div>
				</c:if>
				<c:if test="${danger == null}">
					<div class="alert alert-success" role="alert">
						<c:out value="${message}" />
					</div>
				</c:if>
			</c:if>

			<h2 class="sub-header">List All Categories</h2>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Index</th>
							<th>Category_Id</th>
							<th>Name</th>
							<th>Action</th>
						</tr>
					</thead>
					<%
						int i = 1;
					%>
					<tbody>
						<c:forEach var="category" items="${categoryList}">

							<tr>
								<td><%=i++%></td>
								<td><c:out value="${category.id}" /></td>
								<td><c:out value="${category.name}" /></td>
								<td>
									<form action="edit_delete_category" action="GET"
										class="form-inline">

										<input id="categoryId" name="categoryId" type=hidden
											value="${category.id}">

										<button type="submit" name="button" class="btn btn-warning"
											value="Edit">Edit</button>

										<!-- onclick="javascript:confirmDelete(${user.id})" -->
										<button type="submit" name="button" class="btn btn-danger"
											value="Delete">Delete</button>
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