<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>	
	
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

			<c:if test = "${user != null}">
				<h2 class="sub-header">Update User</h2>
			</c:if>
			
			<c:if test = "${user == null}">
				<h2 class="sub-header">Creating User</h2>
			</c:if>
			
			
			<c:if test = "${user != null}">
				<form action="update_user" id="createUserForum" method="POST" class="form-horizontal">
				<input id="userId" name="userId" type=hidden  value="${user.id}">
			</c:if>
			
			<c:if test = "${user == null}">
				<form action="create_user" id="createUserForum" method="POST" class="form-horizontal">
			</c:if>
				<div class="form-group">
					<label class="col-sm-3 control-label">Name</label>
					<div class="col-sm-5">
						<c:if test = "${user != null}">
							<input type="text" class="form-control" name="first_name" value="${user.name}" />
						</c:if>
						<c:if test = "${user == null}">
							<input type="text" class="form-control" name="first_name" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">Surname</label>
					<div class="col-sm-5">
					    <c:if test = "${user != null}">
							<input type="text" class="form-control" name="last_name" value="${user.surname}" />
						</c:if>
						<c:if test = "${user == null}">
							<input type="text" class="form-control" name="last_name" />
						</c:if>
					</div>
				</div>
				
				<div class="form-group">
			        <label class="col-sm-3 control-label">Email</label>
			        <div class="col-sm-5">
			        	<c:if test = "${user != null}">
							<input type="text" class="form-control" name="email" value="${user.email}" />
						</c:if>
						<c:if test = "${user == null}">
			            	<input type="text" class="form-control" name="email" />
			            </c:if>
			        </div>
			    </div>

				<div class="form-group">
					<label class="col-sm-3 control-label">Password</label>
					<div class="col-sm-5">
						<c:if test = "${user != null}">
							<input type="password" class="form-control" name="password" value="${user.password}" />
						</c:if>
						<c:if test = "${user == null}">
							<input type="password" class="form-control" name="password" />
						</c:if>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">Confirm password</label>
					<div class="col-sm-5">
						<input type="password" class="form-control"
							name="confirm_password" />
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-5 col-sm-offset-3">
					    <c:if test = "${user != null}">
							<button type="submit" name="button" class="btn btn-warning" value="Update">Update</button>
						</c:if>
						<c:if test = "${user == null}">
							<button type="submit" name="button" class="btn btn-primary" value="Save">Save</button>
						</c:if>
						<!-- bir geri dönme onclick="window.history.go(-1);" window.history.back(); -->
						<button type="reset" id="buttonCancel" name="button" class="btn btn-danger" value="Cancel">Cancel</button>
					</div>
				</div>
				
				<c:if test = "${message != null}">
					<div class="alert alert-danger" role="alert">			  	
			         	<c:out value = "${message}"/>   
					</div>
				</c:if>
				
				
			</form>

		</div>

	</div>



	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
	
		$(document).ready(function() {
		    $('#createUserForum')
		        .bootstrapValidator({
		            feedbackIcons: {
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {
		            	first_name: {
		                    validators: {
		                        notEmpty: {
		                            message: 'The full name is required and cannot be empty'
		                        }
		                    }
		                },
		                last_name: {
		                    validators: {
		                        notEmpty: {
		                            message: 'The full name is required and cannot be empty'
		                        }
		                    }
		                },
		                email: {
		                    validators: {
		                        notEmpty: {
		                            message: 'The email address is required and cannot be empty'
		                        },
		                        emailAddress: {
		                            message: 'The email address is not valid'
		                        },
		                        regexp: {
		                            regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
		                            message: 'The value is not a valid email address'
		                        }
		                    }
		                },
		                password: {
		                    validators: {
		                        notEmpty: {
		                            message: 'The password is required and cannot be empty'
		                        },
		                        identical: {
		                            field: 'confirm_password',
		                            message: 'The password and its confirm must be the same'
		                        },
		                        regexp:
		                        {
		                            regexp: "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[$@!%?&_]).{5,10}$",
		                            message: 'The password should contain Minimum 5 and Maximum 10 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet, 1 Number and 1 Special Character:'
		                        }
		                    }
		                },
		                confirm_password: {
		                    validators: {
		                        notEmpty: {
		                            message: 'The confirm password is required and cannot be empty'
		                        },
		                        identical: {
		                            field: 'password',
		                            message: 'The password and its confirm must be the same'
		                        }
		                    }
		                }
		            }
		        })
		        // Enable the password/confirm password validators if the password is not empty
		        .on('keyup', '[name="password"]', function() {
		            var isEmpty = $(this).val() == '';
		            $('#createUserForum')
		                    .bootstrapValidator('enableFieldValidators', 'password', !isEmpty)
		                    .bootstrapValidator('enableFieldValidators', 'confirm_password', !isEmpty);
	
		            // Revalidate the field when user start typing in the password field
		            if ($(this).val().length == 1) {
		                $('#createUserForum').bootstrapValidator('validateField', 'password')
		                                .bootstrapValidator('validateField', 'confirm_password');
		            }
		        });
		    
		    
		    $("#buttonCancel").click(function() {
				history.go(-1);
			});  
		    
		    
		});
	
	</script>
	
</body>
</html>