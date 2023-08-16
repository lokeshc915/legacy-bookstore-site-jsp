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
			
			

			<c:if test="${articleDetail != null}">
				<h2 class="sub-header">Update Article Detail</h2>
			</c:if>

			<c:if test="${articleDetail == null}">
				<h2 class="sub-header">Create Article Detail</h2>
			</c:if>


			<c:if test="${articleDetail != null}">
				<form action="update_article_forum" id="createBookForum" method="POST"
					enctype="multipart/form-data" class="form-horizontal">
					<input id="articleDetailId" name="articleDetailId" type=hidden value="${articleDetail.id}" />
			</c:if>

			<c:if test="${articleDetail == null}">
				<form action="create_article_forum" id="createBookForum" method="POST"
					enctype="multipart/form-data" class="form-horizontal">
			</c:if>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Article Detail Paragragh 1</label>
						<div class="col-sm-8">
							<c:if test="${articleDetail != null}">
								<input type="text" class="form-control" name="article_detail_paragragh1"
									value="${articleDetail.paragragh1}" />
							</c:if>
							<c:if test="${articleDetail == null}">
								<input type="text" class="form-control" name="article_detail_paragragh1" />
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Article Detail Paragragh 2</label>
						<div class="col-sm-8">
							<c:if test="${articleDetail != null}">
								<input type="text" class="form-control" name="article_detail_paragragh2"
									value="${articleDetail.paragragh2}" />
							</c:if>
							<c:if test="${articleDetail == null}">
								<input type="text" class="form-control" name="article_detail_paragragh2" />
							</c:if>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Article Detail Paragragh 3</label>
						<div class="col-sm-8">
							<c:if test="${articleDetail != null}">
								<input type="text" class="form-control" name="article_detail_paragragh3"
									value="${articleDetail.paragragh3}" />
							</c:if>
							<c:if test="${articleDetail == null}">
								<input type="text" class="form-control" name="article_detail_paragragh3" />
							</c:if>
						</div>
					</div>
				</div>			
			</div>

			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Article Detail Image</label>
						<div class="col-sm-5">
							<input type="file" class="form-control-file" id="imageFileUpload"
								name="imageFileUpload" accept=".jpg,.png,.jpeg">
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-sm-3 control-label">Article Detail Image Preview</label>
						<div class="col-sm-5">
							<c:if test="${articleDetail.base64Image ne null}">
								<img id="showImagePreview"
									src="data:image/jpg;base64,${articleDetail.base64Image}" width="85"
									height="85" />
							</c:if>
							<c:if test="${articleDetail.base64Image == null}">
								<img id="showImagePreview" src="#" style="display: none;" />
							</c:if>
						</div>
					</div>
				</div>
			</div>



			<div class="form-group">
				<label class="col-sm-3 control-label">Article Category</label>
				<div class="col-sm-5">
					<c:if test="${articleDetail == null}">
						<select name="category" class="selectpicker">
							<option disabled selected>Kategori Seçin</option>
							<c:forEach var="article" items="${articleList}">
								<option value="${article.id}">${article.name}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${articleDetail != null}">
						<select name="category" class="selectpicker">
							<c:forEach var="article" items="${articleList}">
								<c:if test="${article.id eq articleDetail.article.id}">
									<option value="${article.id}" selected="selected">${article.name}</option>
								</c:if>
								<c:if test="${article.id ne articleDetail.article.id}">
									<option value="${article.id}">${article.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-5 col-sm-offset-3">
					<c:if test="${articleDetail != null}">
						<button type="submit" name="button" class="btn btn-warning"
							value="Update">Update</button>
					</c:if>
					<c:if test="${articleDetail == null}">
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
													article_detail_paragragh1 : {
														validators : {
															notEmpty : {
																message : 'Article Detail_paragragh 1 is required and cannot be empty'
															}
														}
													},
													article_detail_paragragh2 : {
														validators : {
															notEmpty : {
																message : 'Article Detail_paragragh 2 is required and cannot be empty'
															}
														}
													},
													article_detail_paragragh3 : {
														validators : {
															notEmpty : {
																message : 'Article Detail_paragragh 3 is required and cannot be empty'
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
													<c:if test="${articleDetail == null}">
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