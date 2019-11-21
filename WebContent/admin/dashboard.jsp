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

			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="card card-stats">
						<div class="card-body ">
							<div class="row">
								<div class="col-5 col-md-4">
									<div class="icon-big text-center icon-warning">
										<i class="c-icon fa fa-globe text-warning"></i>
									</div>
								</div>
								<div class="col-7 col-md-8">
									<div class="numbers">
										<p class="card-category">Products</p>
										<p class="card-title">${listProduct.size()}</p>
										<p></p>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer ">
							<hr>
							<div class="stats">
								<i class="fa fa-refresh"></i> Update Now
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="card card-stats">
						<div class="card-body ">
							<div class="row">
								<div class="col-5 col-md-4">
									<div class="icon-big text-center icon-warning">
										<i class="c-icon fa fa-shopping-cart text-success"></i>
									</div>
								</div>
								<div class="col-7 col-md-8">
									<div class="numbers">
										<p class="card-category">Orders</p>
										<p class="card-title">${listOrder.size()}</p>
										<p></p>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer ">
							<hr>
							<div class="stats">
								<i class="fa fa-calendar-o"></i> Last day
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="card card-stats">
						<div class="card-body ">
							<div class="row">
								<div class="col-5 col-md-4">
									<div class="icon-big text-center icon-warning">
										<i class="c-icon fa fa-crosshairs text-danger"></i>
									</div>
								</div>
								<div class="col-7 col-md-8">
									<div class="numbers">
										<p class="card-category">Categories</p>
										<p class="card-title">${categories.size()}</p>
										<p></p>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer ">
							<hr>
							<div class="stats">
								<i class="fa fa-clock-o"></i> In the last hour
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="card card-stats">
						<div class="card-body ">
							<div class="row">
								<div class="col-5 col-md-4">
									<div class="icon-big text-center icon-warning">
										<i class="c-icon fa fa-group text-primary"></i>
									</div>
								</div>
								<div class="col-7 col-md-8">
									<div class="numbers">
										<p class="card-category">Users</p>
										<p class="card-title">${listPerson.size()}</p>
										<p></p>
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer ">
							<hr>
							<div class="stats">
								<i class="fa fa-refresh"></i> Update now
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row mt-5">
				<div class="col-md-12">
					<div class="card">
						<div class="card-header">
							<i class="fa fa-table"></i> List of Products
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<div id="dataTable_wrapper"
									class="dataTables_wrapper dt-bootstrap4">

									<div class="row">
										<div class="col-sm-12">
											<table class="table table-bordered dataTable">
												<thead>
													<tr role="row">
														<th>P ID</th>
														<th>Product Name</th>
														<th>Price</th>
														<th>Available. Quantity</th>
														<th>Description</th>
														<!-- 														<th style="width: 92px;">Price</th> -->
													</tr>
												</thead>

												<tbody>
													<c:forEach var="item" items="${listProduct}">
														<tr>
															<td>${item.id}</td>
															<td>${item.name}</td>
															<td>${item.price}</td>
															<td>${item.availableQty}</td>
															<td>${item.description}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>


								</div>
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