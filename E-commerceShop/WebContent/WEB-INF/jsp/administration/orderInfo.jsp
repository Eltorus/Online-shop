<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="generator" content="Bootply" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="css/shop.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/adminstyle.css">
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<title>${administration}</title>
</head>
<body>
<c:if test="${sessionScope.user.admin != true}">
 	<c:redirect url="/" />
 </c:if>
<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container">
		<div class="control-group pull-right">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="admin_order_page" />
				<button type="submit" class="btn btn-danger">${close}</button>
			</form>
		</div>
		<form class="form-signin" action="Controller" method="post">
			<input type="hidden" name="command" value="change_order" /> 
			<input type="hidden" name="order_id" value="${requestScope.order.id}" />
			<fieldset>
				<div class="control-group">
					<label class="control-label">${order_id}: ${requestScope.order.id}</label>
				</div>
				<div class="control-group">
					<label class="control-label">${user_loc}: ${requestScope.order.user.name} ${requestScope.order.user.surname}</label>
				</div>
				<div class="control-group">
					<label class="control-label">${email}: ${requestScope.order.user.email} <br />${phonenumber}: ${requestScope.order.user.phonenumber}
					</label>
				</div>
				<div class="control-group">
					<label class="control-label">${del_address}: </label>
					<div class="controls">
						<input required id="address" value="${requestScope.order.address}" name="address" type="text" class="form-control input-medium" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">${o_date}: <fmt:formatDate value="${requestScope.order.orderDate}" pattern="dd.MM.yyyy HH:mm" /></label>
				</div>
				<div class="control-group">
					<label class="control-label">${d_date}: </label> 
						<input required id="datepicker" placeholder="Delivery Date" 
							   value="<fmt:formatDate value="${requestScope.order.deliveryDate}" pattern="dd.MM.yyyy" />" 
							   name="delivery_date" type="text" class="form-control input-medium" required>
				</div>
				<div class="control-group">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>№</th>
								<th>${title}</th>
								<th>${price}</th>
								<th>${quantity}</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.order.cart.productList}" var="cartLine" varStatus="i">
								<tr>
									<td><c:out value="${i.index+1}" /></td>
									<td>${cartLine.product.title}</td>
									<td>${cartLine.product.price} ${rubles}</td>
									<td>${cartLine.quantity}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</fieldset>
			<div class="control-group">
				<label class="control-label">${order_value}: <fmt:formatNumber pattern="#0.00" value="${requestScope.order.bill}" /> ${rubles}</label> 
				<label class="control-label">${discount}: ${requestScope.order.discount*100} %</label> 
				<label class="control-label">${total}: <fmt:formatNumber pattern="#0.00" value="${requestScope.order.total}" /> ${rubles}</label>
			</div>
			<div class="control-group">
				<c:choose>
					<c:when test="${requestScope.order.orderCompleted == true}">
						<input type="checkbox" name="isOrderComplited" id="order-complited" value="Complited" checked>${compl}
						</c:when>
					<c:otherwise>
						<input type="checkbox" name="isOrderComplited" id="order-complited" value="Complited">${compl}
							</c:otherwise>
				</c:choose>
			</div>
			<div class="control-group">
				<div class="controls">
					<button class="btn btn-success" id="submit-order-info">${submit}</button>
				</div>
			</div>
		</form>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/address-date.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</body>
</html>