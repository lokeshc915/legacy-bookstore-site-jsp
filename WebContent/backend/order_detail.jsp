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

			<c:if test="${bookOrder != null}">
				<h2 class="sub-header">
					Sipariş #
					<c:out value="${bookOrder.id}" />
				</h2>

				<div class="table-responsive">
					<h2 class="sub-header">Sipariş Bilgisi</h2>
					<table class="table table-striped">
						<tr>
							<th>Sipariş Veren</th>
							<td><c:out value="${bookOrder.customerOrders.name}" /> <c:out
									value="${bookOrder.customerOrders.surname}" /></td>
						<tr>
							<th>Kitap Adeti</th>
							<td><c:out value="${bookOrder.allBookCopies}" /></td>
						<tr>
							<th>Toplam Fiyat</th>
							<td><i class="fa fa-turkish-lira"></i> <fmt:setLocale
									value="tr_TR" /> <fmt:formatNumber
									value="${bookOrder.orderTotal}" minFractionDigits="2" /></td>
						</tr>
						<tr>
							<th>Alıcı (Ad Soyad)</th>
							<td><c:out value="${bookOrder.recipentName}" /></td>
						</tr>
						<tr>
							<th>Alıcı Tel No</th>
							<td><c:out value="${bookOrder.recipentPhone}" /></td>
						</tr>
						<tr>
							<th>Teslimat Adres</th>
							<td><c:out value="${bookOrder.shippingAddress}" /></td>
						</tr>
						<tr>
							<th>Ödeme Durumu</th>
							<td><c:out value="${bookOrder.paymentMethod}" /></td>
						</tr>
						<tr>
							<th>Sipariş Durum</th>
							<td><c:out value="${bookOrder.orderStatus}" /></td>
						</tr>
						<tr>
							<th>Sipariş Tarihi</th>
							<td><c:out value="${bookOrder.orderDate}" /></td>
						</tr>
					</table>

					<h2 class="sub-header">Sipariş Edilenler</h2>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Index</th>
								<th>Kitap Adı</th>
								<th>Yazar</th>
								<th>Fiyat</th>
								<th>Miktar</th>
								<th>Adet Fiyat</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="detailOrder" items="${bookOrder.orderDetails}"
								varStatus="status">
								<tr>
									<td><c:out value="${status.index + 1}" /></td>
									<td><c:out value="${detailOrder.book.title}" /></td>
									<td><c:out value="${detailOrder.book.author}" /></td>
									<td><i class="fa fa-turkish-lira"></i> <fmt:setLocale
											value="tr_TR" /> <fmt:formatNumber
											value="${detailOrder.book.price}" minFractionDigits="2" /></td>
									<td><c:out value="${detailOrder.quantity}" /></td>
									<td><i class="fa fa-turkish-lira"></i> <fmt:setLocale
											value="tr_TR" /> <fmt:formatNumber
											value="${detailOrder.subTotal}" minFractionDigits="2" /></td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4" align="right">Toplam :</td>
								<td><c:out value="${bookOrder.allBookCopies}" /></td>
								<td><i class="fa fa-turkish-lira"></i> <fmt:setLocale
										value="tr_TR" /> <fmt:formatNumber
										value="${bookOrder.orderTotal}" minFractionDigits="2" /></td>
							</tr>
						</tbody>

					</table>

					<div class="row">

						<form action="edit_delete_detail_order" action="GET"
							class="form-inline">

							<input id="orderId" name="orderId" type=hidden
								value="${bookOrder.id}">

							<div class="col-sm-12  col-md-3 ">
								<button type="submit" name="button"
									class="btn btn-warning btn-block" value="Edit">Edit</button>
							</div>
							<div class="col-sm-12 col-md-3 ">
								<!-- onclick="javascript:confirmDelete(${user.id})" -->
								<button type="submit" name="button"
									class="btn btn-danger btn-block" value="Delete">Delete</button>
							</div>
							<div class="col-sm-12 col-md-3 ">
								<button type="reset" id="buttonCancel" name="button"
									class="btn btn-danger btn-block" value="Cancel">Cancel</button>
							</div>

						</form>

					</div>

				</div>
			</c:if>
		</div>

	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {

			$("#buttonCancel").click(function() {
				history.go(-1);
			});

		});
	</script>

</body>
</html>