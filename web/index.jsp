<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resources.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.message" var="message"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.locbutton.name.login" var="login_button"/>
    <fmt:message bundle="${loc}" key="local.message.login" var="login_message"/>
    <fmt:message bundle="${loc}" key="local.message" var="welcome_message"/>
    <title><fmt:message bundle="${loc}" key="local.title"/></title>
</head>

</head>
<body>
<h1><c:out value="${welcome_message}"/></h1>
<c:if test="${sessionScope.user != null}">
    <div>
        <span><c:out value="${login_message} : ${sessionScope.user.login}"/></span>
    </div>
</c:if>
<form action="/controller" method="post">
    <input type="hidden" name="command" value="change-locale"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}" />
    <input type="hidden" name="locale" value="ru_RU"/>
    <input type="submit" value="${ru_button}"/>
    <br/>
</form>
<form action="/controller" method="post">
    <input type="hidden" name="command" value="change-locale"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}" />
    <input type="hidden" name="locale" value="en_US"/>
    <input type="submit" value="${en_button}"/>
    <br/>
</form>
<c:if test="${sessionScope.user == null}">
<form action="/controller" method="post">
    <input type="hidden" name="command" value="login"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}" />
    <input type="text" name="login">
    <input type="password" name="password">
    <input type="submit" value="${login_button}">
</form>
</c:if>

</body>
</html>
