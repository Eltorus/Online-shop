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
<title>Product</title>
</head>
<body>
<%@ include file="/WEB-INF/elements/header.jspf" %>
<%@ include file="/WEB-INF/elements/local.jspf" %>
<c:if test="${empty requestScope.good}">
	<c:redirect url="/"/>
</c:if>

	<div class="container">
	<div class="row">
	<div class="col-sm-4 col-lg-4 col-md-4">
		<div class="thumbnail">
			<img src="http://placehold.it/320x150" alt="Product id${requestScope.good.id}">
			<div class="caption">
				<h4 class="pull-right">${requestScope.good.price}</h4>
				<h4>
					<a href="#">${requestScope.good.title}</a>
				</h4>
				<p>
					<a href="#">${requestScope.good.category}</a>
				</p>
			</div>
		</div>
	</div>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="add_to_cart" /> 
		<input type="hidden" name="good_id" value="${requestScope.good.id}" />
		<button type="submit" class = "btn btn-primary btn-md">${cartadd}</button>
	</form>
	</div>
	</div>
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

</body>
</html>