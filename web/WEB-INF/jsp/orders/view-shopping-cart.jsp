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
<form>
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
            <th>
                <fmt:message key="local.text.image"/>
            </th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${sessionScope.shopping_cart.items}">
        <c:set var="count" value="${count+1}"/>
        <c:set var="total_quantity" value="${total_quantity + entry.value}"/>
        <c:set var="total_amount" value="${total_amount + entry.value * entry.key.price}"/>
        <tr>
            <td>
                    ${count}
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/Controller?command=view-item&id=${entry.key.id}">
                    <b>${entry.key.label}</b> ${entry.key.dosage}, ${entry.key.volume} ${entry.key.volumeType}
                </a>
            </td>
            <td>
                    ${entry.value}
            </td>
            <td>
                    ${entry.key.price}
            </td>
            <td>
                <c:if test="${entry.key.byPrescription}">
                    <span class="glyphicon glyphicon-ok"></span>
                </c:if>
            </td>
            <td>
                <c:if test="${entry.key.imagePath != null}">
                    <img src="${entry.key.imagePath}"/>
                </c:if>
            </td>
            </c:forEach>
        </tr>
        </tbody>
        <tfoot>
        <td></td>
        <th>
            <fmt:message key="local.text.total"/>
        </th>
        <th>
           ${total_quantity}
        </th>
        <th>
            ${total_amount}
        </th>
        <th></th>
        <th></th>
        </tfoot>
    </table>
    <input type="submit" style="padding: 10px;margin-left: 30%" class="btn btn-success" value="Обновить Количество"/>
</form>

</body>
</html>