<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/shop.css">
<title>Catalog</title>
</head>
<body>
	<jsp:useBean id="product" class="by.epam.shop.bean.Product" type="java.lang.Object" scope="request" />
	<%@ include file="../elements/header.jspf"%>
	<%@ include file="../elements/local.jspf"%>
	<div class="container">
		<div class="row">
			<c:forEach items="${requestScope.products}" var="product">
				<div class="col-sm-3 col-lg-3 col-md-5">
					<div class="thumbnail">
					<div class="product-pict">
							<a href="Controller?command=product_page&product_id=${product.id}"> <img src="${product.imgPath}" class="product" alt="${product.imgPath}"></a>
					</div>
						<div class="caption">
							<h4 class="pull-right">${product.price}</h4>
							<h4>
								<a href="Controller?command=product_page&product_id=${product.id}">${product.title}</a>
							</h4>
							<p>
								<a href="#">${product.category}</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<ul class="pagination">
		<c:forEach var="i" begin="1" end="${requestScope.pageAmount}">
			<c:choose>
				<c:when test="${requestScope.requestPage == i}">
					<li class="active"><a href="#">i</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="#">i</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
	</ul>
	<div class="container">
		<hr>
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright © Your Website 2014</p>
				</div>
			</div>
		</footer>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>