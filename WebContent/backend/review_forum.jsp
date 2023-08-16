<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BookStore Administration</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<link href="${pageContext.request.contextPath}/css/dashboard.css"
	rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker.css"
	rel="stylesheet">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.7/dist/css/bootstrap-select.min.css">


<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/richtext.min.css">


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

			<c:if test="${review != null}">
				<h2 class="sub-header">Update Review</h2>
			</c:if>


			<c:if test="${review != null}">
				<form action="update_review" id="createReviewForum" method="POST"
					class="form-horizontal">
					<input id="reviewId" name="reviewId" type=hidden
						value="${review.id}">
			</c:if>


			<div class="form-group">
				<label class="col-sm-3 control-label">Book Name</label>
				<div class="col-sm-5">
					<c:if test="${review != null}">
						<input type="text" class="form-control" name="review_book_name"
							value="${review.bookReview.title}" readonly />
					</c:if>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Review Rating</label>
				<div class="col-sm-5">
					<c:if test="${review != null}">
						<input type="text" class="form-control" name="review_rating"
							value="${review.rating}" readonly />
					</c:if>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Customer Full Name</label>
				<div class="row">
					<div class="col-sm-2">
						<c:if test="${review != null}">
							<input type="text" class="form-control"
								name="review_customer_name"
								value="${review.customerReview.name}" readonly />
						</c:if>
					</div>
					<div class="col-sm-2">
						<c:if test="${review != null}">
							<input type="text" class="form-control"
								name="review_customer_surname"
								value="${review.customerReview.surname}" readonly />
						</c:if>
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Review Headline</label>
				<div class="col-sm-5">
					<c:if test="${review != null}">
						<input type="text" class="form-control" name="review_headline"
							value="${review.headline}" />
					</c:if>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Review Comment</label>
				<div class="col-sm-5">
					<c:if test="${review != null}">
						<input type="text" class="form-control" name="review_comment"
							value="${review.reviewComment}" />
					</c:if>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-5 col-sm-offset-3">
					<c:if test="${review != null}">
						<button type="submit" name="button" class="btn btn-warning"
							value="Update">Update</button>
					</c:if>
					<!-- bir geri dönme onclick="window.history.go(-1);" window.history.back(); -->
					<button type="reset" id="buttonCancel" name="button"
						class="btn btn-danger" value="Cancel">Cancel</button>
				</div>
			</div>



			<c:if test="${message != null}">
				<c:if test="${success == null }">
					<div class="alert alert-danger" role="alert">
						<c:out value="${message}" />
					</div>
				</c:if>
				<c:if test="${success != null }">
					<div class="alert alert-${success}" role="alert">
						<c:out value="${message}" />
					</div>
				</c:if>
			</c:if>


			</form>

		</div>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$('.content').richText({
			heightPercentage : 70
		});

		$(document)
				.ready(
						function() {
							$('#createReviewForum')
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													review_headline : {
														validators : {
															notEmpty : {
																message : 'Review Headline is required and cannot be empty'
															}
														}
													},
													review_comment : {
														validators : {
															notEmpty : {
																message : 'Review comment is required and cannot be empty'
															}
														}
													}
												}
											})

						});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	</script>

</body>
</html>