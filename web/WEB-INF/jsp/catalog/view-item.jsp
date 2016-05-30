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
    <title>${item.label}</title>
</head>
<body>
<ctg:header/>
<table class="table">
    <thead>
    <tr>
        <th>
            <fmt:message key="local.text.label"/>
        </th>
        <th>
            <fmt:message key="local.text.dosage.type"/>
        </th>
        <th>
            <fmt:message key="local.text.dosage"/>
        </th>
        <th>
            <fmt:message key="local.text.volume"/>
        </th>
        <th>
            <fmt:message key="local.text.manufacturer"/>
        </th>
        <th>
            <fmt:message key="local.text.price"/>
        </th>
        <th>
            <fmt:message key="local.text.byprescription"/>
        </th>
        <th>
            <fmt:message key="local.text.description"/>
        </th>
        <th>
            <fmt:message key="local.text.image"/>
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            ${item.label}
        </td>
        <td>
            ${item.dosageFormName}
        </td>
        <td>
            ${item.dosage}
        </td>
        <td>
            ${item.volume} ${item.volumeType}
        </td>
        <td>
            ${item.manufacturerName}
        </td>
        <td>
            ${item.price}
        </td>
        <td>
            <c:if test="${item.byPrescription}">
                <span class="glyphicon glyphicon-ok"></span>
            </c:if>
        </td>
        <td>
            ${item.description}
        </td>
        <td>
            <c:if test="${item.imagePath != null}">
            <img src="${item.imagePath}"/>
            </c:if>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
