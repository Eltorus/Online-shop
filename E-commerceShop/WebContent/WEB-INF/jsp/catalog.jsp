<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="generator" content="Bootply" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'> <!-- Include font first -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop.css">
	<%@ include file="../elements/local.jspf"%>
	<title>${catalog}</title>
</head>
<body>
	<%@ include file="../elements/header.jspf"%>
	<div class="container">
		<div class="row">
			<c:forEach items="${requestScope.products}" var="product">
				<div class="col-sm-3 col-lg-3 col-md-5">
					<div class="thumbnail">
					<div class="product-pict">
							<a href="Controller?command=product_page&product_id=${product.id}"> <img src="${product.imgPath}" class="product" alt="${product.imgPath}"></a>
					</div>
						<div class="caption">
							<h4 class="pull-right"><fmt:formatNumber pattern="#0.00" value="${product.price}"/> ${rubles}</h4>
							<h4>
								<a href="Controller?command=product_page&product_id=${product.id}">${product.title}</a>
							</h4>
							<p>
								${product.category}
							</p>
								<c:choose>
								<c:when test="${product.amount==0}">
									<button type="button" class="btn btn-xs pull-right">${n_available}</button>
								</c:when>
								<c:otherwise>
								<form action="Controller" method="post">
									<input type="hidden" name="command" value="add_to_cart" /> 
									<input type="hidden" name="product_id" value="${product.id}" />
									<button type="submit" class="btn btn-default btn-xs pull-right">
							          <span class="glyphicon glyphicon-shopping-cart"></span> ${cartadd}
							        </button>
							    </form>
								</c:otherwise>
								</c:choose>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<ul class="pagination">
		<c:forEach var="i" begin="1" end="${requestScope.pageAmount}">
			<c:choose>
				<c:when test="${requestScope.requestPage == i}">
					<li class="active"><a href="Controller?command=catalog&requestPage=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?command=catalog&requestPage=${i}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>	
	</ul>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>