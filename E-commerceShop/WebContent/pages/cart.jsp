<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="shoptag"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/shop.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<%@ include file="/WEB-INF/elements/local.jspf"%>
<title>${cart}</title>
</head>
<body>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container-fluid">
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
							<c:forEach items="${sessionScope.cart.productList}" var="cartLine" varStatus="i">
								<tr>
									<td><c:out value="${i.index+1}" /></td>
									<shoptag:outcartline value="${cartLine}" />
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
				<div class="row text-center">
					<p>${cart_message}</p>
					<a href="Controller?command=catalog">${catalog}</a>
				</div>
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty sessionScope.cart.productList}">
			<div class="container-fluid">
				<div id="order" class="col-md-12 text-center">
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="order_page" />
						<button type="submit" class="btn btn-primary btn-md">${makeorder}</button>
					</form>
				</div>
			</div>
		</c:if>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>