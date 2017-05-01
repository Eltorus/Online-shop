<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:if test="${empty requestScope.product}">
	<c:redirect url="/" />
</c:if>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="generator" content="Bootply" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'> <!-- Include font first -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop.css">
	<title>${requestScope.product.title}</title>
</head>
<body>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="thumbnail">
					<div class="product-pict text-center">
						<img src="${requestScope.product.imgPath}" alt="${requestScope.product.imgPath}">
					</div>
				</div>
			</div>
			<div class="text-center">
				<div class="caption">
					<h4>${requestScope.product.title}</h4>
					<h4><fmt:formatNumber pattern="#0.00" value="${requestScope.product.price}" /> ${rubles} </h4>
					<p>${requestScope.product.category}</p>
					<p>${requestScope.product.description}</p>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="add_to_cart" /> <input type="hidden" name="product_id" value="${requestScope.product.id}" />
						<c:choose>
							<c:when test="${product.amount==0}">
								<button type="submit" class="btn btn-primary btn-md" disabled>${cartadd}</button>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn btn-primary btn-md">${cartadd}</button>
							</c:otherwise>
						</c:choose>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>