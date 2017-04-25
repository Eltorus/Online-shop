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
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<div class="container-fluid">
		<ul class="nav nav-tabs">
			<li role="presentation"><a href="Controller?command=admin_order_page">${orders_loc}</a></li>
			<li role="presentation" class="active"><a href="Controller?command=admin_product_page">${products_loc}</a></li>
			<li role="presentation"><a href="Controller?command=admin_user_page">${users_loc}</a></li>
		</ul>
		<button type="button" id="addProduct" class="btn btn-primary btn-md" data-toggle="modal" data-target="#productInfo">Add product</button>
		<div class="table-responsive">
			<table class="table table-sm table-hover" id="pTable">
				<thead>
					<tr>
						<th>ID</th>
						<th>${title}</th>
						<th>${category}</th>
						<th>${price}, ${rubles}</th>
						<th>${description}</th>
						<th>${amount}</th>
						<th>${image}</th>
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
										${actions} <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="#" id="updateProduct" data-cat="${product.categoryID}" data-toggle="modal" data-target="#productInfo">${change}</a></li>
										<li><a href="#" id="deleteProduct" data-toggle="modal" data-target="#delete-product-modal">${delete}</a></li>
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
								<label class="control-label">${title}: </label> 
								<input required name="product_title" value="" id="title" type="text" class="form-control" class="input-medium" required>
							</div>
							<div class="control-group">
								<label class="control-label">${category}:</label> 
								<select class="form-control" id="category" name="category">
									<option value="1">Home Stuff</option>
									<option value="2">Electronics</option>
									<option value="3">Clothes</option>
									<option value="4">Shoes</option>
								</select>
							</div>
							<div class="control-group">
								<label class="control-label">${price}: </label> <input required name="price" value="" id="price" type="text" class="form-control"
									class="input-medium" required>
							</div>
							<div class="control-group">
								<label class="control-label">${description}: </label>
								<textarea class="form-control" rows="5" id="description" name="description" required></textarea>
							</div>
							<div class="control-group">
								<label class="control-label">${amount}: </label> <input name="amount" value="" id="amount" type="number" class="form-control input-medium"
									required>
							</div>
							<div class="control-group">
								<input type="hidden" name="command" value="upload_product_img" /> <input type="hidden" id="product_img" name="product_img_path" value="" />
								<label class="btn btn-default" for="my-file-selector"> 
									<input id="my-file-selector" type="file" name="pr-img" size="60"
										   style="display: none;" accept="image/*" 
										   onchange="$('#upload-file-info').html($(this).val());"> 
									${upload}
								</label> <span class='label label-info' id="upload-file-info"></span>
							</div>
						</fieldset>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" id="submit-product">${submit}</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">${close}</button>
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
					<h4>${r_u_sure} ${del_product}?</h4>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="delete_product" /> <input type="hidden" id="product-id" name="product_id" value="" />
						<div class="modal-footer">
							<button type="submit" class="btn btn-danger">${delete}</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">${close}</button>
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