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
<div class="container-fluid">

    <c:if test="${requestScope.orders == null}">
        <div class="alert alert-info">
            <fmt:message key="local.message.orders.null"/>
        </div>
    </c:if>
    <div style="padding-bottom: 10px">
        <c:choose>
            <c:when test="${param.is_canceled eq false or param.is_canceled == null}">
                <a href="${pageContext.request.contextPath}/Controller?command=view-all-orders&is_canceled=true&limit=20&page_number=1&processing=true&shipping=true&completed=true"
                   class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span> <fmt:message
                        key="local.button.orders.show.canceled"/> </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/Controller?command=view-all-orders&is_canceled=false&limit=20&page_number=1&processing=true&shipping=true&completed=true"
                   class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> <fmt:message
                        key="local.button.orders.show.all"/> </a>
            </c:otherwise>
        </c:choose>
        <form class="inline" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-orders">
            <span><fmt:message key="local.text.filter.statuses"/>:</span>
            <label for="cart" class="btn btn-default"><fmt:message key="local.link.shopping.cart"/><input
                    name="cart" value="true" type="checkbox" id="cart" class="badgebox"
                    <c:if test="${param.cart}">checked</c:if>><span class="badge">&check;</span></label>
            <label for="processing" class="btn btn-info"><fmt:message key="local.text.order.processing"/><input
                    name="processing" value="true" type="checkbox" id="processing" class="badgebox"
                    <c:if test="${param.processing}">checked</c:if>><span class="badge">&check;</span></label>
            <label for="shipping" class="btn btn-primary"><fmt:message key="local.text.order.shipping"/><input
                    name="shipping" value="true" type="checkbox" id="shipping" class="badgebox"
                    <c:if test="${param.shipping}">checked</c:if>><span class="badge">&check;</span></label>
            <label for="completed" class="btn btn-success"><fmt:message key="local.text.order.completed"/><input
                    name="completed" value="true" type="checkbox" id="completed" class="badgebox"
                    <c:if test="${param.completed}">checked</c:if>><span class="badge">&check;</span></label>
            <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
            <input type="hidden" name="page_number" value="1"/>
            <input type="hidden" name="limit" value="${param.limit}"/>
            <input type="submit" class="btn btn-warning" style="padding: 7px 35px 7px 35px" value="<fmt:message key="local.button.filter"/> "/>
        </form>
    </div>
    <c:set var="number_of_pages" value="${requestScope.number_of_orders div param.limit + 1}"/>
    <div style="padding: 10px;">
        <span><fmt:message key="local.text.number.of.orders"/>:</span>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-orders">
            <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
            <input type="hidden" name="processing" value="${param.processing}"/>
            <input type="hidden" name="cart" value="${param.cart}"/>
            <input type="hidden" name="shipping" value="${param.shipping}"/>
            <input type="hidden" name="completed" value="${param.completed}"/>
            <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 5 + 1}"/>"/>
            <input type="hidden" name="limit" value="5"/>
            <input class="btn btn-default" type="submit" value="5"/>
        </form>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-orders">
            <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
            <input type="hidden" name="processing" value="${param.processing}"/>
            <input type="hidden" name="cart" value="${param.cart}"/>
            <input type="hidden" name="shipping" value="${param.shipping}"/>
            <input type="hidden" name="completed" value="${param.completed}"/>
            <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 10 + 1}"/>"/>
            <input type="hidden" name="limit" value="10"/>
            <input class="btn btn-default" type="submit" value="10"/>
        </form>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-orders">
            <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
            <input type="hidden" name="processing" value="${param.processing}"/>
            <input type="hidden" name="cart" value="${param.cart}"/>
            <input type="hidden" name="shipping" value="${param.shipping}"/>
            <input type="hidden" name="completed" value="${param.completed}"/>
            <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 20 + 1}"/>"/>
            <input type="hidden" name="limit" value="20"/>
            <input class="btn btn-default" type="submit" value="20"/>
        </form>
        <span><fmt:message key="local.text.go.to.page"/>:</span>
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-all-orders">
            <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
            <input type="hidden" name="processing" value="${param.processing}"/>
            <input type="hidden" name="cart" value="${param.cart}"/>
            <input type="hidden" name="shipping" value="${param.shipping}"/>
            <input type="hidden" name="completed" value="${param.completed}"/>
            <input type="number" max="${number_of_pages}" min="1" name="page_number"
                   placeholder="${param.page_number}/<fmt:parseNumber integerOnly="true" type="number"
                value="${number_of_pages}"/>" required/>
            <input type="hidden" name="limit" value="${param.limit}"/>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.go"/>"/>
        </form>
        <c:choose>
            <c:when test="${param.page_number != 1}">
                <form style="display: inline-block" role="form" action="Controller" method="get">
                    <input type="hidden" name="command" value="view-all-orders">
                    <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
                    <input type="hidden" name="processing" value="${param.processing}"/>
                    <input type="hidden" name="cart" value="${param.cart}"/>
                    <input type="hidden" name="shipping" value="${param.shipping}"/>
                    <input type="hidden" name="completed" value="${param.completed}"/>
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
                    <input type="hidden" name="command" value="view-all-orders">
                    <input type="hidden" name="is_canceled" value="${param.is_canceled}"/>
                    <input type="hidden" name="processing" value="${param.processing}"/>
                    <input type="hidden" name="cart" value="${param.cart}"/>
                    <input type="hidden" name="shipping" value="${param.shipping}"/>
                    <input type="hidden" name="completed" value="${param.completed}"/>
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
        <th class="col-sm-2">
            <fmt:message key="local.text.customer"/>
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.phonenumber"/>
        </th>
        <th class="col-sm-2">
            <fmt:message key="local.text.address"/>
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.date"/>
        </th>
        <th class="col-sm-1">
            <fmt:message key="local.text.amount"/>
        </th>
        <th class="col-sm-2">
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
                <td class="col-sm-2">
                    <c:out value="#${order.owner.id},${order.owner.firstName} ${order.owner.lastName} (${order.owner.login})"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${order.owner.phoneNumber}"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${order.owner.address}, ${order.owner.city}"/>
                </td>
                <td class="col-sm-1">
                    <fmt:formatDate dateStyle="full" value="${order.date}"/>
                </td>
                <td class="col-sm-1">
                    <c:out value="${order.amount}"/>
                </td>
                <td class="col-sm-2">
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
