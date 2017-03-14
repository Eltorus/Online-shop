<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/shop.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Catalog</title>
</head>
<body>
<jsp:useBean id="good" class="_java._ee._02._bean.Good" type="java.lang.Object" scope="request"/>
<%@ include file="../elements/header.jspf" %>
<%@ include file="../elements/local.jspf" %>    
    <div class="container">
   
	 <div class="row">
	               
                    <c:forEach items="${requestScope.goods}" var="good">               
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                        	<form action="Controller" method="get">
                        	<input type="hidden" name="command" value="good_page"/>
                        	<input type="hidden" name="good_id" value="${good.id}"/>
                            <input type="image" src="http://placehold.it/320x150"  alt="${good.id}">
                            </form>
                            <div class="caption">
                                <h4 class="pull-right">${good.price}</h4>
                               <h4><a href="#">${good.title}</a></h4>
                                <p><a href="#">${good.category}</a></p>
                            </div>
                        </div>
                    </div>
                    </c:forEach>

                </div>
                </div>
                
    <div class="container">
        <hr>
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright © Your Website 2014</p>
                </div>
            </div>
        </footer>
    </div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>    
</body>
</html>