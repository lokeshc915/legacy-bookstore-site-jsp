<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${category.name}KitapListesi</title>
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

	<c:if test="${message != null}">
		<div class="alert alert-success" role="alert">
			<c:out value="${message}" />
		</div>
	</c:if>

	<c:if test="${message == null}">
		<div class="container">
			<h3 class="h3">
				<c:out value="${category.name}" />
				Kitap Listesi
			</h3>
			<div class="row">
				<c:forEach var="bookByCategory" items="${bookListByCategory}">
					<div class="col-md-3 col-sm-6">

						<div class="product-grid3">

							<div class="product-image3">
								<img class="pic-1"
									src="data:image/jpg;base64,${bookByCategory.base64Image}">
								<img class="pic-2"
									src="data:image/jpg;base64,${bookByCategory.base64Image}">

								<ul class="social">
									<li><a href="view_book?id=${bookByCategory.id}"
										data-tip="İncele"><i class="fa fa-eye"></i></a></li>
									<li><a href="#" data-tip="Sepete Ekle"><i
											class="fa fa-shopping-cart"></i></a></li>
								</ul>
								<span class="product-new-label">Yeni</span>
							</div>
							<div class="product-content">
								<h3 class="title">
									<a href="view_book?id=${bookByCategory.id}"> <c:out
											value="${bookByCategory.title}" />
									</a>
								</h3>
								<h3 class="author">
									<c:out value="${bookByCategory.author}" />
								</h3>
								<div class="price">
									<i class="fa fa-turkish-lira"></i>
									<c:out value="${bookByCategory.price}" />
									<span><i class="fa fa-turkish-lira"></i>75.00</span>
								</div>
								<ul class="rating">
									<c:forTokens items="${bookByCategory.averageRatingStar}" delims=","
										var="star">
										<c:if test="${star eq 'on'}">
											<li class="fa fa-star"></li>
										</c:if>
										<c:if test="${star eq 'half'}">
											<li class="fa fa-star-half-o"></li>
										</c:if>
										<c:if test="${star eq 'off'}">
											<li class="fa fa-star disable"></li>
										</c:if>

									</c:forTokens>
								</ul>
							</div>

						</div>

					</div>
				</c:forEach>
			</div>
		</div>
	</c:if>

	<jsp:include page="footer.jsp"></jsp:include>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

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