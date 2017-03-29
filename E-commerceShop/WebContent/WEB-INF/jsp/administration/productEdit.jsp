<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Bootply"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
 <link rel="stylesheet" href="css/shop.css">
 <link rel="stylesheet" href="css/bootstrap.min.css">
 <link rel="stylesheet" href="css/adminstyle.css">
<title>Administration</title>
</head>
<body>
<c:if test="${not empty sessionScope.user and sessionScope.user.is_admin != true}">
 	<c:redirect url="/" />
 </c:if>
<%@ include file="/WEB-INF/elements/local.jspf"%>
<%@ include file="/WEB-INF/elements/header.jspf"%>

	<div class="col-sm-10">
		<form class="form-signin" action="Controller" method="post">
			<input type="hidden" name="command" value="update_product" /> 
			<input type="hidden" name="order_id" value="${requestScope.product.id}" />
			<fieldset>
				<div class="control-group">
					<label class="control-label">Product ID: ${requestScope.product.id}</label>
				</div>

				<div class="control-group">
					<label class="control-label">Title: </label>
					<input required value="${requestScope.product.title}" name="title" type="text" class="form-control" class="input-medium" required>
				</div>
				
				<!-- Should be dropdown here -->
				<div class="control-group">
					<label class="control-label">Category :</label>
					<select class="form-control" id="sel1" name="category">
				        <option>Home Stuff</option>
				        <option>Electronics</option>
				        <option>Clothes</option>
				        <option>Shoes</option>
				      </select>
				</div>
				
				<div class="control-group">
					<label class="control-label">Price: </label>
					<input required value="${requestScope.product.price}" name="price" type="text" class="form-control" class="input-medium" required>
				</div>
				
				
				<div class="control-group">
					<label class="control-label">Description: </label>
					<textarea class="form-control" rows="5" id="comment" name="description">${requestScope.product.description}</textarea>
				</div>

				<div class="control-group">
					<label class="control-label">Amount: </label>
					<input required value="${requestScope.product.amount}" name="amount" type="number" class="form-control" class="input-medium" required>
				</div>
				
				</fieldset>

				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">Submit</button>
					</div>
				</div>
		</form>
			<div class="controls">
				<form action="Controller" method="get">
					<input type="hidden" name="command" value="admin_product_page" />
					<button type="submit" class="btn btn-default">Close</button>
				</form>
		</div>
	</div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/reg_script.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>

</body>
</body>
</html>