<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${book.title}Bilgisi</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/glyphicon.css">


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

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">



</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<c:if test="${message != null}">
		<c:if test="${success == null }">
			<div class="col-sm-12" style="margin-top: 20px">
				<div class="alert alert-danger" role="alert">
					<c:out value="${message}" />
				</div>
			</div>
		</c:if>
		<c:if test="${success != null }">
			<div class="col-sm-12" style="margin-top: 20px">
				<div class="alert alert-${success}" role="alert">
					<c:out value="${message}" />
				</div>
			</div>
		</c:if>
	</c:if>

	<c:if test="${book != null}">
		<div class="container">
			<form id="addToCart" action="add_to_cart" method="POST">
				<div class="card">
					<div class="row">
						<aside class="col-sm-5 border-right"> <article
							class="gallery-wrap">
						<div class="img-big-wrap">
							<div>
								<img src="data:image/jpg;base64,${book.base64Image}" width=85
									height=85>
							</div>
						</div>
						<!-- slider-product.// -->
						<div class="img-small-wrap">
							<div class="item-gallery">
								<img src="data:image/jpg;base64,${book.base64Image}" width=85
									height=85>
							</div>
							<div class="item-gallery">
								<img src="data:image/jpg;base64,${book.base64Image}" width=85
									height=85>
							</div>
							<div class="item-gallery">
								<img src="data:image/jpg;base64,${book.base64Image}" width=85
									height=85>
							</div>
							<div class="item-gallery">
								<img src="data:image/jpg;base64,${book.base64Image}" width=85
									height=85>
							</div>
						</div>
						<!-- slider-nav.// --> </article> <!-- gallery-wrap .end// --> </aside>
						<aside class="col-sm-7"> <article class="card-body p-5">
						<h3 class="title mb-3">${book.title}</h3>

						<p class="price-detail-wrap">
							<span class="price h3 text-warning">${book.category.name}</span>
							<span>/ ${book.author}</span>
						</p>
						<!-- price-detail-wrap .// -->
						<dl class="item-property">
							<dt>Tanım</dt>
							<dd>
								<p>${book.description}</p>
							</dd>
						</dl>
						<dl class="param param-feature">

							<dt>Yayınlama Tarihi</dt>
							<dd>
								<fmt:formatDate pattern="dd/MM/yyyy" value="${book.publishDate}" />
							</dd>

							<dt>Toplam Yorum Sayısı</dt>
							<dd>
								<c:out value="${fn:length(book.reviews)}" />
							</dd>

							<dt>Değerlendirme</dt>
							<dd id="rating-book">
								<c:forTokens items="${book.averageRatingStar}" delims=","
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
							</dd>
						</dl>


						<!-- item-property-hor .// -->

						<hr>
						<div class="row">
							<div class="col-sm-5">
								<dl class="param param-inline">
									<dt>Adet(Max 3 Ürün):</dt>
									<c:if test="${book.quantity ge 3}">
										<dd>
											<select name="item" class="form-control form-control-sm"
												style="width: 70px;">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select>
										</dd>
									</c:if>
									<c:if test="${book.quantity lt 3 and book.quantity ge 0}">
										<dd>
											<p style="color: red;">Ürün Stokta Kalmamıştır.
											<p>
										</dd>
									</c:if>
								</dl>
								<!-- item-property .// -->
							</div>
							<!-- col.// -->
							<div class="col-sm-7">

								<input id="bookId" name="bookId" type=hidden value="${book.id}">
								<button type="submit" name="button" value="SepeteEkle"
									class="btn btn-lg btn-outline-primary text-uppercase"
									style="margin-top: 10px;">
									<i class="fas fa-shopping-cart"></i> Sepete Ekle
								</button>

							</div>
						</div>
						<!-- row.// --> </article> <!-- card-body.// --> </aside>
						<!-- col.// -->
					</div>
					<!-- row.// -->
				</div>
				<!-- card.// -->
			</form>

		</div>
		<!--container.//-->


		<div id="tabs" class="project-tab">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<nav>
						<div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
							<a class="nav-item nav-link active" id="nav-home-tab"
								data-toggle="tab" href="#nav-home" role="tab"
								aria-controls="nav-home" aria-selected="true">Tanım</a> <a
								class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
								href="#nav-profile" role="tab" aria-controls="nav-profile"
								aria-selected="false">Yorum Ekle</a> <a
								class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab"
								href="#nav-contact" role="tab" aria-controls="nav-contact"
								aria-selected="false">Yorumlar</a>
						</div>
						</nav>
						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade show active" id="nav-home"
								role="tabpanel" aria-labelledby="nav-home-tab">
								<hr />
								<table class="table" cellspacing="0">
									<thead>
										<tr>
											<th>Project Name</th>
											<th>Employer</th>
											<th>Time</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><a href="#">Work 1</a></td>
											<td>Doe</td>
											<td>john@example.com</td>
										</tr>
										<tr>
											<td><a href="#">Work 2</a></td>
											<td>Moe</td>
											<td>mary@example.com</td>
										</tr>
										<tr>
											<td><a href="#">Work 3</a></td>
											<td>Dooley</td>
											<td>july@example.com</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="tab-pane fade" id="nav-profile" role="tabpanel"
								aria-labelledby="nav-profile-tab">
								<hr />

								<c:if test="${existReview != null}">

									<div class="container">
										<div class="row justify-content-center">
											<div class="col-md-8" id="customerLoginForm">
												<div class="card">
													<div class="card-header">Yorum Paneli(Yorum
														Yapılmıştır)</div>
													<div class="card-body">
														<form id="writeReview" method="POST">

															<div class="form-group row">
																<label for="comment_headline"
																	class="col-md-4 col-form-label text-md-right">Yorum
																	Başlığı</label>
																<div class="col-md-6">
																	<input type="text" id="comment_headline"
																		class="form-control" name="comment_headline" readonly
																		value="${existReview.headline}">
																</div>
															</div>

															<div class="form-group row">
																<label for="comment_area"
																	class="col-md-4 col-form-label text-md-right">Yorum</label>
																<div class="col-md-6">
																	<textarea class="form-control" id="comment_area"
																		name="comment_area" rows="3" readonly>
																	<c:out value="${existReview.reviewComment}"></c:out>
																</textarea>
																</div>
															</div>


															<div class="form-group row">
																<label for="comment_rating_star"
																	class="col-md-4 col-form-label text-md-right">Yorum
																	Puan</label>
																<div class="col-md-6">
																	<input id="rating" name="rating" type=hidden />
																	<div id="rateYo"></div>
																</div>
															</div>

														</form>

													</div>
												</div>
											</div>
										</div>
									</div>



								</c:if>

								<c:if test="${existReview == null}">
									<div class="container">
										<div class="row justify-content-center">
											<div class="col-md-8" id="customerLoginForm">
												<div class="card">
													<div class="card-header">Yorum Paneli</div>
													<div class="card-body">
														<form id="writeReview" action="write_review" method="POST">

															<input id="bookId" name="bookId" type=hidden
																value="${book.id}" />

															<div class="form-group row">
																<label for="comment_headline"
																	class="col-md-4 col-form-label text-md-right">Yorum
																	Başlığı</label>
																<div class="col-md-6">
																	<input type="text" id="comment_headline"
																		class="form-control" name="comment_headline">
																</div>
															</div>

															<div class="form-group row">
																<label for="comment_area"
																	class="col-md-4 col-form-label text-md-right">Yorum</label>
																<div class="col-md-6">
																	<textarea class="form-control" id="comment_area"
																		name="comment_area" rows="3"></textarea>
																</div>
															</div>


															<div class="form-group row">
																<label for="comment_rating_star"
																	class="col-md-4 col-form-label text-md-right">Yorum
																	Puan</label>
																<div class="col-md-6">
																	<input id="rating" name="rating" type=hidden />
																	<div id="rateYo"></div>
																</div>
															</div>

															<div class="col-md-6 offset-md-4">
																<button type="submit" class="btn btn-primary">Gönder
																</button>
															</div>
														</form>



													</div>
												</div>
											</div>
										</div>
									</div>
								</c:if>

							</div>
							<div class="tab-pane fade" id="nav-contact" role="tabpanel"
								aria-labelledby="nav-contact-tab">
								<div class="row">
									<div class="col-sm-12">
										<hr />
										<c:forEach items="${book.reviews}" var="review">
											<div class="review-block">

												<div class="row">
													<div class="col-sm-3">
														<i class="fas fa-user fa-3x"></i>
														<div class="review-block-name">
															<p>
																<c:out value="${review.customerReview.name}" />
																<c:out value="${review.customerReview.surname}" />
															</p>
														</div>
														<div class="review-block-date">
															<fmt:formatDate pattern="dd/MM/yyyy"
																value="${review.reviewDate}" />
															<br />
															<fmt:formatDate pattern="HH:mm"
																value="${review.reviewDate}" />
														</div>
													</div>
													<div class="col-sm-9">
														<div class="review-block-rate">
															<c:forTokens items="${review.ratingStar}" delims=","
																var="star">

																<c:if test="${star eq 'on'}">
																	<span class="glyphicon glyphicon-star"></span>
																</c:if>

																<c:if test="${star eq 'half'}">
																	<span class="glyphicon glyphicon-star half"></span>
																</c:if>

																<c:if test="${star eq 'off'}">
																	<span class="glyphicon glyphicon-star empty"></span>
																</c:if>

															</c:forTokens>
														</div>

														<div class="review-block-title">
															<c:out value="${review.headline}" />
														</div>

														<div class="review-block-description">
															<c:out value="${review.reviewComment}" />
														</div>

													</div>
												</div>
												<hr />
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
	
	$(document).ready(function() {
	    $('#writeReview')
	        .bootstrapValidator({
	            feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
	            	comment_headline: {
	            		validators: {
	                        notEmpty: {
	                            message: 'Yorum Başlığını Boş Bırakılmaz'
	                        }
	                    }
	                },
	                comment_area: {
	                    validators: {
	                        notEmpty: {
	                            message: 'Yorum Alanı Boş Bırakılmaz'
	                        }
	                    }
	                }
	            }
	        })
	        
          
	});
	
	$(function () {
		 
		$("#rateYo").rateYo({
        	fullStar: true,
        	<c:if test="${existReview == null}">
        	onSet: function (rating, rateYoInstance) {
        	      $("#rating").val(rating);
        	}
			</c:if>
			<c:if test="${existReview != null}">
			rating: ${existReview.rating},
			readOnly:true
			</c:if>
        }); 
		 
	});
	
	</script>

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

	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>

</body>
</html>