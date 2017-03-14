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
<div class="container">
  <fieldset>
    <div class="container">
    <form class="form-horizontal" action='Controller' method="post">
		<input type="hidden" name="command" value="sign_up" />
    <div class="control-group">
      <!-- Name -->
      <label class="control-label"  for="username">First Name</label>
      <div class="controls">
        <input type="text" id="username" name="name" placeholder="" class="input-xlarge" required>
        <p class="help-block">Can contain any letters or numbers, without spaces</p>
      </div>
    </div>
    
     <div class="control-group">
      <!-- Surname -->
      <label class="control-label"  for="username">Surname</label>
      <div class="controls">
        <input type="text" id="username" name="surname" placeholder="Surname" class="input-xlarge" required>
        <p class="help-block">Can contain any letters or numbers, without spaces</p>
      </div>
    </div>
    
     <div class="control-group">
      <!-- Phonenumber -->
      <label class="control-label"  for="Phonenumber">Phone number</label>
      <div class="controls">
        <input type="text" id="username" name="phonenumber" placeholder="" class="input-xlarge" required>
        <p class="help-block">Phone number</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="email" id="email" name="email" placeholder="" class="input-xlarge" required>
        <p class="help-block">Please provide your E-mail</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" id="password" name="password" placeholder="" class="input-xlarge" required>
        <p class="help-block">Password should be at least 4 characters</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password - confirm -->
      <label class="control-label"  for="password_confirm">Password (Confirm)</label>
      <div class="controls">
        <input type="password" id="password_confirm" name="password_confirm" placeholder="" class="input-xlarge" required>
    
        <p class="help-block">Please confirm password</p>
      </div>
    </div>
 
    <div class="control-group">
      <div class="controls">
        <button class="btn btn-success" type="submit">Register</button>
      </div>
    </div>
    </form>
    </div>
  </fieldset>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>