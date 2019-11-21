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
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<%@ include file="fragments/header.html"%>
	<section class="mt-50">
		<div class="container">
		
			 
<div class="row">
<div  class="col-md-12 mt-5">
<div class="alert alert-success" role="alert">
  ${successMessage}
</div>
</div></div>
 
			<div class="row">

				<div class="col-sm-9">
					<div class="">
						<div class="interactive-credit-card row">
							<div class="form-group col-sm-6">
								<label>Card Number</label> <input class="form-control unknown"
									type="text" name="number" placeholder="Card Number"
									value="${payment.cardNumber}" readonly="readonly">
							</div>
							<div class="form-group col-sm-6">
								<label>Card Owner</label> <input class="form-control"
									type="text" name="name" placeholder="Full Name"
									value="${payment.cardHolder}" readonly="readonly">
							</div>
							<div class="form-group col-sm-3">
								<label>Card Expiry Date</label> <input class="form-control"
									type="text" name="expiry" placeholder="MM/YY"
									value="${payment.expiryDate}" readonly="readonly">
							</div>
							<div class="form-group col-sm-3">
								<label>Card Type</label> <input class="form-control" type="text"
									name="cvc" placeholder="CVC" value="${payment.cardType}"
									readonly="readonly">
							</div>
							<div class="form-group col-sm-6">
								<label>Card Provider</label> <input class="form-control unknown"
									type="text" name="number" placeholder="Card Number"
									value="${payment.cardProvider}" readonly="readonly">
							</div>
						</div>
					</div>
				</div>

				<div class="col-sm-3">
					<div class="order-details">
						<h5>Order summary</h5>
						<form action="OrderController" method="POST">
							<ul class="orders mb-4">
								<li><span>Product</span> <span>Total</span></li>
								<c:forEach var="item" items="${cartItems}">
									<li><span>${item.quantity} X ${item.product.name}</span> <span>
											$ ${item.product.price}</span></li>
								</c:forEach>
								<li class="mt-70"><span>Subtotal</span> <span>$
										${cartTotal}</span></li>
								<li><span>Shipping</span> <span>Free</span></li>
								<li><span>Total</span> <span>$ ${cartTotal}</span></li>
							</ul>
							<button class="btn btn-default pl-order-btn">Place Order</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="fragments/footer.html"%>
</body>
</html>