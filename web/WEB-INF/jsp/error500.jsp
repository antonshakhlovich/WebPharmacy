<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resources.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.message.error.500" var="error_message"/>
    <title>Show Error Page</title>
</head>
<body>
<h1>${error_message}</h1>
<table width="100%" border="1">
    <tr valign="top">
        <td width="40%"><b>Error:</b></td>
        <td>${pageContext.exception}</td>
    </tr>
    <tr valign="top">
        <td><b>URI:</b></td>
        <td>${pageContext.errorData.requestURI}</td>
    </tr>
    <tr valign="top">
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>
    <tr valign="top">
        <td><b>Stack trace:</b></td>
        <td>
            <c:forEach var="trace"
                       items="${pageContext.exception.stackTrace}">
                <p>${trace}</p>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>