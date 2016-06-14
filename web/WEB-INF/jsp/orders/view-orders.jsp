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
    <title><fmt:message key="local.title.orders"/></title>
</head>
<body>
<ctg:header/>
<div class="container">
    <c:if test="${sessionScope.access_denied}">
        <div class="alert alert-danger">
            <fmt:message key="local.message.access.denied"/>
            <c:set var="access_denied" value="false" scope="session"/>
        </div>
    </c:if>
    <c:if test="${requestScope.orders == null}">
        <div class="alert alert-info">
            <fmt:message key="local.message.orders.null"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.error_message}">
        <div class="alert alert-info">
            <fmt:message key="local.message.order.not.found"/>
            <c:set var="error_message" scope="session" value="false"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.success_message}">
        <div class="alert alert-success">
            <fmt:message key="local.message.order.submitted"/>
            <c:set var="success_message" scope="session" value="false"/>
        </div>
    </c:if>
    <div style="padding-bottom: 10px">
        <c:choose>
            <c:when test="${param.is_canceled eq false}">
                <form role="form" class="inline" action="Controller" method="get">
                    <input type="hidden" name="command" value="view-orders"/>
                    <input type="hidden" name="user_id" value="${param.user_id}"/>
                    <input type="hidden" name="is_canceled" value="true"/>
                    <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>
                        <fmt:message key="local.button.orders.show.canceled"/></button>
                </form>
            </c:when>
            <c:otherwise>
                <form role="form" class="inline" action="Controller" method="get">
                    <input type="hidden" name="command" value="view-orders"/>
                    <input type="hidden" name="user_id" value="${param.user_id}"/>
                    <input type="hidden" name="is_canceled" value="false"/>
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>
                        <fmt:message key="local.button.orders.show.all"/></button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
        <th class="col-sm-1">
            #
        </th>
        <th class="col-sm-3">
            <fmt:message key="local.text.date"/>
        </th>
        <th class="col-sm-3">
            <fmt:message key="local.text.amount"/>
        </th>
        <th class="col-sm-3">
            <fmt:message key="local.text.status"/>
        </th>
        <th class="col-sm-2"></th>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr <c:if test="${order.canceled}">class="danger"</c:if>>
                <td class="col-sm-1">
                    <c:out value="${order.id}"/>
                </td>
                <td class="col-sm-3">
                    <fmt:formatDate type="both" value="${order.date}"/>
                </td>
                <td class="col-sm-3">
                    <c:out value="${order.amount}"/>
                </td>
                <td class="col-sm-3">
                    <c:if test="${order.canceled}">
                        <span class="label label-danger"><fmt:message key="local.text.order.canceled"/></span>
                    </c:if>
                    <c:choose>
                        <c:when test="${order.status eq 'в работе'}">
                            <span class="label label-info"><fmt:message key="local.text.order.processing"/></span>
                        </c:when>
                        <c:when test="${order.status eq 'к доставке'}">
                            <span class="label label-primary"><fmt:message key="local.text.order.shipping"/></span>
                        </c:when>
                        <c:when test="${order.status eq 'выполнен'}">
                            <span class="label label-success"><fmt:message key="local.text.order.completed"/></span>
                        </c:when>
                        <c:when test="${order.status eq 'открыт'}">
                            <span class="label label-default"><fmt:message key="local.link.shopping.cart"/></span>
                        </c:when>
                    </c:choose>
                </td>
                <td class="col-sm-2">
                    <a href="${pageContext.request.contextPath}/Controller?command=view-order&id=${order.id}"
                       class="btn btn-info" role="button"><fmt:message key="local.button.order.view"/> </a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
