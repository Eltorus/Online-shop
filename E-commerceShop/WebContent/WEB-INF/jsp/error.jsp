<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>E R R O R</h1>
<c:out value="${requestScope['javax.servlet.error.exception']}" />
<br />
<a href="<c:url value="/"/>">On main</a>
</body>
</html>