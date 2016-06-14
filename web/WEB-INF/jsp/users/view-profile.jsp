<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.local"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <title><fmt:message key="local.title.user.profile"/></title>
    <style>
        span {
            display: inline-block;
            padding: 10px;
        }
    </style>

</head>
<body>
<ctg:header/>
<div class="container">
    <c:if test="${sessionScope.success_message}">
        <div class="alert alert-info">
            <span>
                <fmt:message key="local.message.user.edit.success"/>
             </span>
            <c:set var="success_message" value="false" scope="session"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.error_message}">
        <div class="alert alert-danger">
            <fmt:message key="local.message.user.edit.error"/>
            <c:set var="error_message" value="false" scope="session"/>
        </div>
    </c:if>
    <h3><c:out value="${requestScope.user.login}"/>
        <form role="form" class="inline" action="Controller" method="get">
            <input type="hidden" name="command" value="view-orders"/>
            <input type="hidden" name="user_id" value="${requestScope.user.id}"/>
            <input type="hidden" name="is_canceled" value="false"/>
            <input type="submit" class="btn btn-default" value="<fmt:message key="local.button.orders.show.all"/> "/>
        </form>
        <c:if test="${requestScope.user.banned}">
            <span class="label label-danger"><fmt:message key="local.text.banned"/></span>
        </c:if>
    </h3>
    <hr>
    <h4>
        <span><b><fmt:message key="local.text.firstname"/></b>: <c:out value="${requestScope.user.firstName}"/></span>
        <br>
        <span><b><fmt:message key="local.text.lastname"/></b>: <c:out value="${requestScope.user.lastName}"/></span>
        <br>
        <span><b><fmt:message key="local.text.email"/></b>: <c:out value="${requestScope.user.email}"/> </span>
        <br>
        <span><b><fmt:message key="local.text.phonenumber"/></b>: <c:out
                value="${requestScope.user.phoneNumber}"/></span>
        <br>
        <span><b><fmt:message key="local.text.address"/></b>: <c:out
                value="${requestScope.user.address}, ${requestScope.user.city}"/></span>

    </h4>
    <div>
        <form role="form" class="inline" action="Controller" method="get">
            <input type="hidden" name="command" value="view-edit-user"/>
            <input type="hidden" name="user_id" value="${requestScope.user.id}"/>
            <input type="submit" class="btn btn-default" value="<fmt:message key="local.button.change"/> "/>
        </form>
        <c:if test="${sessionScope.user.role eq 'ADMIN' or sessionScope.user.role eq 'MANAGER'}">
            <c:choose>
                <c:when test="${requestScope.user.banned}">
                    <form role="form" class="inline" action="Controller" method="post">
                        <input type="hidden" name="command" value="ban-user"/>
                        <input type="hidden" name="user_id" value="${requestScope.user.id}"/>
                        <input type="hidden" name="ban_status" value="${not requestScope.user.banned}"/>
                        <input type="submit" class="btn btn-success" value="<fmt:message key="local.button.unban"/> "/>
                    </form>
                </c:when>
                <c:otherwise>
                    <form role="form" class="inline" action="Controller" method="post">
                        <input type="hidden" name="command" value="ban-user"/>
                        <input type="hidden" name="user_id" value="${requestScope.user.id}"/>
                        <input type="hidden" name="ban_status" value="${not requestScope.user.banned}"/>
                        <input type="submit" class="btn btn-danger"
                               value="<fmt:message key="local.button.ban"/> "/>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:if>
    </div>
</div>
</body>
</html>
