<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="generator" content="Bootply"/>
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

<!-- Main -->
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/elements/adminMenu.jspf"%>
			<!-- /col-3 -->
			<div class="col-sm-20">
				<div class="row">
					<div class="col-md-6">
						<div class="table-responsive">
							<table class="table table-sm table-hover" id="pTable">
								<thead>
									<tr>
										<th>ID</th>
										<th>Name, Surname</th>
										<th>Email</th>
										<th>Phone number</th>
										<th>Status</th>
										<th>Ban status</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.userList}" var="user" varStatus="i">
										<tr>
											<td>${user.id}</td>
											<td>${user.name} ${user.surname}</td>
											<td>${user.email}</td>
											<td>${user.phonenumber}</td>
											<td><c:choose>
													<c:when test="${user.is_admin == true}">
														Administrator
													</c:when>
													<c:otherwise>
														User
													</c:otherwise>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${user.is_banned == true}">
														Banned
													</c:when>
													<c:otherwise>
														Not banned
													</c:otherwise>
												</c:choose></td>
											<td>
											<form action="Controller" method="post">
												<input type="hidden" name="command" value="user_update" /> 
												<input type="hidden" name="user_id" value="${user.id}" />
												<input type="hidden" name="user_banned" value="${user.is_banned}" /> 
												<button type="submit" id="updateUser" class="btn btn-default btn-md">Change ban status</button>
											</form>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>

</body>
</body>
</html>