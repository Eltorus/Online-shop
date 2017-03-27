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


<!-- Main -->
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/elements/adminMenu.jspf"%>
        <!-- /col-3 -->
        <div class="col-sm-9">
            <div class="row">
                <!-- center left-->
                <div class="col-md-6">
                    <!--tabs-->
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

                </div>
                <!--/col-->
                <div class="col-md-6">
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Visits</th>
                                    <th>ROI</th>
                                    <th>Source</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>45</td>
                                    <td>2.45%</td>
                                    <td>Direct</td>
                                </tr>
                                <tr>
                                    <td>289</td>
                                    <td>56.2%</td>
                                    <td>Referral</td>
                                </tr>
                                <tr>
                                    <td>98</td>
                                    <td>25%</td>
                                    <td>Type</td>
                                </tr>
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