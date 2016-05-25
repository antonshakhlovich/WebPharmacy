<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
    <title><fmt:message key="local.title.additem"/></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@include file="/WEB-INF/jsp/change-locale.jsp" %>
<form role="form" action="Controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="add-item"/>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
    <input type="text" name="label" placeholder="<fmt:message key="local.text.label"/>"/>
    <select name="dosage_form_id">
        <c:forEach var="dosage_form" items="${requestScope.dosage_forms}">
            <option value="${dosage_form.id}">${dosage_form.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="dosage" placeholder="<fmt:message key="local.text.dosage"/>"/>
    <input type="number" step="0.01" name="volume" placeholder="<fmt:message key="local.text.volume"/>"/>
    <select name="volume_type">
        <c:forEach var="volume_type" items="${requestScope.volume_types}">
            <option value="${volume_type}">${volume_type}</option>
        </c:forEach>
    </select>
    <input type="number" name="manufacturer_id" placeholder="<fmt:message key="local.text.manufacturer"/>"/>
    <input type="number" step="0.01" name="price" placeholder="<fmt:message key="local.text.price"/>"/>
    <input type="hidden" name="by_prescription" value="false"/>
    <input type="checkbox" name="by_prescription" value="true"><fmt:message key="local.text.byprescription"/>
    <input type="text" name="description" placeholder="<fmt:message key="local.text.description"/> "/>
    <input type="file" name="image_file" accept="image/*"/>
    <input type="submit" class="btn-primary" placeholder="<fmt:message key="local.button.item.add"/>"/>

</form>

</body>
</html>
