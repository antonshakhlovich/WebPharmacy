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
    <title><fmt:message key="local.title.additem"/></title>
</head>
<body>
<ctg:header/>
<div class="col-sm-2"></div>
<div class="col-sm-8">
    <div>
        <c:if test="${success_message}">
    <span>
        <fmt:message key="local.message.item.add.success"/> :
        <a href="/Controller?command=view_item&id=${item.id}">${item.label}</a>
    </span>
            <c:set var="success_message" value="false" scope="session"/>
        </c:if>
        <c:if test="${error_message}">
            <fmt:message key="local.message.item.add.error"/>
            <c:set var="error_message" value="false" scope="session"/>
        </c:if>
    </div>
    <form role="form" action="Controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="add-item"/>
        <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
        <div class="form-group">
            <label for="label"><fmt:message key="local.text.label"/>:</label>
            <input type="text" id="label" name="label" class="form-control"
                   placeholder="<fmt:message key="local.text.label"/>" required/>
        </div>
        <div class="form-group">
            <label for="sel1"><fmt:message key="local.text.dosage"/></label>
            <select name="dosage_form_id" class="form-control" id="sel1" required>
                <c:forEach var="dosage_form" items="${requestScope.dosage_forms}">
                    <option value="${dosage_form.id}">${dosage_form.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="dosage"><fmt:message key="local.text.dosage"/></label>
            <input type="text" id="dosage" class="form-control" name="dosage"
                   placeholder="<fmt:message key="local.text.dosage"/>" required/>
        </div>
        <div class="form-group">
            <label for="volume"><fmt:message key="local.text.volume"/></label>
            <input type="number" class="form-control" id="volume" step="0.01" name="volume"
                   placeholder="<fmt:message key="local.text.volume"/>"
                   required/>
        </div>
        <div class="form-group">
            <label for="sel2"><fmt:message key="local.text.unit"/></label>
            <select name="volume_type" class="form-control" id="sel2" required>
                <c:forEach var="volume_type" items="${requestScope.volume_types}">
                    <option value="${volume_type}">${volume_type}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="sel3"><fmt:message key="local.text.manufacturer"/></label>
            <select name="manufacturer_id" class="form-control" id="sel3">
                <c:forEach var="company" items="${requestScope.companies}">
                    <option value="${company.id}">${company.type} "${company.fullName}"</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="price"><fmt:message key="local.text.price"/></label>
            <input type="number" class="form-control" id="price" step="0.01" name="price"
                   placeholder="<fmt:message key="local.text.price"/>"
                   required/>
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="by_prescription" value="true"/><fmt:message
                    key="local.text.byprescription"/></label>
        </div>
        <div class="form-group">
            <label for="description"><fmt:message key="local.text.description"/></label>
        <textarea class="form-control" rows="5" id="description" name="description"
                  placeholder="<fmt:message key="local.text.description"/> "></textarea>
        </div>
        <div class="form-group">
            <input type="file" class="form-control" name="image_file" accept="image/*"/>
        </div>
        <input type="submit" class="btn-primary" placeholder="<fmt:message key="local.button.item.add"/>"/>
    </form>
</div>
<div class="col-sm-2"></div>

</body>
</html>
