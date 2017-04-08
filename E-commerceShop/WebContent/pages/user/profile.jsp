<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/shop.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>${profile}</title>
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
					<div class="img-div avatar">
						<img alt="User Pic" data-toggle="modal" data-target="#img-upload-modal" src="${sessionScope.user.imgPath}">
					</div>
					<br>
					<!-- /input-group -->
				</div>
				<div class="col-sm-6">
					<h4 style="color: #00b1b1;">${sessionScope.user.name}${sessionScope.user.surname}</h4>
				</div>
				<div class="col-sm-6">
					<button type="button" class="btn-link"data-toggle="modal" data-target="#delete-user-modal">${delprofile}</button>
				</div>
				<div class="clearfix"></div>
				<hr style="margin: 5px 0 5px 0;">
				<div class="col-sm-5 col-xs-6 tital ">${name}:</div>
				<div class="col-sm-7 col-xs-6 ">${sessionScope.user.name}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">${surname}:</div>
				<div class="col-sm-7">${sessionScope.user.surname}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">${email}:</div>
				<div class="col-sm-7">${sessionScope.user.email}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">${phonenumber}:</div>
				<div class="col-sm-7">${sessionScope.user.phonenumber}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">${discount}:</div>
				<div class="col-sm-7">${sessionScope.user.discountCoefficient}</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
				<div class="col-sm-5 col-xs-6 tital ">${balance}:</div>
				<div class="col-sm-7">${sessionScope.user.balance}</div>
				<button type="button" class="btn btn-default" onclick="$('#target').toggle();">${rebalance}</button>
				<div id="target" style="display: none">
					<!-- убрать стиль -->
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="top_balance" /> 
						<input type="number" id="inputCredit" min="0" max="1000" title="Enter number from 0 to 1000" class="form-control input-sm chat-input" name="credit" autofocus>
						<button type="submit" class="btn btn-primary btn-md">${submit}</button>
					</form>
				</div>
				<div class="clearfix"></div>
				<div class="bot-border"></div>
			</div>
		</div>
	</div>
	<div class="modal fade bs-modal-sm" id="img-upload-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
			<form class="form-signin" action="Controller" method="post" enctype="multipart/form-data">
				<div class="modal-body">
					<div id="myTabContent" class="tab-content">
						<h4>${uploadimg}</h4>
						<div class="tab-pane fade active in">
								<input type="hidden" name="command" value="upload_avatar" /> 
								<label class="btn btn-default" for="my-file-selector"> 
									<input id="my-file-selector" type="file" name="img" size="60" style="display: none;" onchange="$('#upload-file-info').html($(this).val());"> 
									${choose}
								</label> 
								<span class='label label-info' id="upload-file-info"></span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary btn-md">${upload}</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="delete-user-modal" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<h4>${rusure} ${suredelprof}?</h4>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="delete_user" /> 
						<div class="modal-footer">
							<button type="submit" class="btn btn-danger">${delete}</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>