<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.local"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/css/bootstrap.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@include file="/WEB-INF/jsp/change-locale.jsp" %>
<form role="form" action="Controller" method="post">
    <input type="hidden" name="command" value="add-item"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
    <input type="text" name="label" placeholder="<fmt:message key="local.text.label"/>"/>
    <select>
        <c:forEach var="dosage_form" items="${dosage_forms}">
        <option value="${dosage_form.id}">${dosage_form.name}</option>
        </c:forEach>
    </select>

</form>

</body>
</html>
