<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="css/shop.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<title>Profile</title>
</head>
<body>
	<%@ include file="/WEB-INF/elements/header.jspf"%>
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<c:if test="${empty sessionScope.user}">
		<c:redirect url="signin" />
	</c:if>
	<div class="panel-body">
		<div class="box box-info">
			<div class="box-body">
				<div class="col-sm-6">
<<<<<<< HEAD
					<div class="img-div avatar">
						<img type="button" alt="User Pic" data-toggle="modal" data-target="#img-upload-modal" src="${sessionScope.user.imgPath}">
=======
					<div align="center">
						<img alt="User Pic"> <input id="profile-image-upload" class="hidden" type="file">
						<div style="color: #999;">click here to change profile image</div>
						<!--Upload Image Js And Css-->
>>>>>>> 5b40ca0df939b188283d86b48f985bc60801bd39
					</div>
					<br>
					<!-- /input-group -->
				</div>
				<div class="col-sm-6">
<<<<<<< HEAD
					<h4 style="color: #00b1b1;">${sessionScope.user.name}${sessionScope.user.surname}</h4>
=======
					<h4 style="color: #00b1b1;">${sessionScope.user.name} ${sessionScope.user.surname}</h4>
>>>>>>> 5b40ca0df939b188283d86b48f985bc60801bd39
				</div>
				<div class="clearfix"></div>
				<hr style="margin: 5px 0 5px 0;">
				<div class="col-sm-5 col-xs-6 tital ">Name:</div>
				<div class="col-sm-7 col-xs-6 ">${sessionScope.user.name}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">Surname:</div>
				<div class="col-sm-7">${sessionScope.user.surname}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
<<<<<<< HEAD
				<div class="col-sm-5 col-xs-6 tital ">Email:</div>
				<div class="col-sm-7">${sessionScope.user.email}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
=======
>>>>>>> 5b40ca0df939b188283d86b48f985bc60801bd39
				<div class="col-sm-5 col-xs-6 tital ">Phone number:</div>
				<div class="col-sm-7">${sessionScope.user.phonenumber}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">Discount:</div>
				<div class="col-sm-7">${sessionScope.user.discountCoefficient}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">Balance:</div>
				<div class="col-sm-7">${sessionScope.user.balance}</div>
				<button type="button" class="btn btn-default" onclick="$('#target').toggle();">Recharge Balance</button>
				<div id="target" style="display: none">
					<!-- убрать стиль -->
					<form action="Controller" method="post">
<<<<<<< HEAD
						<input type="hidden" name="command" value="top_balance" /> 
						<input type="number" id="inputCredit" min="0" max="1000" title="Enter number from 0 to 1000" class="form-control input-sm chat-input" name="credit" autofocus>
=======
						<input type="hidden" name="command" value="top_balance" /> <input type="number" id="inputCredit" min="0" max="1000"
							title="Enter number from 0 to 1000" class="form-control input-sm chat-input" name="credit" autofocus>
>>>>>>> 5b40ca0df939b188283d86b48f985bc60801bd39
						<button type="submit" class="btn btn-primary btn-md">Submit</button>
					</form>
				</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
			</div>
		</div>
	</div>
<<<<<<< HEAD
	<div class="modal fade bs-modal-sm" id="img-upload-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
			<form class="form-signin" action="Controller" method="post" enctype="multipart/form-data">
				<div class="modal-body">
					<div id="myTabContent" class="tab-content">
						<h4>Upload img</h4>
						<div class="tab-pane fade active in">
								<input type="hidden" name="command" value="upload_avatar" /> 
								<label class="btn btn-default" for="my-file-selector"> 
									<input id="my-file-selector" type="file" name="img" size="60" style="display: none;" onchange="$('#upload-file-info').html($(this).val());"> 
									Choose picture
								</label> 
								<span class='label label-info' id="upload-file-info"></span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary btn-md">Upload</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
				</form>
			</div>
		</div>
	</div>
=======
>>>>>>> 5b40ca0df939b188283d86b48f985bc60801bd39
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>