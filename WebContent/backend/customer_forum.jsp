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

		<div
			class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 book-main">

			<c:if test="${customer != null}">
				<h2 class="sub-header">Update Customer</h2>
			</c:if>

			<c:if test="${customer == null}">
				<h2 class="sub-header">Create Customer</h2>
			</c:if>


			<c:if test="${customer != null}">
				<form action="update_customer" id="createCustomerForum"
					method="POST" class="form-horizontal">
					<input id="customerId" name="customerId" type=hidden
						value="${customer.id}" />
			</c:if>

			<c:if test="${customer == null}">
				<form action="create_customer" id="createCustomerForum"
					method="POST" class="form-horizontal">
			</c:if>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Customer Name</label>
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
						<label class="col-sm-3 control-label">Customer Surname</label>
						<div class="col-sm-8">
							<c:if test="${customer != null}">
								<input type="text" class="form-control" name="customer_surname"
									value="${customer.surname}" />
							</c:if>
							<c:if test="${customer == null}">
								<input type="text" class="form-control" name="customer_surname" />
							</c:if>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Customer Email</label>
						<div class="col-sm-8">
							<c:if test="${customer != null}">
								<input type="text" class="form-control" name="customer_email"
									value="${customer.email}" />
							</c:if>
							<c:if test="${customer == null}">
								<input type="text" class="form-control" name="customer_email" />
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Customer Phone
							Number</label>
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
						<label class="col-sm-3 control-label">City</label>
						<div class="col-sm-5">
							<c:if test="${customer == null}">
								<select id="cityDropDown" name="city" class="selectpicker" onchange="city_change()">
									<option disabled selected>Şehir Seçin</option>
									<c:forEach var="city" items="${cityList}">
										<option value="${city.id}">${city.cityName}</option>
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${customer != null}">
								<select id="cityDropDown" name="city" class="selectpicker" onchange="city_change()">
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
						<label class="col-sm-3 control-label">District</label>
						<div class="col-sm-5">
							<c:if test="${customer == null}">
								<select id="districtDropdown" name="district" class="selectpicker">
									<option disabled selected>İlçe Seçin</option>
									
								</select>
							</c:if>
							<c:if test="${customer != null}">
								<select id="districtDropdown" name="district" class="selectpicker">
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
						<label class="col-sm-3 control-label">Customer Address</label>
						<div class="col-sm-8">
							<c:if test="${customer != null}">
								<input type="text" class="form-control" name="customer_address"
									value="${customer.address}" />
							</c:if>
							<c:if test="${customer == null}">
								<input type="text" class="form-control" name="customer_address" />
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Customer Zip Code</label>
						<div class="col-sm-8">
							<c:if test="${customer != null}">
								<input type="text" class="form-control" name="customer_zipCode"
									value="${customer.zipCode}" />
							</c:if>
							<c:if test="${customer == null}">
								<input type="text" class="form-control" name="customer_zipCode" />
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Customer Password</label>
						<div class="col-sm-8">
							<c:if test="${customer != null}">
								<input type="text" class="form-control" name="customer_password"
									value="${customer.password}" />
							</c:if>
							<c:if test="${customer == null}">
								<input type="text" class="form-control" name="customer_password" />
							</c:if>
						</div>
					</div>
				</div>
			</div>



			<div class="form-group">
				<div class="col-sm-5 col-sm-offset-3">
					<c:if test="${customer != null}">
						<button type="submit" name="button" class="btn btn-warning"
							value="Update">Update</button>
					</c:if>
					<c:if test="${customer == null}">
						<button type="submit" name="button" class="btn btn-primary"
							value="Save">Save</button>
					</c:if>
					<!-- bir geri dönme onclick="window.history.go(-1);" window.history.back(); -->
					<button type="reset" id="buttonCancel" name="button"
						class="btn btn-danger" value="Cancel">Cancel</button>
				</div>
			</div>

			<c:if test="${message != null}">
				<div class="alert alert-danger" role="alert">
					<c:out value="${message}" />
				</div>
			</c:if>


			</form>

		</div>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>
	
	

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('#createCustomerForum')
									.find('[name="city"]')
									.selectpicker()
									.change(
											function(e) {
												// revalidate the color when it is changed
												$('#createCustomerForum')
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
												$('#createCustomerForum')
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
																message : 'Customer name is required and cannot be empty'
															}
														}
													},
													customer_surname : {
														validators : {
															notEmpty : {
																message : 'Customer surname is required and cannot be empty'
															}
														}
													},
													customer_email : {
														validators : {
															notEmpty : {
																message : 'Customer email is required and cannot be empty'
															}
														}
													},
													customer_phoneNumber : {
														validators : {
															notEmpty : {
																message : 'Customer Phone is required and cannot be empty'
															}
														}
													},
													book_quantity : {
														validators : {
															notEmpty : {
																message : 'Book Quantity is required and cannot be empty'
															}
														}
													},
													city : {
														validators : {
															notEmpty : {
																message : 'Please select your city'
															}
														}
													},
													district : {
														validators : {
															notEmpty : {
																message : 'Please select your district'
															}
														}
													},
													customer_address : {
														validators : {
															notEmpty : {
																message : 'Customer Address is required and cannot be empty'
															}
														}
													},
													customer_zipCode : {
														validators : {
															notEmpty : {
																message : 'Customer Zipcode is required and cannot be empty'
															}
														}
													},
													customer_password : {
														validators : {
															notEmpty : {
																message : 'Customer Password is required and cannot be empty'
															}
														}
													}

												}
											})
																													

						});
		
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
		
		
		function city_change()
		{
		    var city_id = $("#cityDropDown").val();
		    console.log(city_id);
		    $.ajax({
		        type: "POST",
		        url: "district.jsp",
		        data: "city_id="+city_id,
		        cache: false,
		        success: function(response)
		        {
		        	console.log(response);
		            $("#districtDropdown").html(response);
		            $('#districtDropdown').selectpicker('refresh');
		        }
		    });
		}
		
		
		
		/*$('#cityDropDown').change(function(event) {
			var cityId = $("select#cityDropDown").val(); 
			$.get('DistrictServlet', {
				cityId : cityId
			}, function(response) {

				var select = $('#districtDropDown');
				$.each(response, function(index, value) {
					$('<option>').val(district.id).text(district.name).appendTo(select);
				});
			});
		});	
		*/
		
	</script>


</body>
</html>