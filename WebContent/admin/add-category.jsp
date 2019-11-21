<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
					<i class="fa fa-table"></i> Add Product Category
				</div>
				<div class="card-body">

					<div class="row">

						<div class="col-sm-12">
							<div class="c-form">
								<form action="../CategoryController" method="POST">
									<div class="form-group row">
										<h5 ${messageColor}>${errorMessage}</h5>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Category
											Name</label>
										<div class="col-sm-9">
											<input type="text" class="form-control"
												placeholder="Category Name" name="name" value="${name}" required="required">
										</div>
									</div>
									<div class="form-group row">
										<label for="inputEmail3" class="col-sm-3 col-form-label">Category
											Description</label>
										<div class="col-sm-9">
											<input type="text" class="form-control"
												placeholder="Category Description" name="description"
												value="${description}" required="required">
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