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

			<div class="card">
				<div class="card-header">
					<i class="fa fa-table"></i> List of Users
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
												<th>First Name</th>
												<th>Last Name</th>
												<th>Gender</th>
												<th>Email</th>
												<!-- 														<th style="width: 92px;">Price</th> -->
											</tr>
										</thead>

										<tbody>
											<c:forEach var="item" items="${listPerson}">
												<tr>
													<td>${item.id}</td>
													<td>${item.firstName}</td>
													<td>${item.lastName}</td>
													<td>${item.gender}</td>
													<td>${item.email}</td>
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

			<%@ include file="fragments/footer.jsp"%>
		</div>
	</div>
</body>
</html>