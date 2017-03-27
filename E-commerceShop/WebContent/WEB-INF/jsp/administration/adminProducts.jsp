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
 <c:if test="${sessionScope.user.is_admin != true}">
 	<c:redirect url="/" />
 </c:if>
<%@ include file="/WEB-INF/elements/local.jspf"%>
<%@ include file="/WEB-INF/elements/header.jspf"%>


<!-- Main -->
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/elements/adminMenu.jspf"%>
			<!-- /col-3 -->
			<div class="col-sm-9">
				<div class="row">
					<!-- center left-->
					<!--  <div class="col-md-6">
                    tabs
                    <div class="panel">
                        <ul class="nav nav-tabs" id="myTab">
                            <li class="active"><a href="#profile" data-toggle="tab">Profile</a></li>
                            <li><a href="#messages" data-toggle="tab">Messages</a></li>
                            <li><a href="#settings" data-toggle="tab">Settings</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active well" id="profile">
                                <h4><i class="glyphicon glyphicon-user"></i></h4> Lorem profile dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate.
                                <p>Quisque mauris augue, molestie tincidunt condimentum vitae, gravida a libero. Aenean sit amet felis dolor, in sagittis nisi.</p>
                            </div>
                            <div class="tab-pane well" id="messages">
                                <h4><i class="glyphicon glyphicon-comment"></i></h4> Message ipsum dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate.
                                <p>Quisque mauris augu.</p>
                            </div>
                            <div class="tab-pane well" id="settings">
                                <h4><i class="glyphicon glyphicon-cog"></i></h4> Lorem settings dolor sit amet, consectetur adipiscing elit. Duis pharetra varius quam sit amet vulputate.
                                <p>Quisque mauris augue, molestie.</p>
                            </div>
                        </div>

                    </div>

                </div> -->
					<!--/col-->
					<div class="col-md-6">
						<div class="table-responsive">
							<table class="table table-sm table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>Title</th>
										<th>Category</th>
										<th>Price</th>
										<th>Amount</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${requestScope.products}"
										var="product" varStatus="i">
										<tr>
											<td>${product.id}</td>
											<td>${product.title}</td>
											<td>${product.category}</td>
											<td>${product.price}</td>
											<td>${product.amount}</td>
											<td><form action="Controller" method="post">
													<input type="hidden" name="command"
														value="change_product" /> <input type="hidden"
														name="product_id" value="${product.id}" />
													<button type="submit" class="btn btn-default btn-md">Change</button>
												</form></td>
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