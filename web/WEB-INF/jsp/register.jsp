<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.local"/>
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
<%@include file="/WEB-INF/jsp/change-locale.jsp" %>
<c:set var="from" value="${pageContext.request.requestURI}" scope="request" />
<div class="col-sm-2"></div>
<div class="col-sm-8">
<c:if test="${sessionScope.error_message}">
    <div style="text-align:right;padding-right: 20px"><fmt:message key="local.message.register.error" /></div>
    <c:set var="error_message" scope="session" value="false"/>
</c:if>
<form role="form" action="/Controller" method="post">
    <input type="hidden" name="command" value="register"/>
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.username" />" name="login" required/>
    <input type="password" class="form-control" placeholder="<fmt:message key="local.text.password" />" name="password" required/>
    <input type="email" class="form-control" placeholder="<fmt:message key="local.text.email" />" name="email" required />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.firstname" />" name="first_name" />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.lastname" />" name="last_name" />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.phonenumber" />" name="phone_number" required/>
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.city" />" name="city" />
    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.address" />" name="address" />
    <input type="submit" class="btn-primary" value="<fmt:message key="local.button.name.register" />"/>
</form>
</div>
<div class="col-sm-2"></div>
</body>
