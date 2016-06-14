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
    <title><fmt:message key="local.title.users"/></title>
</head>
<body>
<ctg:header/>
<div class="container-fluid">
    <c:if test="${requestScope.error_message}">
        <div class="alert alert-danger">
            <fmt:message key="local.message.user.not.found"/>
        </div>
    </c:if>
    <c:if test="${requestScope.success_message}">
        <div class="alert alert-info">
            <fmt:message key="local.message.users.found"/>: ${requestScope.number_of_users}
        </div>
    </c:if>
    <c:set var="number_of_pages" value="${requestScope.number_of_users div param.limit + 1}"/>
    <div>
        <form class="inline" role="search" action="Controller" method="get">
            <input type="hidden" name="command" value="view-user">
            <div class="form-group">
                <input type="text" name="user_id" class="form-control" style="width: 300px;"
                       placeholder="#"/>
            </div>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.search"/>"/>
        </form>
        <form class="inline" role="search" action="Controller" method="get">
            <input type="hidden" name="command" value="view-user">
            <div class="form-group">
                <input type="text" name="email" class="form-control" style="width: 300px;"
                       placeholder="<fmt:message key="local.text.email"/> "/>
            </div>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.search"/>"/>
        </form>
        <form class="inline" role="search" action="Controller" method="get">
            <input type="hidden" name="command" value="view-user">
            <div class="form-group">
                <input type="text" name="login" class="form-control" style="width: 300px;"
                       placeholder="<fmt:message key="local.text.username"/> "/>
            </div>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.search"/>"/>
        </form>
    </div>
    <div style="padding: 10px;">
        <span><fmt:message key="local.text.number.of.users.on.page"/>:</span>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-users">
            <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 5 + 1}"/>"/>
            <input type="hidden" name="limit" value="5"/>
            <input class="btn btn-default" type="submit" value="5"/>
        </form>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-users">
            <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 10 + 1}"/>"/>
            <input type="hidden" name="limit" value="10"/>
            <input class="btn btn-default" type="submit" value="10"/>
        </form>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-users">
            <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 20 + 1}"/>"/>
            <input type="hidden" name="limit" value="20"/>
            <input class="btn btn-default" type="submit" value="20"/>
        </form>
        <span><fmt:message key="local.text.go.to.page"/>:</span>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-users">
            <input type="number" max="${number_of_pages}" min="1" name="page_number"
                   placeholder="${param.page_number}/<fmt:parseNumber integerOnly="true" type="number"
                value="${number_of_pages}"/>" required/>
            <input type="hidden" name="limit" value="${param.limit}"/>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.go"/>"/>
        </form>
        <c:choose>
            <c:when test="${param.page_number != 1}">
                <form style="display: inline-block" role="form" action="Controller" method="get">
                    <input type="hidden" name="command" value="view-all-users">
                    <input type="hidden" name="page_number" value="${param.page_number - 1}"/>
                    <input type="hidden" name="limit" value="${param.limit}"/>
                    <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.previous"/>"/>
                </form>
            </c:when>
            <c:otherwise>
                <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.previous"/>" disabled/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${param.page_number < (number_of_pages - 1)}">
                <form style="display: inline-block" role="form" action="Controller" method="get">
                    <input type="hidden" name="command" value="view-all-users">
                    <input type="hidden" name="page_number" value="${param.page_number + 1}"/>
                    <input type="hidden" name="limit" value="${param.limit}"/>
                    <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.next"/>"/>
                </form>
            </c:when>
            <c:otherwise>
                <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.next"/>" disabled/>
            </c:otherwise>
        </c:choose>
    </div>
    <table class="table table-striped table-condensed">
        <thead>
        <th class="col-sm-1">
            #
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.username"/>
        </th>
        <th class="col-sm-2">
            <fmt:message key="local.text.firstname"/> <fmt:message key="local.text.lastname"/>
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.role"/>
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.email"/>
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.phonenumber"/>
        </th>
        <th class="col-sm-3">
            <fmt:message key="local.text.address"/>
        </th>
        <th class="col-sm-2"></th>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr <c:if test="${user.banned}">class="danger"</c:if>>
                <td class="col-sm-1">
                    <c:out value="${user.id}"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${user.login}"/>
                </td>
                <td class="col-sm-2">
                    <c:out value="${user.firstName} ${user.lastName}"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${user.role}"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${user.email}"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${user.phoneNumber}"/>
                </td>
                <td class="col-sm-3">
                    <c:out value="${user.address}, ${user.city}"/>
                </td>
                <td class="col-sm-2">
                    <form class="inline" role="search" action="Controller" method="get">
                        <input type="hidden" name="command" value="view-user">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <input class="btn btn-info" type="submit" value="<fmt:message key="local.button.user.view"/>"/>
                    </form>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
