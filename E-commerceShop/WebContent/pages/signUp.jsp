<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/shop.css">
<title>Sign Up</title>
</head>
<body>
<%@ include file="/WEB-INF/elements/header.jspf" %>
<%@ include file="/WEB-INF/elements/local.jspf" %>
<c:if test="${not empty sessionScope.user}">
	<c:redirect url="/"/>
</c:if>

<div class="container">
  <fieldset>
    <div class="container">
    <form class="form-horizontal" action="Controller" method="post">
    <input type="hidden" name="command" value="sign_up"/>
            <fieldset>
            <!-- Sign Up Form -->
            <div class="control-group">
              <label class="control-label" >First Name:</label>
              <div class="controls">
                <input id="name" name="name" class="form-control" type="text" placeholder="Enter first name" class="input-large" required>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" >Last Name:</label>
              <div class="controls">
                <input id="surname" name="surname" class="form-control" type="text" placeholder="Enter last name" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="Email">Email:</label>
              <div class="controls">
                <input id="Email" name="email" class="form-control" type="email" pattern="^(.[^@\s]+)@(.[^@\s]+)\.([a-z]+)$" placeholder="Enter email" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="userid">Phone number:</label>
              <div class="controls">
                <input id="phone" name="phone" class="form-control" type="tel" placeholder="Enter phonenumber" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="password">Password:</label>
              <div class="controls">
                <input id="password" name="password" class="form-control" min="1" max="5" type="password" placeholder="********" class="input-large" required>
                <em>1-8 Characters</em>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="reenterpassword">Confirm Password:</label>
              <div class="controls">
                <input id="password_confirm" class="form-control" min="1" max="5" name="password_confirm" type="password" placeholder="********" class="input-large" required>
              </div>
            </div>
            <br>
            
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
                <button id="confirmsignup" name="confirmsignup" type="submit" class="btn btn-success">Sign Up</button>
              </div>
            </div>
            </fieldset>
            </form>
    </div>
  </fieldset>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.maskedinput.min.js"></script>
<script src="js/reg_script.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>