<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="udf" uri="http://www.your-domain.com/taglib"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kullanıcı Profil</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css">


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.7/dist/css/bootstrap-select.min.css">

<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">


<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" />


</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>


	<div id="registerForm">

		<div class="container-fluid">


			<div
				class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 book-main">

				<h2 class="sub-header">Kullanıcı Profil</h2>


				<form action="update_customer_profile" id="registerCustomerForum"
					method="POST" class="form-horizontal">
					<input id="customerId" name="customerId" type=hidden
						value="${customer.id}" />

					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Kullanıcı Ad</label>
								<div class="col-sm-8">
									<c:if test="${customer != null}">
										<input type="text" class="form-control" name="customer_name"
											value="${customer.name}" />
									</c:if>
									<c:if test="${customer == null}">
										<input type="text" class="form-control" name="customer_name" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Kullanıcı Soyad</label>
								<div class="col-sm-8">
									<c:if test="${customer != null}">
										<input type="text" class="form-control"
											name="customer_surname" value="${customer.surname}" />
									</c:if>
									<c:if test="${customer == null}">
										<input type="text" class="form-control"
											name="customer_surname" />
									</c:if>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Kullanıcı
									Email(Değişmez)</label>
								<div class="col-sm-8">
									<c:if test="${customer != null}">
										<input type="text" class="form-control" name="customer_email"
											value="${customer.email}" readonly />
									</c:if>
									<c:if test="${customer == null}">
										<input type="text" class="form-control" name="customer_email" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Telefon Numara</label>
								<div class="col-sm-8">
									<c:if test="${customer != null}">
										<input type="text" class="form-control"
											name="customer_phoneNumber" value="${customer.phoneNumber}" />
									</c:if>
									<c:if test="${customer == null}">
										<input type="text" class="form-control"
											name="customer_phoneNumber" />
									</c:if>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">İl</label>
								<div class="col-sm-5">
									<c:if test="${customer == null}">
										<select id="cityDropDown" name="city" class="selectpicker"
											onchange="city_change()">
											<option disabled selected>Şehir Seçin</option>
											<c:forEach var="city" items="${cityList}">
												<option value="${city.id}">${city.cityName}</option>
											</c:forEach>
										</select>
									</c:if>
									<c:if test="${customer != null}">
										<select id="cityDropDown" name="city" class="selectpicker"
											onchange="city_change()">
											<c:forEach var="city" items="${cityList}">
												<c:if test="${city.id eq customer.city.id}">
													<option value="${city.id}" selected="selected">${city.cityName}</option>
												</c:if>
												<c:if test="${city.id ne customer.city.id}">
													<option value="${city.id}">${city.cityName}</option>
												</c:if>
											</c:forEach>
										</select>
									</c:if>

								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">İlçe</label>
								<div class="col-sm-5">
									<c:if test="${customer == null}">
										<select id="districtDropdown" name="district"
											class="selectpicker">
											<option disabled selected>İlçe Seçin</option>

										</select>
									</c:if>
									<c:if test="${customer != null}">
										<select id="districtDropdown" name="district"
											class="selectpicker">
											<c:forEach var="district" items="${districtList}">
												<c:if test="${district.id eq customer.district.id}">
													<option value="${district.id}" selected="selected">${district.districtName}</option>
												</c:if>
												<c:if test="${district.id ne customer.district.id}">
													<option value="${district.id}">${district.districtName}</option>
												</c:if>
											</c:forEach>
										</select>
									</c:if>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Adres</label>
								<div class="col-sm-8">
									<c:if test="${customer != null}">
										<input type="text" class="form-control"
											name="customer_address" value="${customer.address}" />
									</c:if>
									<c:if test="${customer == null}">
										<input type="text" class="form-control"
											name="customer_address" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Zip Kod</label>
								<div class="col-sm-8">
									<c:if test="${customer != null}">
										<input type="text" class="form-control"
											name="customer_zipCode" value="${customer.zipCode}" />
									</c:if>
									<c:if test="${customer == null}">
										<input type="text" class="form-control"
											name="customer_zipCode" />
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Şifre(İsteğe
									Bağlı)</label>
								<div class="col-sm-8">
										<input type="text" class="form-control"
											name="customer_password" />
								</div>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<label class="col-sm-3 control-label">Şifre
									Doğrulama(İsteğe Bağlı)</label>
								<div class="col-sm-8">
									<input type="password" class="form-control"
										name="customer_confirm_password" />
								</div>
							</div>
						</div>
					</div>


					<div class="form-group">
						<div class="col-sm-5 col-sm-offset-3">
							<c:if test="${customer != null}">
								<button type="submit" name="button" class="btn btn-warning"
									value="Update">Güncelle</button>
							</c:if>
							<c:if test="${customer == null}">
								<button type="submit" name="button" class="btn btn-primary"
									value="Save">Kaydet</button>
							</c:if>
							<!-- bir geri dönme onclick="window.history.go(-1);" window.history.back(); -->
							<button type="reset" id="buttonCancel" name="button"
								class="btn btn-danger" value="Cancel">İptal</button>
						</div>
					</div>

					<c:if test="${message != null}">
						<c:if test="${success != null}">
							<div class="alert alert-success" role="alert">
								<c:out value="${message}" />
							</div>
						</c:if>

						<c:if test="${success == null}">
							<div class="alert alert-danger" role="alert">
								<c:out value="${message}" />
							</div>
						</c:if>
					</c:if>


				</form>

			</div>

		</div>
	</div>



	<jsp:include page="footer.jsp"></jsp:include>


	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#registerCustomerForum')
									.find('[name="city"]')
									.selectpicker()
									.change(
											function(e) {
												// revalidate the color when it is changed
												$('#registerCustomerForum')
														.bootstrapValidator(
																'revalidateField',
																'colors');
											})
									.end()
									.find('[name="district"]')
									.selectpicker()
									.change(
											function(e) {
												// revalidate the color when it is changed
												$('#registerCustomerForum')
														.bootstrapValidator(
																'revalidateField',
																'colors');
											})
									.end()
									.bootstrapValidator(
											{
												feedbackIcons : {
													valid : 'glyphicon glyphicon-ok',
													invalid : 'glyphicon glyphicon-remove',
													validating : 'glyphicon glyphicon-refresh'
												},
												fields : {
													customer_name : {
														validators : {
															notEmpty : {
																message : 'Kullanıcı Adı boş bırakılmaz'
															}
														}
													},
													customer_surname : {
														validators : {
															notEmpty : {
																message : 'Kullancı Soyadı boş bırakılmaz'
															}
														}
													},
													customer_email : {
														validators : {
															notEmpty : {
																message : 'Kullancı Email Adresi boş bırakılmaz'
															}
														}
													},
													customer_phoneNumber : {
														validators : {
															notEmpty : {
																message : 'Telefon boş bırakılmaz'
															}
														}
													},
													city : {
														validators : {
															notEmpty : {
																message : 'İl seçiniz'
															}
														}
													},
													district : {
														validators : {
															notEmpty : {
																message : 'İlçe Seçiniz'
															}
														}
													},
													customer_address : {
														validators : {
															notEmpty : {
																message : 'Müşteri Adresi boş bırakılmaz'
															}
														}
													},
													customer_zipCode : {
														validators : {
															notEmpty : {
																message : 'Zip Kodu boş bırakılmaz'
															}
														}
													},
													customer_password : {
														validators : {
															identical : {
																field : 'customer_confirm_password',
																message : 'The password and its confirm must be the same'
															},
															regexp : {
																regexp : "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$@!%?&_]).{5,10}$",
																message : 'The password should contain Minimum 5 and Maximum 10 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character:'
															}
														}
													},
													customer_confirm_password : {
														validators : {
															identical : {
																field : 'customer_password',
																message : 'The password and its confirm must be the same'
															}
														}
													}
												}
											})
									// Enable the password/confirm password validators if the password is not empty
									.on(
											'keyup',
											'[name="password"]',
											function() {
												var isEmpty = $(this).val() == '';
												$('registerCustomerForum')
														.bootstrapValidator(
																'enableFieldValidators',
																'customer_password',
																!isEmpty)
														.bootstrapValidator(
																'enableFieldValidators',
																'customer_confirm_password',
																!isEmpty);

												// Revalidate the field when user start typing in the password field
												if ($(this).val().length == 1) {
													$('registerCustomerForum')
															.bootstrapValidator(
																	'validateField',
																	'customer_password')
															.bootstrapValidator(
																	'validateField',
																	'customer_confirm_password');
												}
											});

						});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});

		function city_change() {
			var city_id = $("#cityDropDown").val();
			console.log(city_id);
			$.ajax({
				type : "POST",
				url : "frontend/district.jsp",
				data : "city_id=" + city_id,
				cache : false,
				success : function(response) {
					console.log(response);
					$("#districtDropdown").html(response);
					$('#districtDropdown').selectpicker('refresh');
				}
			});
		}
	</script>


</body>
</html>