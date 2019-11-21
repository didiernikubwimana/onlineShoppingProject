<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<div class="col-sm-4 col-sm-offset-4">
					<div class="login-form">
						<h2 class="text-center title">Login</h2>
						<div>
							<label style="color: red">${usernameMessage}
								${passwordMessage} </label>
						</div>
						<form action="LoginController" method="POST">
							<input type="text" placeholder="Username" name="username"
								value="${username}" /> <input type="password"
								placeholder="Password" name="password" /> <span> <input
								type="checkbox" class="checkbox" name="rememberMe"> Keep
								me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>

						</form>
					</div>
					<!--/login form-->
				</div>

			</div>

			<div class="row">
				<div class="col-sm-4 col-sm-offset-4" style="margin-top: 20px;">
					<a href="signup.jsp" style="color: #0081b2">Don't have account?
						Sign Up</a>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="fragments/footer.html"%>
</body>
</html>