<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<div class="container">
		<h3 class="h3">Yeni Çıkan Kitaplar</h3>
		<div class="row">
			<c:forEach var="newBook" items="${newBookList}">
				<div class="col-md-3 col-sm-6">

					<div class="product-grid3">
						<div class="product-image3">
							<img class="pic-1"
								src="data:image/jpg;base64,${newBook.base64Image}"> <img
								class="pic-2" src="data:image/jpg;base64,${newBook.base64Image}">

							<ul class="social">
								<li><a href="view_book?id=${newBook.id}"
									data-tip="İncele"><i class="fa fa-eye"></i></a></li>
									<c:set var = "quantity" value = "1"/>
								<li><a href="add_to_cart?bookId=${newBook.id}&bookQuantity=${quantity}" data-tip="Sepete Ekle"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
							<span class="product-new-label">Yeni</span>
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="view_book?id=${newBook.id}"> <c:out
										value="${newBook.title}" />
								</a>
							</h3>
							<h3 class="author">
								<c:out value="${newBook.author}" />
							</h3>
							<div class="price">
								<i class="fa fa-turkish-lira"></i>
								<c:out value="${newBook.price}" />
								<span><i class="fa fa-turkish-lira"></i>75.00</span>
							</div>
							

							<ul class="rating">
								<c:forTokens items="${newBook.averageRatingStar}" delims="," var="star">
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
	<hr>
	<div class="container">
		<h3 class="h3">En Çok Satılan Kitaplar</h3>
		<div class="row">
			<c:forEach var="bestBook" items="${listBestSellingBooks}">
				<div class="col-md-3 col-sm-6">
					<div class="product-grid3">
						<div class="product-image3">
							<img class="pic-1"
								src="data:image/jpg;base64,${bestBook.base64Image}"> <img
								class="pic-2" src="data:image/jpg;base64,${bestBook.base64Image}">
	
							<ul class="social">
								<li><a href="view_book?id=${bestBook.id}"
									data-tip="İncele"><i class="fa fa-eye"></i></a></li>
									<c:set var = "quantity" value = "1"/>
								<li><a href="add_to_cart?bookId=${bestBook.id}&bookQuantity=${quantity}" data-tip="Sepete Ekle"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
	
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="view_book?id=${bestBook.id}"> <c:out
										value="${bestBook.title}" />
								</a>
							</h3>
							<h3 class="author">
								<c:out value="${bestBook.author}" />
							</h3>
							<div class="price">
								<i class="fa fa-turkish-lira"></i>
								<c:out value="${bestBook.price}" />
								<span><i class="fa fa-turkish-lira"></i>75.00</span>
							</div>
							

							<ul class="rating">
								<c:forTokens items="${bestBook.averageRatingStar}" delims="," var="star">
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
	<hr>
	<div class="container">
		<h3 class="h3">En Çok Tercih Edilen Kitaplar</h3>
		<div class="row">
			<c:forEach var="favoredBook" items="${listMostFavoredBooks}">
				<div class="col-md-3 col-sm-6">
					<div class="product-grid3">
						<div class="product-image3">
							<img class="pic-1"
								src="data:image/jpg;base64,${favoredBook.base64Image}"> <img
								class="pic-2" src="data:image/jpg;base64,${favoredBook.base64Image}">
	
							<ul class="social">
								<li><a href="view_book?id=${favoredBook.id}"
									data-tip="İncele"><i class="fa fa-eye"></i></a></li>
									<c:set var = "quantity" value = "1"/>
								<li><a href="add_to_cart?bookId=${favoredBook.id}&bookQuantity=${quantity}" data-tip="Sepete Ekle"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
		
						</div>
						<div class="product-content">
							<h3 class="title">
								<a href="view_book?id=${favoredBook.id}"> <c:out
										value="${favoredBook.title}" />
								</a>
							</h3>
							<h3 class="author">
								<c:out value="${favoredBook.author}" />
							</h3>
							<div class="price">
								<i class="fa fa-turkish-lira"></i>
								<c:out value="${favoredBook.price}" />
								<span><i class="fa fa-turkish-lira"></i>75.00</span>
							</div>
							
	
							<ul class="rating">
								<c:forTokens items="${favoredBook.averageRatingStar}" delims="," var="star">
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