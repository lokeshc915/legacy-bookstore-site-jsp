<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sepetim</title>

<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet">

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
	
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" />		


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


	<div class="container" align="center">
		<h1 class="jumbotron-heading">Ödeme Sayfası</h1>
	</div>

	<c:set var="cart" value="${sessionScope['cart']}" />



	<div class="container mb-4">
		<div class="row">
			<div class="col-12">
					<c:if test="${cart.totalItems == 0 }">
					Sepet Boş
					</c:if>
					<c:if test="${cart.totalItems > 0 }">		
						<form id="checkoutUpdateCart" action="checkout_process" action="POST">		
						
						
							<div class="row">
								<div class="col-sm-8">
									<div class="form-group">
										Sepet Bilgisi
										
										<button type="submit" name="button"
										value="Update"
										class="btn btn-info ">Güncelle</button>
										
										<button type="submit" name="button" value="Continue Shopping"
													class="btn btn-warning ">Alışverişe
													Devam Et</button>
									</div>
								</div>
																
							</div>
						
							
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th scope="col">No</th>
											<th scope="col">Ürün Resmi</th>
											<th scope="col">Ürün Adı</th>
											<th scope="col">Yazar</th>
											<th scope="col" class="text-center">Miktar</th>
											<th scope="col" class="text-right">Fiyat</th>
											<th scope="col" class="text-right">Alt Fiyat</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${cart.cart}" var="item" varStatus="status">
											<input id="bookId" name="bookId" type=hidden value="${item.key.id}">
											<tr>
												<td><c:out value="${status.index+1 }" /></td>
												<td><img
													src="data:image/jpg;base64,${item.key.base64Image}"
													width="25" height="25" /></td>
												<td><c:out value="${item.key.title}" /></td>
												<td><c:out value="${item.key.author}" /></td>
												<td>								
												    <input type="text" class="form-control" 
													name="quantity${status.index+1}" value="${item.value}" readonly="readonly" />												
												</td>
												<td class="text-right"><i class="fa fa-turkish-lira"></i> <fmt:setLocale
														value="tr_TR" /> <fmt:formatNumber
														value="${item.key.price}" minFractionDigits="2" /></td>
												<td class="text-right" ><i class="fa fa-turkish-lira"></i> <fmt:setLocale
														value="tr_TR" /> <fmt:formatNumber
														value="${item.value * item.key.price}"
														minFractionDigits="2" /></td>
												<td class="text-center">												  
													<button type="submit" name="button" value="Remove"
														class="btn btn-sm btn-danger">
														<i class="far fa-trash-alt"></i>
													</button>
												</td>
											</tr>
										</c:forEach>


										<tr>
											<td colspan="4"><b>Toplam Ürün Sayısı:</b></td>
											<td class="text-center">${cart.totalQuantity}</td>
											<td></td>
											<td class="text-right"><b>Toplam Fiyat:</b></td>
											<td class="text-center"><i class="fa fa-turkish-lira"></i>
												<fmt:formatNumber value="${cart.totalAmount}"
													minFractionDigits="2" /></td>
										</tr>

									</tbody>
								</table>
							</div>
								<div class="container" align="center">
									<h2 class="jumbotron-heading">Teslimat Bilgisi</h2>
								</div>	
							<div class="table-responsive">
								<table class="table table-striped">
										<th>Ad</th>										
										<td><input type="text" class="form-control" name="recipientName" value="${loggedCustomer.name}" readonly/></td>
									<tr>
										<th>Soyad</th>																				
										<td><input type="text" class="form-control" name="recipientSurname" value="${loggedCustomer.surname}" readonly/></td>
									</tr>
									<tr>
										<th>Tel No</th>										
										<td><input type="text" class="form-control" name="recipientPhone" value="${loggedCustomer.phoneNumber}" readonly/></td>
									</tr>
									<tr>
										<th>Adres</th>										
										<td><input type="text" class="form-control" name="recipientAddress" value="${loggedCustomer.address}" readonly/></td>
									</tr>
									<tr>
										<th>Şehir</th>										
										<td><input type="text" class="form-control" name="recipientCityName" value="${loggedCustomer.city.cityName}" readonly/></td>
									</tr>
									<tr>
										<th>İlçe</th>										
										<td><input type="text" class="form-control" name="recipientDistrictName" value="${loggedCustomer.district.districtName}" readonly/>
										</td>
									</tr>									
									<tr>
										<th>Zip Kodu</th>										
										<td><input type="text" class="form-control" name="recipientZipCode" value="${loggedCustomer.zipCode}" readonly/></td>
									</tr>
									<tr>
										<td>			
												<input id="customerId" name="customerId" type=hidden
													value="${loggedCustomer.id}">
			
												<button type="submit" name="button" class="btn btn-warning"
													value="Edit">Düzenle</button>			
										<td>
									<tr>									
								</table>
																
							</div>
							
							<div class="row">
								<div class="col-sm-2">
									<div class="form-group">
										Ödeme Türü
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<select name="paymentMethod" class="form-control">
												<option disabled selected>Ödeme Türü Seçiniz</option>
												<option value="Cash On Delivery">Kapıda Ödeme</option>
										</select>
									</div>
								</div>								
								<div class="col-sm-2">
									<div class="form-group">
										<button type="submit" name="Checkout" value="Checkout"
											class="btn btn-block btn-success ">Öde</button>
									</div>
								</div>
							</div>
																				
						</form>	
					</c:if>
				
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
	    $("#checkoutUpdateCart")
		    .find('[name="paymentMethod"]')
	            .selectpicker()
	            .change(function(e) {
	                // revalidate the language when it is changed
	                $('#checkoutUpdateCart').bootstrapValidator('revalidateField', 'paymentMethod');
	            })
	            .end()
	        .bootstrapValidator({
	            feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
	            	<c:if test="${param.button eq 'Checkout'}">
	            	paymentMethod : {
						validators : {
							notEmpty : {
								message : 'Ödeme Türünü Seçiniz'
							}
						}
					}
	            	</c:if>
	            }
	            
	        })      
	});
	
	
	</script>
	
	

	<!--  <script type="text/javascript">
	
	$(document).ready(function() {
	    $("#shoppingCart")
	        .bootstrapValidator({
	            feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
	            	<c:forEach items="${cart.cart}" var="item" varStatus="status">
	            		quantity${status.index+1}: {
		                    validators: {
		                        between: {
		                            min: 1,
		                            max: 3,
		                            message: 'The quantity should be between 1 and 3  '
		                        },
		                        notEmpty: {
		                            message: 'The quantity is required'
		                        },
		                        integer: {
		                            message: 'The value is not an integer'
		                        }
		                    }
		                }
	            	</c:forEach>
	            }
	            
	        })      
	});
	
	
	</script>-->

</body>
</html>