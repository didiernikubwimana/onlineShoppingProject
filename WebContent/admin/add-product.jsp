<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<%@ include file="fragments/header.html"%>
	<div id="wrapper">
		<%@ include file="fragments/sidebar.jsp"%>
		<div id="content-wrapper">

			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-table"></i> Add Product
				</div>
				<div class="card-body">

					<div class="row">

						<div class="col-sm-12">
							<div class="c-form">
								<form action="../ProductController" method="POST">
									<div class="form-group row">
										<h5 ${messageColor}>${errorMessage}</h5>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Product
											Name</label>
										<div class="col-sm-9">
											<input type="text" class="form-control"
												placeholder="Product Name" value="${product.name}"
												name="name" required="required">
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Product
											Description</label>
										<div class="col-sm-9">
											<input type="text" class="form-control"
												placeholder="Product Description" value="${product.description}"
												name="description" required="required">
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Product
											Price</label>
										<div class="col-sm-9">
											<input type="number" class="form-control"
												placeholder="Product Price" value="${product.price}"
												name="price" required="required">
										</div>
									</div>
									<div class="form-group row">
										<label for="inputPassword3" class="col-sm-3 col-form-label">Category</label>
										<div class="col-sm-9">
											<div class="select">
												<select name="category" id="slct" required="required">
													<option>Choose</option>
													<c:forEach var="item" items="${categories}">
														<option value="${item.id}">${item.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Product
											Quantity</label>
										<div class="col-sm-9">
											<input type="number" class="form-control"
												placeholder="Product Quantity"
												value="${product.availableQty}" name="qty">
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Upload
											Image</label>
										<div class="col-sm-9">
											<input type="file" name="file" id="file" />
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-12 text-right">
											<button type="submit" class="btn btn-primary">CANCEL</button>
											<button type="submit" class="btn btn-default">SUBMIT</button>
										</div>
									</div>
								</form>
							</div>

						</div>
					</div>


				</div>

			</div>

			<%@ include file="fragments/footer.jsp"%>
		</div>
	</div>
</body>
</html>