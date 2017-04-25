<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="Controller?command=admin_order_page">${orders_loc}</a></li>
			<li role="presentation"><a href="Controller?command=admin_product_page">${products_loc}</a></li>
			<li role="presentation"><a href="Controller?command=admin_user_page">${users_loc}</a></li>
		</ul>
		<div class="table-responsive">
			<table class="table table-sm table-hover" id="pTable">
				<thead>
					<tr>
						<th>${order_id}</th>
						<th>${user_ID}</th>
						<th>${o_date}</th>
						<th>${d_date}</th>
						<th>${del_address}</th>
						<th>${order_value}</th>
						<th>${discount}</th>
						<th>${total}</th>
						<th>${ord_status}</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.orderList}" var="order">
						<tr>
							<td>${order.id}</td>
							<td>${order.user.id}</td>
							<td><fmt:formatDate value="${order.orderDate}" pattern="dd.MM.yyyy HH:mm" /></td>
							<td>
								<c:choose>
									<c:when test="${empty order.deliveryDate}">
										-
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${order.deliveryDate}" pattern="dd.MM.yyyy" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${order.address}</td>
							<td><fmt:formatNumber pattern="#0.00" value="${order.bill}"/> ${rubles}</td>
							<td>${order.discount*100}%</td>
							<td><fmt:formatNumber pattern="#0.00" value="${order.total}"/> ${rubles}</td>
							<td><c:choose>
									<c:when test="${order.orderCompleted == true}">
														${compl}
													</c:when>
									<c:otherwise>
														${process}
													</c:otherwise>
								</c:choose></td>
							<td>
								<form action="Controller" method="get">
									<input type="hidden" name="command" value="order_details" /> 
									<input type="hidden" name="order_id" value="${order.id}" />
									<button type="submit" class="btn btn-default btn-md">${details}</button>
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