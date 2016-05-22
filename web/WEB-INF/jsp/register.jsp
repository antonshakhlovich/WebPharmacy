<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.local" var="loc"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/css/bootstrap.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<form role="form" action="/Controller" method="post">
    <input type="hidden" name="command" value="register"/>
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.username" bundle="${loc}"/>" name="login"/>
    <input type="password" class="form-control" placeholder="<fmt:message key="local.text.password" bundle="${loc}"/>" name="password"/>
    <input type="email" class="form-control" placeholder="<fmt:message key="local.text.email" bundle="${loc}"/>" name="email" required />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.firstname" bundle="${loc}"/>" name="first_name" required />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.lastname" bundle="${loc}"/>" name="last_name" required />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.phonenumber" bundle="${loc}"/>" name="phone_number" required />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.city" bundle="${loc}"/>" name="city" required />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.address" bundle="${loc}"/>" name="address" required />
    <input type="submit" class="btn-default" value="<fmt:message key="local.button.name.register" bundle="${loc}"/>"/>
</form>

</body>
