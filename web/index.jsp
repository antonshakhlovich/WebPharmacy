<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/css/bootstrap.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resources.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.login" var="login_button"/>
    <fmt:message bundle="${loc}" key="local.message.login" var="login_message"/>
    <fmt:message bundle="${loc}" key="local.message.welcome" var="welcome_message"/>
    <fmt:message bundle="${loc}" key="local.message.company.name" var="company_name"/>
    <fmt:message bundle="${loc}" key="local.message.staffonly" var="staff_only"/>
    <title><fmt:message bundle="${loc}" key="local.title"/></title>
</head>

</head>
<body>
<div class="jumbotron">
    <h1>
        <c:out value="${company_name}"/>
        <small><c:out value=" ${staff_only}"/></small>
    </h1>
</div>
<%@include file="WEB-INF/jsp/change-locale.jsp" %>
<div class="login">
    <c:if test="${sessionScope.user != null}">
        <span><c:out value="${login_message} : ${sessionScope.user.login}"/></span>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <form role="form" action="Controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
            <div class="row">
                <div class="col-sm-7"></div>
                <div class="col-sm-2">
                    <input type="text" class="form-control" placeholder="Username" name="login">
                </div>
                <div class="col-sm-2">
                    <input type="password" class="form-control" placeholder="Password" name="password">
                </div>
                <div class="col-sm-1">
                    <input class="btn btn-default" type="submit" value="${login_button}">
                </div>
            </div>
        </form>
    </c:if>
</div>


</body>
</html>
