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

		<div class="container-fluid">


			<div
				class="col-sm-9 col-sm-offset-3 col-md-7 col-md-offset-2 book-main">

				<h2 class="sub-header">Kullanıcı Profil Bilgisi</h2>

				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<th>Müşteri No</th>
							<td><c:out value="${existCustomer.id}" /></td>
						<tr>
							<th>Ad</th>
							<td><c:out value="${existCustomer.name}" /></td>
						<tr>
							<th>Soyad</th>
							<td><c:out value="${existCustomer.surname}" /></td>
						</tr>
						<tr>
							<th>Email</th>
							<td><c:out value="${existCustomer.email}" /></td>
						</tr>
						<tr>
							<th>Adres</th>
							<td><c:out value="${existCustomer.address}" /></td>
						</tr>
						<tr>
							<th>Şehir</th>
							<td><c:out value="${existCustomer.city.cityName}" /></td>
						</tr>
						<tr>
							<th>İlçe</th>
							<td><c:out value="${existCustomer.district.districtName}" />
							</td>
						</tr>
						<tr>
							<th>Tel No</th>
							<td><c:out value="${existCustomer.phoneNumber}" /></td>
						</tr>
						<tr>
							<th>Zip Kodu</th>
							<td><c:out value="${existCustomer.zipCode}" /></td>
						</tr>
						<tr>
							<td>
								<form action="edit_customer_profile" action="GET" class="form-inline">

									<input id="customerId" name="customerId" type=hidden
										value="${existCustomer.id}">

									<button type="submit" name="button" class="btn btn-warning"
										value="Edit">Düzenle</button>

								</form>
							<td>
						<tr>
					</table>
					
					<c:if test="${message != null}">
						<c:if test="${success != null}">
							<div class="alert alert-success" role="alert">
								<c:out value="${message}" />
							</div>
						</c:if>

					</c:if>
					
				</div>
			</div>
		</div>
		
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>