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

			<c:if test="${book != null}">
				<h2 class="sub-header">Update Book</h2>
			</c:if>

			<c:if test="${book == null}">
				<h2 class="sub-header">Create Book</h2>
			</c:if>


			<c:if test="${book != null}">
				<form action="update_book" id="createBookForum" method="POST"
					enctype="multipart/form-data" class="form-horizontal">
					<input id="bookId" name="bookId" type=hidden value="${book.id}" />
			</c:if>

			<c:if test="${book == null}">
				<form action="create_book" id="createBookForum" method="POST"
					enctype="multipart/form-data" class="form-horizontal">
			</c:if>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Title</label>
						<div class="col-sm-8">
							<c:if test="${book != null}">
								<input type="text" class="form-control" name="book_title"
									value="${book.title}" />
							</c:if>
							<c:if test="${book == null}">
								<input type="text" class="form-control" name="book_title" />
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Author</label>
						<div class="col-sm-8">
							<c:if test="${book != null}">
								<input type="text" class="form-control" name="book_author"
									value="${book.author}" />
							</c:if>
							<c:if test="${book == null}">
								<input type="text" class="form-control" name="book_author" />
							</c:if>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book ISBN</label>
						<div class="col-sm-8">
							<c:if test="${book != null}">
								<input type="text" class="form-control" name="book_isbn"
									value="${book.isbn}" />
							</c:if>
							<c:if test="${book == null}">
								<input type="text" class="form-control" name="book_isbn" />
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Price</label>
						<div class="col-sm-8">
							<c:if test="${book != null}">
								<input type="text" class="form-control" name="book_price"
									value="${book.price}" />
							</c:if>
							<c:if test="${book == null}">
								<input type="text" class="form-control" name="book_price" />
							</c:if>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Quantity</label>
						<div class="col-sm-8">
							<c:if test="${book != null}">
								<input type="text" class="form-control" name="book_quantity"
									value="${book.quantity}" />
							</c:if>
							<c:if test="${book == null}">
								<input type="text" class="form-control" name="book_quantity" />
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Publish Date</label>
						<div class="col-sm-8">
							<c:if test="${book != null}">
								<div class="input-group date" id="datetimepicker">
									<input type="text" class="form-control" name="publishDate"
										value="<fmt:formatDate value='${book.publishDate}' pattern='dd/mm/yyyy'/>" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</c:if>
							<c:if test="${book == null}">
								<div class="input-group date" id="datetimepicker">
									<input type="text" class="form-control" name="publishDate" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Image</label>
						<div class="col-sm-5">
							<input type="file" class="form-control-file" id="imageFileUpload"
								name="imageFileUpload" accept=".jpg,.png,.jpeg">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Book Image Preview</label>
						<div class="col-sm-5">
							<c:if test="${book.base64Image ne null}">
								<img id="showImagePreview"
									src="data:image/jpg;base64,${book.base64Image}" width="85"
									height="85" />
							</c:if>
							<c:if test="${book.base64Image == null}">
								<img id="showImagePreview" src="#" style="display: none;" />
							</c:if>
						</div>
					</div>
				</div>
			</div>



			<div class="form-group">
				<label class="col-sm-3 control-label">Category</label>
				<div class="col-sm-5">
					<c:if test="${book == null}">
						<select name="category" class="selectpicker">
							<option disabled selected>Kategori Seçin</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${book != null}">
						<select name="category" class="selectpicker">
							<c:forEach var="category" items="${categoryList}">
								<c:if test="${category.id eq book.category.id}">
									<option value="${category.id}" selected="selected">${category.name}</option>
								</c:if>
								<c:if test="${category.id ne book.category.id}">
									<option value="${category.id}">${category.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Book Description</label>
				<div class="col-sm-9">
					<c:if test="${book != null}">
						<textarea class="content" name="book_description">${book.description}</textarea>
						<!--  <textarea class="form-control" name="book_description" rows="3">${book.description}</textarea>-->
					</c:if>
					<c:if test="${book == null}">
						<textarea class="content" name="book_description" rows="3"></textarea>
					</c:if>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-5 col-sm-offset-3">
					<c:if test="${book != null}">
						<button type="submit" name="button" class="btn btn-warning"
							value="Update">Update</button>
					</c:if>
					<c:if test="${book == null}">
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
	
		$('.content').richText({heightPercentage: 70});
	
		$(document)
				.ready(
						function() {
							$('#createBookForum')
									.find('[name="category"]')
									.selectpicker()
									.change(
											function(e) {
												// revalidate the color when it is changed
												$('#createBookForum')
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
													book_title : {
														validators : {
															notEmpty : {
																message : 'Book title is required and cannot be empty'
															}
														}
													},
													book_author : {
														validators : {
															notEmpty : {
																message : 'Book author is required and cannot be empty'
															}
														}
													},
													book_isbn : {
														validators : {
															notEmpty : {
																message : 'Book ISBN is required and cannot be empty'
															}
														}
													},
													book_price : {
														validators : {
															notEmpty : {
																message : 'Book Price is required and cannot be empty'
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
													category : {
														validators : {
															notEmpty : {
																message : 'Please select your category'
															}
														}
													},
													publishDate : {
														validators : {
															notEmpty : {
																message : 'Please select your publish date'
															}
														}
													},
													book_description : {
														validators : {
															notEmpty : {
																message : 'Book Description is required'
															}
														}
													},
													<c:if test="${book == null}">
													imageFileUpload : {
														validators : {
															file : {
																extension : 'jpeg,png,jpg',
																type : 'image/jpeg,image/png,image/jpg',
																maxSize : 2048 * 1024,
																message : 'The selected file is not valid'
															},
															notEmpty : {
																message : 'Please select your file'
															}
														}
													}
													</c:if>
												}
											})

							$('#datetimepicker').on(
									'dp.change dp.show',
									function(e) {
										$('#createBookForum')
												.bootstrapValidator(
														'revalidateField',
														'publishDate');
									});

						});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var i;
				for (i = 0; i < input.files.length; ++i) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$('#showImagePreview').attr('src', e.target.result);

						$('#showImagePreview').attr({
							height : "85px",
							width : "85px"
						});
						
						$("#showImagePreview").removeAttr("style");
						
						//$('#showImagePreview').attr('style', 'width : 85px');
						//$('#showImagePreview').attr('style', 'height : 85px');
					}
					reader.readAsDataURL(input.files[0]);
				}
			}
		}

		$("#imageFileUpload").change(function() {
			readURL(this);
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});

		$('#datetimepicker').datepicker({
			language : 'tr',
			format : 'dd/mm/yyyy',
			orientation: "bottom" // add this
		});
		
		

		
	</script>

</body>
</html>