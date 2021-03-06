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
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<title>${authorization}</title>
</head>
<body>
	<c:if test="${not empty sessionScope.user}">
		<c:redirect url="/" />
	</c:if>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-md-offset-5 col-md-3">
				<div class="form-login">
					<form class="form-signin" action="Controller" method="post">
						<input type="hidden" name="command" value="sign_in" />
						<h2 class="form-signin-heading">${message}</h2>
						<input type="email" id="inputEmail" class="form-control input-sm chat-input" placeholder="${email}" name="email" required autofocus> <br />
						<input type="password" id="inputPassword" class="form-control input-sm chat-input" placeholder="${password}" name="password" required> <br />
						<div class="wrapper">
							<span class="group-btn"> 
							<input type="submit" class="btn btn-primary btn-md" value="${signin}">
							</span>
						</div>
					</form>
					<a href="signup">${signup}</a>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>