<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- top-header -->
<div class="agile-main-top">
	<div class="container-fluid">
		<div class="row main-top-w3l py-2">
		    <div class="col-lg-6 header-right mt-lg-0 mt-2">
		    	<!-- header lists -->
				<ul>
					<li class="text-center text-white">
						<img id="turkishFlag" src="${pageContext.request.contextPath}/flags/tr.svg"><a href="#" data-toggle="modal" data-target="#exampleModal" class="text-white">
							Türkçe </a></img>
					</li>
					<li class="text-center text-white">
						<img id="englishFlag" src="${pageContext.request.contextPath}/flags/gb.svg"><a href="#" data-toggle="modal" data-target="#exampleModal2" class="text-white">
							English</a></img>
					</li>
				</ul>
				<!-- //header lists -->
		    </div>
			<div class="col-lg-6 header-right mt-lg-0 mt-2">
				<!-- header lists -->
				<ul>
					<li class="text-center border-right text-white">
						<i class="fas fa-phone mr-2"></i> 001 234 5678
					</li>
					
					<c:if test="${loggedCustomer == null}">
						<li class="text-center border-right text-white">
						<a href="login" class="text-white">
							<i class="fas fa-sign-in-alt mr-2"></i> Giriş </a>
						</li>
						<li class="text-center text-white">
							<a href="register" class="text-white">
								<i class="fas fa-sign-out-alt mr-2"></i>Kayıt Ol </a>
						</li>
					</c:if>
					
					<c:if test="${loggedCustomer != null}">
						<li class="text-center border-right text-white">
						<a href="view_profile" class="text-white">
							<i class="fas fa-sign-in-alt mr-2"></i> Hoşgeldiniz, ${loggedCustomer.name} ${loggedCustomer.surname}  </a>
						</li>
						<li class="text-center text-white">
							<a href="view_cart" class="text-white">
								<i class="fas fa-list-alt mr-2"></i>Sepetim </a>
						</li>
						<li class="text-center text-white">
							<a href="view_orders" class="text-white">
								<i class="fas fa-list-alt mr-2"></i>Siparişlerim </a>
						</li>
						<li class="text-center text-white">
							<a href="logout" class="text-white">
								<i class="fas fa-sign-out-alt mr-2"></i>Çıkış </a>
						</li>
					</c:if>
				</ul>
				<!-- //header lists -->
			</div>
		</div>
	</div>
</div>

<!-- header-bottom-->
	<div class="header-bot">
		<div class="container">
			<div class="row header-bot_inner_wthreeinfo_header_mid">
				<!-- logo -->
				<div class="col-md-4 logo_agile">
					<h1 class="text-center">
						<a href="${pageContext.request.contextPath}/" class="font-weight-bold font-italic">
							<img src="${pageContext.request.contextPath}/images/logo2.png" alt=" " class="img-fluid">Kitap Evi
						</a>
					</h1>
				</div>
				<!-- //logo -->
				<!-- header-bot -->
				<div class="col-md-8 header mt-4 mb-md-0 mb-4">
					<div class="row">
						<!-- search -->
						<div class="col-10 agileits_search">
							<form class="form-inline" action="search" method="post">
								<input class="form-control mr-sm-2" type="search" name="keyword" placeholder="Arama" aria-label="Search" required>
								<button class="btn my-2 my-sm-0" type="submit">Ara</button>
							</form>
						</div>
						<!-- //search -->
						<!-- cart details -->
						<div class="col-2 top_nav_right text-center mt-sm-0 mt-2">
							<div class="wthreecartaits wthreecartaits2 cart cart box_1">
								<form action="view_cart" method="post" class="last">
									<button class="btn w3view-cart" type="submit" name="submit" value="">
										<i class="fas fa-cart-arrow-down"></i>
									</button>
								</form>
							</div>
						</div>
						<!-- //cart details -->
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!-- shop locator (popup) -->
<!-- //header-bottom -->
<!-- navigation -->
<div class="navbar-inner">
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
			    aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto text-center mr-xl-5">
					<li class="nav-item active mr-lg-2 mb-lg-0 mb-2">
						<a class="nav-link" href="${pageContext.request.contextPath}/">Ana Sayfa
							<span class="sr-only">(current)</span>
						</a>
					</li>
					<c:forEach var="category" items="${categoryList}">
						<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
							<a class="nav-link" href="view_category?id=${category.id}"><c:out value = "${category.name}"/></a>
						</li>
					</c:forEach>
					
					<c:forEach var="article" items="${articleList}">
					    <c:if test="${article.name eq 'Hakkımızda'}">
							<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
								<a class="nav-link" href="about">Hakkımızda</a>
							</li>
						</c:if>
						<c:if test="${article.name eq 'İletişim'}">
							<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
								<a class="nav-link" href="contact.html">İletişim</a>
							</li>
						</c:if>
					</c:forEach>
					
					<!--  <li class="nav-item mr-lg-2 mb-lg-0 mb-2">
						<a class="nav-link" href="product.html">Yeni Çıkanlar</a>
					</li>
					<li class="nav-item mr-lg-2 mb-lg-0 mb-2">
						<a class="nav-link" href="about.html">Hakkımızda</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="contact.html">İletişim</a>
					</li>-->
				</ul>
			</div>
		</nav>
	</div>
</div>
<!-- //navigation -->
	