<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop.css">
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<title>${orders}</title>
</head>
<body>
	<c:if test="${empty sessionScope.user}">
		<c:redirect url="signin" />
	</c:if>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container">
		<c:choose>
			<c:when test="${not empty requestScope.orderList}">
				<div class="table-responsive">
					<table class="table table-sm table-hover" id="pTable">
						<thead>
							<tr>
								<th>${order_id}</th>
								<th>Items</th>
								<th>${o_date}</th>
								<th>${del_address}</th>
								<th>${order_value}</th>
								<th>${ord_status}</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.orderList}" var="order">
								<tr>
									<td>${order.id}</td>
									<td><c:forEach items="${order.cart.productList}" var="cartLine" varStatus="i">${title}: ${cartLine.product.title}<br />${price}: <fmt:formatNumber pattern="#0.00" value="${cartLine.product.price}" /><br />${quantity}: ${cartLine.quantity}<br />
											<br />
										</c:forEach></td>
									<td><fmt:formatDate value="${order.orderDate}" pattern="dd.MM.yyyy HH:mm" /></td>
									<td>${order.address}</td>
									<td><fmt:formatNumber pattern="#0.00" value="${order.total}" /> ${rubles}</td>
									<td><c:choose>
											<c:when test="${order.orderCompleted == true}">
										${compl}
									</c:when>
											<c:otherwise>
										${process}
									</c:otherwise>
										</c:choose></td>
									<td>
										<button id="cancelProduct" type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#cancel-order-modal">Cancel</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="modal fade" id="cancel-order-modal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<h4>${r_u_sure} ${cancel_order}?</h4>
									<form action="Controller" method="post">
										<input type="hidden" name="command" value="order_cancel" /> <input type="hidden" id="order-id" name="order_id" value="" />
										<div class="modal-footer">
											<button type="submit" class="btn btn-default">${yes}</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">${no}</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="row text-center">
					<p>${cart_message}</p>
					<a href="Controller?command=catalog">${catalog}</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/order-script.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>