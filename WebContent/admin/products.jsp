<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<%@ include file="fragments/header.html" %>
 <div id="wrapper">
  <%@ include file="fragments/sidebar.jsp" %>
 <div id="content-wrapper">
 
 <div class="card">
          <div class="card-header">
            <i class="fa fa-table"></i>
            List of Products</div>
          <div class="card-body">
            <div class="table-responsive">
              <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
            
              <div class="row"><div class="col-sm-12">
              <table class="table table-bordered dataTable">
                <thead>
                  <tr role="row">
                  <th style="width: 165px;">Product Name</th>
                  <th style="width: 253px;">Position</th>
                  <th style="width: 117px;">Office</th>
                  <th style="width: 54px;">Age</th>
                  <th style="width: 114px;">Start date</th>
                  <th style="width: 92px;">Price</th></tr>
                </thead>
                 
                <tbody>

                <tr>
                    <td>Airi Satou</td>
                    <td>Accountant</td>
                    <td>Tokyo</td>
                    <td>33</td>
                    <td>2008/11/28</td>
                    <td>$162,700</td>
                  </tr>   </tbody>
              </table></div></div>
              
              
               </div>
            </div>
          </div>
        
        </div>
 
 <%@ include file="fragments/footer.jsp" %>
 </div>
 </div>
</body>
</html>