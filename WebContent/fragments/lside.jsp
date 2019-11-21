<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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