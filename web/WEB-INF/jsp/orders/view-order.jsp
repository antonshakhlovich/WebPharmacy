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
    <title><fmt:message key="local.title.shopping.cart"/></title>
</head>
<body>
<ctg:header/>
<c:set var="count" value="0" scope="page"/>
<c:set var="total_quantity" value="0" scope="page"/>
<c:set var="total_amount" value="0" scope="page"/>
<div class="container-fluid">

    <div class="container">
        <h3><fmt:message key="local.text.order"/> #<c:out value="${requestScope.order.id}"/></h3>
        <h4><fmt:message key="local.text.status"/>: <c:if test="${requestScope.order.canceled}"><span
                class="label label-danger"><fmt:message key="local.text.order.canceled"/></span>
        </c:if>
            <c:choose>
                <c:when test="${requestScope.order.status eq 'в работе'}">
                    <span class="label label-info"><fmt:message key="local.text.order.processing"/></span>
                </c:when>
                <c:when test="${requestScope.order.status eq 'к доставке'}">
                    <span class="label label-primary"><fmt:message key="local.text.order.shipping"/></span>
                </c:when>
                <c:when test="${requestScope.order.status eq 'выполнен'}">
                    <span class="label label-success"><fmt:message key="local.text.order.completed"/></span>
                </c:when>
                <c:when test="${requestScope.order.status eq 'открыт'}">
                    <span class="label label-default"><fmt:message key="local.link.shopping.cart"/></span>
                </c:when>
            </c:choose></h4>
        <h4><fmt:message key="local.text.date"/>: <c:out value="${requestScope.order.date}"/></h4>
        <h4><fmt:message key="local.text.amount"/>:<b> <c:out value="${requestScope.order.amount}"/></b></h4>
        <table class="table">
            <thead>
            <tr>
                <th>
                    #
                </th>
                <th>
                    <fmt:message key="local.text.drug"/>
                </th>
                <th>
                    <fmt:message key="local.text.quantity"/>
                </th>
                <th>
                    <fmt:message key="local.text.price"/>
                </th>
                <th>
                    <fmt:message key="local.text.byprescription"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${requestScope.order.items}">
            <c:set var="count" value="${count+1}"/>
            <tr>
                <td>
                    <c:out value="${count}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/Controller?command=view-item&id=${entry.key.id}">
                        <b>${entry.key.label}</b> ${entry.key.dosage}, ${entry.key.volume} ${entry.key.volumeType}
                    </a>
                </td>
                <td>
                    <c:out value="${entry.value}"/>
                </td>
                <td>
                    <c:out value="${entry.key.price}"/>
                </td>
                <td>
                    <c:if test="${entry.key.byPrescription}">
                        <span class="glyphicon glyphicon-ok"></span>
                    </c:if>
                </td>
                </c:forEach>
            </tr>
            </tbody>
        </table>
        <c:if test="${requestScope.order.status eq 'в работе' and not requestScope.order.canceled}">
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="cancel-order"/>
                <input type="hidden" name="order_id" value="${requestScope.order.id}"/>
                <div class="col-sm-offset-4 col-sm-4">
                    <input type="submit" style="padding: 10px" class="btn btn-danger btn-lg btn-block btn-huge"
                           value="<fmt:message key="local.button.order.cancel"/> "/>
                </div>
            </form>
        </c:if>
    </div>


</div>


</body>
</html>