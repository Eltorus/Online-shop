<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="shoptag"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="generator" content="Bootply" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'> <!-- Include font first -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/shop.css">
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<title>${order}</title>
</head>
<body>
	<c:if test="${empty sessionScope.cart.productList}">
		<c:redirect url="cart" />
	</c:if>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container-fluid">
	<div class="table-responsive">
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
				<c:forEach items="${sessionScope.cart.productList}" var="cartLine" varStatus="i">
					<tr>
						<td><c:out value="${i.index+1}" /></td>
						<shoptag:outcartline value="${cartLine}" />
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<h4>${order_value}: <fmt:formatNumber pattern="#0.00" value="${sessionScope.order.bill}" /> ${rubles}
		<br />${discount}: ${sessionScope.order.discount*100} %
		<br />${total}: <fmt:formatNumber pattern="#0.00" value="${sessionScope.order.total}" /> ${rubles}
	</h4>
		<div class="row centered">
			<div class="col-sm-4 col-md-4">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="make_order" />
					<div class="form-group"> 
					<label class="control-label">${destination}</label> 
					<input type="text" id="address" pattern="^([А-Яа-я\s.])+\,([А-Яа-я\s.])+\,([\s\d])+\,([А-Яа-я\d\s.])$" class="form-control input-sm chat-input"
						title="Формат: г.Минск, ул. Сурганова, 7, к.5" name="address" required />
					</div> 
						<div class="control-group">
							<div class="controls">
								<button type="submit" id="purchase" class="btn btn-default btn-md" disabled>${purchase}</button>
							</div>
						</div>
				</form>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/address-date.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>