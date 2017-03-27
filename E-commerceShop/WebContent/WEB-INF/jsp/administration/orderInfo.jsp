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
 
<div class="col-sm-10">
            <form class="form-signin" action="Controller" method="post">
            <input type="hidden" name="command" value="change_order" />
            <input type="hidden" name="order_id" value="${requestScope.order.id}" />
            <fieldset>

            <div class="control-group">
              <label class="control-label">Order ID: ${requestScope.order.id}</label>
            </div>

            <div class="control-group">
              <label class="control-label">User: ${requestScope.order.user.name} ${requestScope.order.user.surname}</label>
              <label class="control-label">Contacts: ${requestScope.order.user.email} ${requestScope.order.user.phonenumber}</label>
            </div>
            
            <div class="control-group">
              <label class="control-label">${address}</label>
              <div class="controls">
                <input required id="address" value="${requestScope.order.address}" name="address" type="text" class="form-control" class="input-medium" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label">Order date: ${requestScope.order.orderDate}</label>
              <input required id="datepicker" placeholder="Delivery Date" value="${requestScope.order.deliveryDate}" name="delivery_date" type="text" class="form-control" class="input-medium" required>
            </div>

				<div class="control-group">
					<div class="controls">
						<button class="btn btn-success">Submit</button>
					</div>
				</div>
			</fieldset>
            </form>
        </div>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/jquery.maskedinput.min.js"></script>
	<script src="js/reg_script.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>

</body>
</body>
</html>