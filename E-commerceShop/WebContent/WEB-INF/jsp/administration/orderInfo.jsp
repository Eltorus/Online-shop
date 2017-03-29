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
			<input type="hidden" name="command" value="change_order" /> 
			<input type="hidden" name="order_id" value="${requestScope.order.id}" />
			<fieldset>
				<div class="control-group">
					<label class="control-label">Order ID:
						${requestScope.order.id}</label>
				</div>

				<div class="control-group">
					<label class="control-label">User: ${requestScope.order.user.name} ${requestScope.order.user.surname}</label>
				</div>
				<div class="control-group">
					<label class="control-label">Email: ${requestScope.order.user.email} <br/>Phonenumber: ${requestScope.order.user.phonenumber}</label>
				</div>
				<div class="control-group">
					<label class="control-label">Discount: ${requestScope.order.user.discountCoefficient*100} %</label>
				</div>

				<div class="control-group">
					<label class="control-label">Delivery address: </label>
					<div class="controls">
						<input required id="address" value="${requestScope.order.address}" name="address" type="text" class="form-control" class="input-medium" required>
					</div>
				</div>

				<div class="control-group">
					<label class="control-label">Order date: ${requestScope.order.orderDate}</label>
				</div>

				<div class="controls">
					<label class="control-label">Delivery date: </label> <input
						required id="datepicker" placeholder="Delivery Date"
						value="${requestScope.order.deliveryDate}" name="delivery_date"
						type="text" class="form-control" class="input-medium" required>
				</div>


				<div class="col-sm-9">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>№</th>
								<th>Title</th>
								<th>Price</th>
								<th>Quantity</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.order.cart.productList}" var="cartLine" varStatus="i">
								<tr>
									<td><c:out value="${i.index+1}" /></td>
									<td>${cartLine.product.title}</td>
									<td>${cartLine.product.price}</td>
									<td>${cartLine.quantity}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				</fieldset>
				<div class="control-group">
					<label class="control-label">Bill: ${requestScope.order.bill}</label>
				</div>
				<div class="control-group">
					<c:choose>
						<c:when test="${order.orderCompleted == true}">
							<input type="checkbox" name="isOrderComplited" value="Complited" checked>Complited
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="isOrderComplited" value="Complited">Complited
							</c:otherwise>
					</c:choose>
				</div>
				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">Submit</button>
					</div>
				</div>
		</form>
			<div class="controls">
				<form action="Controller" method="get">
					<input type="hidden" name="command" value="admin_order_page" />
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