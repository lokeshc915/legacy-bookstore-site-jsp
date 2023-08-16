<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css" />


<body>

	<jsp:include page="header.jsp"></jsp:include>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-8" id="customerLoginForm">
				<div class="card">
					<div class="card-header">Giriş</div>
					<div class="card-body">
						<form id="formLogin" action="login" method="POST">
							<div class="form-group row">
								<label for="email_address"
									class="col-md-4 col-form-label text-md-right">E-Mail
									Addresi</label>
								<div class="col-md-6">
									<input type="text" id="email" class="form-control"
										name="email">
								</div>
							</div>

							<div class="form-group row">
								<label for="password"
									class="col-md-4 col-form-label text-md-right">Şifre</label>
								<div class="col-md-6">
									<input type="password" id="password" class="form-control"
										name="password">
								</div>
							</div>

							<div class="form-group row">
								<div class="col-md-6 offset-md-4">
									<div class="checkbox">
										<label> <input type="checkbox" name="remember">
											Beni Hatırla
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-6 offset-md-4">
								<button type="submit" class="btn btn-primary">Giriş
								</button>
								<a href="resetpassword.jsp" class="btn btn-link"> Şifremi Unuttum </a>
							</div>
						</form>
						
						<c:if test="${message != null}">
							<div class="alert alert-danger" role="alert">
								<c:out value="${message}" />
							</div>
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

<script type="text/javascript">
	
	$(document).ready(function() {
	    $('#formLogin')
	        .bootstrapValidator({
	            feedbackIcons: {
	                valid: 'glyphicon glyphicon-ok',
	                invalid: 'glyphicon glyphicon-remove',
	                validating: 'glyphicon glyphicon-refresh'
	            },
	            fields: {
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
	                            message: 'Password is required and cannot be empty'
	                        }
	                    }
	                },
	                remember: {
	                    validators: {
	                        notEmpty: {
	                            message: 'Accept the terms required and cannot be empty'
	                        }
	                    }
	                }
	            }
	        })
	        
	        
	});
	
	</script>

</body>
</html>