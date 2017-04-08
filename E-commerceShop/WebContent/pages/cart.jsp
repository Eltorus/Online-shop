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
<title>Cart</title>
</head>
<body>
<%@ include file="/WEB-INF/elements/local.jspf"%>
<%@ include file="/WEB-INF/elements/header.jspf"%>
<c:choose>
	<c:when test="${not empty sessionScope.cart.productList}">
	<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>№</th>
					<th>${title}</th>
					<th>${category}</th>
					<th>${price}</th>
					<th>${quantity}</th>
					<th></th>
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
						<td><form action="Controller" method="post">
								<input type="hidden" name="command" value="delete_from_cart" />
								<input type="hidden" name="product_index" value="${i.index}" />
								<button type="submit" class="btn btn-default btn-md">${delete}</button>
							</form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		</c:when>
<c:otherwise>
<!-- Убрать align!!!!!!!!! -->
<p align="center">${cart_message}</p><form action="Controller" method="get" align="center"><input type="hidden" name="command" value="catalog" />
                	<button type="submit" class="btn-link">${catalog}</button></form>
</c:otherwise>
</c:choose>


<c:if test="${not empty sessionScope.cart.productList}">
	<c:choose>
		<c:when test="${not empty sessionScope.user}">
		<div id="order" class="col-md-offset-5 col-md-3">
					<form action="Controller" method="post">
					<input type="hidden" name="command" value="order_page"/> 
						<button type="submit" class="btn btn-primary btn-md">${makeorder}</button>
					</form>
					</div>
		</c:when>
<c:otherwise>
<div class="col-md-offset-5 col-md-3">
		<form action="Controller" method="get"><input type="hidden" name="command" value="sign_in_page" />
				<button type="submit" class="btn btn-default btn-md">${makeorder}</button></form>
				</div>
			</c:otherwise>
		</c:choose>
</c:if>	
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>