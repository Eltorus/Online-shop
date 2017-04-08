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
	<c:if test="${sessionScope.user.is_admin != true}">
		<c:redirect url="/" />
	</c:if>
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li role="presentation"><a href="Controller?command=admin_order_page">Orders</a></li>
			<li role="presentation" class="active"><a href="Controller?command=admin_product_page">Products</a></li>
			<li role="presentation"><a href="Controller?command=admin_user_page">Users</a></li>
		</ul>
		<button type="button" id="addProduct" class="btn btn-primary btn-md" data-toggle="modal" data-target="#productInfo">Add product</button>
		<div class="table-responsive">
			<table class="table table-sm table-hover" id="pTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Category</th>
						<th>Price</th>
						<th>Description</th>
						<th>Amount</th>
						<th>Img</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.products}" var="product" varStatus="i">
						<tr>
							<td>${product.id}</td>
							<td>${product.title}</td>
							<td>${product.category}</td>
							<td>${product.price}</td>
							<td>${product.description}</td>
							<td>${product.amount}</td>
							<td>${product.imgPath}</td>
							<td>
								<div class="dropdown">
									<button class="btn btn-danger dropdown-toggle" type="button" data-toggle="dropdown">
										Actions <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="#" id="updateProduct" data-cat="${product.categoryID}" data-toggle="modal" data-target="#productInfo">Change</a></li>
										<li><a href="#" id="deleteProduct" data-toggle="modal" data-target="#delete-product-modal">Delete</a></li>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div class="modal fade" id="productInfo" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form class="form-signin" action="Controller" method="post" enctype="multipart/form-data">
						<input type="hidden" name="command" value="update_product" /> 
						<input type="hidden" id="product_id" name="product_id" value="" />
						<fieldset>
							<div class="control-group">
								<label class="control-label">Title: </label> 
								<input required name="product_title" value="" id="title" type="text" class="form-control" class="input-medium" required>
							</div>
							<div class="control-group">
								<label class="control-label">Category :</label> <select class="form-control" id="category" name="category">
									<option value="1">Home Stuff</option>
									<option value="2">Electronics</option>
									<option value="3">Clothes</option>
									<option value="4">Shoes</option>
								</select>
							</div>
							<div class="control-group">
								<label class="control-label">Price: </label> <input required name="price" value="" id="price" type="text" class="form-control"
									class="input-medium" required>
							</div>
							<div class="control-group">
								<label class="control-label">Description: </label>
								<textarea class="form-control" rows="5" id="description" name="description" required></textarea>
							</div>
							<div class="control-group">
								<label class="control-label">Amount: </label> <input name="amount" value="" id="amount" type="number" class="form-control input-medium"
									required>
							</div>
							<div class="control-group">
								<input type="hidden" name="command" value="upload_product_img" /> <input type="hidden" id="product_img" name="product_img_path" value="" />
								<label class="btn btn-default" for="my-file-selector"> <input id="my-file-selector" type="file" name="pr-img" size="60"
									style="display: none;" accept="image/*" onchange="$('#upload-file-info').html($(this).val());"> Choose picture
								</label> <span class='label label-info' id="upload-file-info"></span>
							</div>
						</fieldset>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" id="submit-product">Submit</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="delete-product-modal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4>Are you sure you want to delete this product?</h4>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="delete_product" /> <input type="hidden" id="product-id" name="product_id" value="" />
						<div class="modal-footer">
							<button type="submit" class="btn btn-danger">Delete</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/modalProduct.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
</body>
</html>