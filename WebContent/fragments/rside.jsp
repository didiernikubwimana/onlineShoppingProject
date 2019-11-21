<%@ page import="edu.mum.wap.dao.ProductDAO"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.mum.wap.model.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="rside">
	<h2 class="text-center title">Features Items</h2>
	<c:forEach var="item" items="${allProductList}">
		<div class="col-sm-4">
			<div class="product-image-wrapper">
				<div class="single-products">
					<div class="productinfo text-center">
						<img src="images/home/product1.jpg" alt="" />
						<h2>$56</h2>
						<p>Easy Polo Black Edition</p>
						<a href="#" class="btn btn-default add-to-cart"><i
							class="fa fa-shopping-cart"></i>Add to cart</a>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
