<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Store</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
	

<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	
	<div align="center">
		<h2 class="sub-header">Siparişlerim</h2>	
	</div>
	
	<c:if test="${bookOrder eq null}">
		<div align="center">
			<c:if test="${loggedCustomer eq null}">
				<h3>Siparişleri görmek için giriş yapınız</h3>
			</c:if>
			<c:if test="${loggedCustomer ne null}">
				<h3>Herhangi bir sipariş yoktur..</h3>
			</c:if>
		</div>
	</c:if>
	
	<c:if test="${bookOrder ne null}">
		<div class="container-fluid">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

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
								<th></th>
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
									<td><img
													src="data:image/jpg;base64,${detailOrder.book.base64Image}"
													width="25" height="25" /></td>
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

				</div>
			</c:if>
		</div>
		</div>
	</c:if>

	<jsp:include page="footer.jsp"></jsp:include>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

	<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>

</body>
</html>