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
	<%@ include file="/WEB-INF/elements/local.jspf"%>
	<title>${signup}</title>
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
              <label class="control-label" >${name}</label>
              <div class="controls">
                <input id="name" name="name" title="Letters and numbers only, max 50 symbols" class="form-control"  pattern=".{1,50}" type="text" placeholder="${name}" class="input-large" required>
              </div>
            </div>

            <div class="control-group">
              <label class="control-label" >${surname}</label>
              <div class="controls">
                <input id="surname" name="surname" pattern=".{1,50}" title="Letters and numbers only, max 50 symbols" class="form-control" type="text" placeholder="${surname}" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="Email">${email}</label>
              <div class="controls">
                <input id="Email" name="email" max="45" class="form-control" type="email" title="Example: aa@aa.aa" pattern="^(.[^@\s]+)@(.[^@\s]+)\.([a-z]+)$" placeholder="${email}" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="userid">${phonenumber}</label>
              <div class="controls">
                <input id="phone" name="phone"  pattern=".{13,}" max="20" class="form-control" type="tel" placeholder="${phonenumber}" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="password">${password}</label>
              <div class="controls">
                <input id="password" class="form-control" name="password"  title="Must be at least 5 symbols" pattern=".{5,}" type="password" placeholder="********" class="input-large" required>
              </div>
            </div>
            
            <div class="control-group">
              <label class="control-label" for="reenterpassword">${confpassword}</label>
              <div class="controls">
                <input id="confirm_password" class="form-control" name="password_confirm" pattern=".{5,}"  title="Must be at least 5 symbols" type="password" placeholder="********" class="input-large" required>
              </div>
            </div>
            <br>
            <div class="control-group">
            	<span id="message"></span>
            </div>
            <div class="control-group">
              <label class="control-label" for="confirmsignup"></label>
              <div class="controls">
                <button id="signup_button" name="confirmsignup" type="submit" class="btn btn-success" disabled>${signup}</button>
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