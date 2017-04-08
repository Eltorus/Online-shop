<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Bootply" />
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
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="Controller?command=admin_order_page">Orders</a></li>
			<li role="presentation"><a href="Controller?command=admin_product_page">Products</a></li>
			<li role="presentation"><a href="Controller?command=admin_user_page">Users</a></li>
		</ul>
		<div class="table-responsive">
			<table class="table table-sm table-hover" id="pTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Client ID</th>
						<th>Order Date</th>
						<th>Delivery Date</th>
						<th>Address</th>
						<th>Bill</th>
						<th>Discount</th>
						<th>Total</th>
						<th>Paid?</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.orderList}" var="order">
						<tr>
							<td>${order.id}</td>
							<td>${order.user.id}</td>
							<td>${order.orderDate}</td>
							<td>${order.deliveryDate}</td>
							<td>${order.address}</td>
							<td><fmt:formatNumber pattern="#0.00" value="${order.bill}"/></td>
							<td>${order.discount*100}%</td>
							<td><fmt:formatNumber pattern="#0.00" value="${order.total}"/></td>
							<td><fmt:formatNumber pattern="#0.00" value="${order.orderPaid}"/></td>
							<td><c:choose>
									<c:when test="${order.orderCompleted == true}">
														Complited
													</c:when>
									<c:otherwise>
														Proccessing
													</c:otherwise>
								</c:choose></td>
							<td>
								<form action="Controller" method="get">
									<input type="hidden" name="command" value="order_details" /> 
									<input type="hidden" name="order_id" value="${order.id}" />
									<button type="submit" class="btn btn-default btn-md">Details</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</body>
</html>