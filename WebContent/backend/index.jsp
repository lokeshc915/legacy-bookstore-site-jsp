<%@page import="com.bookstore.service.BookOrdersService"%>
<%@page import="com.bookstore.service.ReviewService"%>
<%@page import="com.bookstore.service.CustomerService"%>
<%@page import="com.bookstore.service.BookService"%>
<%@page import="com.bookstore.service.CategoryService"%>
<%@page import="com.bookstore.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
	
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>	
	
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">	
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="${pageContext.request.contextPath}/backend/">Overview</a></li>
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

			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="col-lg-2">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<i class="fa fa-users fa-5x"></i>
								</div>
								<div class="col-xs-6 text-right">
									<p class="announcement-heading"><%= new UserService().count() %></p>
									<p class="announcement-text">Users</p>
								</div>
							</div>
						</div>
						<a href="list_users">
							<div class="panel-footer announcement-bottom">
								<div class="row">
									<div class="col-xs-6">View Users</div>
									<div class="col-xs-6 text-right">
										<i class="fa fa-arrow-circle-right"></i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<i class="fa fa-book fa-5x"></i>
								</div>
								<div class="col-xs-6 text-right">
									<p class="announcement-heading"><%=new BookService().count() %></p>
									<p class="announcement-text">Books</p>
								</div>
							</div>
						</div>
						<a href="list_books">
							<div class="panel-footer announcement-bottom">
								<div class="row">
									<div class="col-xs-6">View Books</div>
									<div class="col-xs-6 text-right">
										<i class="fa fa-arrow-circle-right"></i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<i class="fa fa-user fa-5x"></i>
								</div>
								<div class="col-xs-6 text-right">
									<p class="announcement-heading"><%=new CustomerService().count() %></p>
									<p class="announcement-text">Customers</p>
								</div>
							</div>
						</div>
						<a href="list_customers">
							<div class="panel-footer announcement-bottom">
								<div class="row">
									<div class="col-xs-6">View Customers</div>
									<div class="col-xs-6 text-right">
										<i class="fa fa-arrow-circle-right"></i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="panel panel-success">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<i class="fa fa-comments fa-5x"></i>
								</div>
								<div class="col-xs-6 text-right">
									<p class="announcement-heading"><%=new ReviewService().count() %></p>
									<p class="announcement-text">Reviews</p>
								</div>
							</div>
						</div>
						<a href="list_reviews">
							<div class="panel-footer announcement-bottom">
								<div class="row">
									<div class="col-xs-6">View Reviews</div>
									<div class="col-xs-6 text-right">
										<i class="fa fa-arrow-circle-right"></i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="panel panel-info">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<i class="fa fa-truck fa-5x"></i>
								</div>
								<div class="col-xs-6 text-right">
									<p class="announcement-heading"><%=new BookOrdersService().count() %></p>
									<p class="announcement-text">Orders</p>
								</div>
							</div>
						</div>
						<a href="list_orders">
							<div class="panel-footer announcement-bottom">
								<div class="row">
									<div class="col-xs-6">View Orders</div>
									<div class="col-xs-6 text-right">
										<i class="fa fa-arrow-circle-right"></i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-lg-2">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-6">
									<i class="fa fa-list-alt fa-5x"></i>
								</div>
								<div class="col-xs-6 text-right">
									<p class="announcement-heading"><%= new CategoryService().count() %></p>
									<p class="announcement-text">Categories</p>
								</div>
							</div>
						</div>
						<a href="list_categories">
							<div class="panel-footer announcement-bottom">
								<div class="row">
									<div class="col-xs-6">View Categories</div>
									<div class="col-xs-6 text-right">
										<i class="fa fa-arrow-circle-right"></i>
									</div>
								</div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<!-- /.row -->
		</div>

		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">Quick Actions</h1>

			<div class="row placeholders">
				<div class="col-xs-6 col-sm-3 placeholder">
					<a href="book_forum"><span class="fa fa-book icon-book"></span>
						<h4>Book</h4></a> <span class="text-muted">Create New Book</span>
				</div>
				<div class="col-xs-6 col-sm-3 placeholder">
					<a href="user_forum.jsp"><span class="fa fa-users icon-users"></span>
						<h4>User</h4></a> <span class="text-muted">Create New User</span>
				</div>
				<div class="col-xs-6 col-sm-3 placeholder">
					<a href="category_forum.jsp"><span class="fa fa-list-alt icon-category"></span>
						<h4>Category</h4></a> <span class="text-muted">Create New
						Category</span>
				</div>
				<div class="col-xs-6 col-sm-3 placeholder">
					<a href="customer_forum"><span class="fa fa-user icon-customer"></span>
						<h4>Customer</h4></a> <span class="text-muted">Create New
						Customer</span>
				</div>
			</div>

			<h2 class="sub-header">Recent Sales</h2>
			<div class="table-responsive table-show-information">
				<table class="table table-striped">
					<thead>
						<tr>
						    <th>Index</th>
							<th>Order_Id</th>
							<th>Order By</th>
							<th>Book Copies</th>
							<th>Total</th>
							<th>Payment Method</th>
							<th>Status</th>
							<th>Order Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bookOrder" items="${listMostRecentSales}" varStatus="status">

							<tr>
							    <td><c:out value="${status.index + 1}" /></td>
								<td><c:out value="${bookOrder.id}" /></td>
								<td><c:out value="${bookOrder.customerOrders.name}" /> 
								    <c:out value="${bookOrder.customerOrders.surname}" />
								</td>
								<td><c:out value="${bookOrder.allBookCopies}" /></td>
								<td>								
									<i class="fa fa-turkish-lira"></i> <fmt:setLocale
														value="tr_TR" /> <fmt:formatNumber
														value="${bookOrder.orderTotal}" minFractionDigits="2" />								
								</td>
								<td><c:out value="${bookOrder.paymentMethod}" /></td>
								<td><c:out value="${bookOrder.orderStatus}" /></td>
								<td><fmt:formatDate value="${bookOrder.orderDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>  								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<h2 class="sub-header">Recent Reviews</h2>
			<div class="table-responsive table-show-information">
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
						</tr>
					</thead>
					<tbody>
						<c:forEach var="review" items="${listMostRecent}" varStatus="status">

							<tr>
							    <td><c:out value="${status.index + 1}" /></td>
								<td><c:out value="${review.id}" /></td>
								<td><c:out value="${review.bookReview.title}" /></td>
								<td><c:out value="${review.customerReview.name}" /> 
								    <c:out value="${review.customerReview.surname}" />
								</td>
								<td><c:out value="${review.rating}" /></td>
								<td><c:out value="${review.headline}" /></td>
								<td><c:out value="${review.reviewComment}" /></td>
								<td><fmt:formatDate value="${review.reviewDate}" pattern="dd/MM/yyyy HH:mm:ss"/></td>  								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>


		</div>
	</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	
</body>
</html>