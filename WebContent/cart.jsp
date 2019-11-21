<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<%@ include file="fragments/header.html"%>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="table-responsive cart_info">
						<table class="table table-condensed">
							<thead>
								<tr class="cart_menu">
									<td>Item</td>
									<td></td>
									<td>Price</td>
									<td>Quantity</td>
									<td>Total</td>
									<td></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${cartItems}">
									<tr>
										<td><a href=""><img src="images/cart/one.png" alt=""></a>
										</td>
										<td>
											<h4>
												<a href="">${item.product.name}</a>
											</h4>
											<p>Web ID: ${item.product.id}</p>
										</td>
										<td>
											<p>$ ${item.product.price}</p>
										</td>
										<td>
											<div class="cart_quantity_button">
												<form action="CartController" method="POST">
													<input type="hidden" name="cartAction" value="add">
													<input type="hidden" name="cartURL" value="CartView">
													<input type="hidden" name="productId"
														value="${item.product.id}">
													<button class="cart_quantity_up" name="action" value="add">+</button>
													<input class="cart_quantity_input" type="text"
														name="quantity" value="${item.quantity}"
														autocomplete="off" size="2">
													<button class="cart_quantity_down" name="action"
														value="minus">-</button>
												</form>
											</div>
										</td>
										<td>
											<p class="cart_total_price">$ ${item.totalAmount}</p>
										</td>
										<td class="cart_delete"><a class="cart_quantity_delete"
											href=""><i class="fa fa-times"></i></a></td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</section>
	<%--  		<%@ include file="fragments/footer.html"%> --%>
</body>
</html>