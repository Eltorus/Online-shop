<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/shop.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Order</title>
</head>
<body>
<c:if test="${empty sessionScope.order.cart}">
	<c:redirect url="cart"/>
</c:if>
<%@ include file="/WEB-INF/elements/local.jspf"%>
<%@ include file="/WEB-INF/elements/header.jspf"%>

	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>№</th>
					<th>Title</th>
					<th>Category</th>
					<th>Price</th>
					<td>Quantity</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${sessionScope.cart.productList}" var="cartLine"
					varStatus="i">
					<tr>
						<td><c:out value="${i.index+1}" /></td>
						<td>${cartLine.product.title}</td>
						<td>${cartLine.product.category}</td>
						<td>${cartLine.product.price}</td>
						<td>${cartLine.quantity}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<h4>${order_value}: ${sessionScope.order.bill}</h4>
	<form action="Controller" method="post">
	<input type="hidden" name="command" value="make_order" />
		<label class="control-label">${address}</label> 
		<div class="col-sm-10">
		<div class="form-group has-feedback has-error" id="addressinput">
		<div class="col-sm-5">
			<input type="text" id="address" class="form-control input-sm chat-input" pattern="^([А-Яа-я\s.])+\,([А-Яа-я\s.])+\,([\s\d])+\,([А-Яа-я\d\s.])$"
				title="Формат: г.Минск, ул. Сурганова, 7, к.5" name="address" required/>
				</div>
		</div>
		
			<input type="submit" id="purchase" class="btn btn-default btn-md" disabled value="${purchase}" />
		</div>
	</form>

	<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.maskedinput.min.js"></script>
<script src="js/reg_script.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>