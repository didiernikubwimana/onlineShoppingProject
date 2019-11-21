<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.mum.wap.dao.ProductDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.mum.wap.model.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>

<link href="css/bootstrap.min.css" rel="stylesheet" media="all">
<link href="css/font-awesome.min.css" rel="stylesheet" media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">

</head>
<body>
	<%-- 	<%@ include file="fragments/header.html"%> --%>
	<header id="header">
		<!--header-->
		<!--header_top-->
		<div class="header_top"></div>
		<!--/header_top-->

		<div class="header-middle">
			<!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-md-4 clearfix">
						<div class="logo pull-left">
							<a href="index.html"><img src="images/log1.png" alt=""
								style="width: 200px;" /></a>
						</div>

					</div>
					<div class="col-md-8 clearfix">
						<div class="shop-menu clearfix pull-right">
							<ul class="nav navbar-nav">
								<li><a href="/wap-project/CheckoutView"><i
										class="fa fa-crosshairs"></i> Checkout</a></li>
								<li><a href="cart.jsp"><i class="fa fa-shopping-cart"></i>
										Cart <span>${cartItems.size()}</span> </a></li>
								<li><a href="/wap-project/LoginView"><i
										class="fa fa-lock"></i> Login</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--/header-middle-->
	
	</header>
	<!--/header-->

	<%@ include file="fragments/slider.html"%>
	<section class="mt-50">
		<div class="container">

			<div class="row">
				<div class="col-sm-3">
					
					<div class="lside">
						<h2>Category</h2>
						<c:forEach var="item" items="${categories}">
							<div class="panel-group category-products">

								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a href="#">${item.name}</a>
										</h4>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
				<div class="col-sm-9">
					<div class="rside">
						<h2 class="text-center title">Features Items</h2>
						<c:forEach var="item" items="${allProductList}">
							<form action="CartController" method="POST">
								<input type="hidden" name="productId" value="${item.id}">
								<input type="hidden" name="cartURL" value="/IndexView">
								<div class="col-sm-4">
									<div class="product-image-wrapper">
										<div class="single-products">
											<div class="productinfo text-center">
												<img class="s-img" src="product-image/${item.image}" alt="" />
												<h2>$ ${item.price}</h2>
												<p>${item.description}</p>
												<button class="btn btn-default add-to-cart">
													<i class="fa fa-shopping-cart"></i>Add to cart
												</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</c:forEach>
					</div>

				</div>
			</div>
		</div>
	</section>

	<%@ include file="fragments/footer.html"%>
</body>
</html>