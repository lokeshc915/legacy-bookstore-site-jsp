<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	<div align="center">
		<h2 class="sub-header">Siparişlerim</h2>	
	</div>
	
	<c:if test="${fn:length(listOrders) == 0}">
		<div align="center">
			<h3>Herhangi bir sipariş yoktur..</h3>
		</div>
	</c:if>
	
	<c:if test="${fn:length(listOrders) > 0}">
		<div class="container-fluid">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
							    <th>Index</th>
								<th>Sipariş ID</th>
								<th>Miktar</th>
								<th>Toplam Fiyat</th>
								<th>Sipariş Tarihi</th>
								<th>Durum</th>
								<th>Eylemler</th>
							</tr>
							<c:forEach var="order" items="${listOrders}" varStatus="status">
								<tr>
									<td>${status.index + 1}</td>
									<td>${order.id}</td>				
									<td>${order.allBookCopies}</td>
									<td>
										<i class="fa fa-turkish-lira"></i> <fmt:setLocale
														value="tr_TR" /> <fmt:formatNumber
														value="${order.orderTotal}"
														minFractionDigits="2" />
									</td>				
									<td>${order.orderDate}</td>
									<td>${order.orderStatus}</td>
									<td>
									
										<form action="show_order_detail" action="GET" class="form-inline">
									
										    <input id="orderId" name="orderId" type=hidden  value="${order.id}">
										    
										    <button type="submit" name="button" class="btn btn-info" value="Detail">Ayrıntılar</button>
										    
										</form>
									
									</td>
								</tr>
							</c:forEach>
						</thead>
					</table>
				</div>	
			</div>
		</div>
	</c:if>

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