<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.local"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/css/bootstrap.js"></script>
    <fmt:message key="local.button.name.login" var="login_button"/>
    <fmt:message key="local.text.login" var="login_message"/>
    <fmt:message key="local.message.welcome" var="welcome_message"/>
    <fmt:message key="local.message.login.error" var="login_error"/>
    <title><fmt:message key="local.title"/></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@include file="/WEB-INF/jsp/change-locale.jsp" %>
<div class="login">
    <c:if test="${sessionScope.user != null}">
        <span><c:out value="${login_message} : ${sessionScope.user.login}"/></span>
        <a href="${pageContext.request.contextPath}/Controller?command=logout">
            <fmt:message key="local.link.logout"/>
        </a>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <form role="form" action="Controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
            <div class="row">
                <div class="col-sm-7"></div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="<fmt:message key="local.text.username"/>"
                           name="login">
                </div>
                <div class="col-sm-2">
                    <input type="password" class="form-control" placeholder="<fmt:message key="local.text.password"/>"
                           name="password">
                </div>
                <div class="col-sm-1">
                    <input class="btn btn-default" type="submit" value="${login_button}">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${sessionScope.login_failed}">
        <div style="text-align:right;padding-right: 20px"><c:out value="${login_error}"/></div>
        <c:set var="login_failed" scope="session" value="false"/>
    </c:if>
</div>
<form role="form" action="Controller" method="get">
    <input type="hidden" value="view-add-item">
    <input class="btn btn-default" type="submit" value="<fmt:message key="local.button.item.add"/>"/>
</form>


</body>
</html>
