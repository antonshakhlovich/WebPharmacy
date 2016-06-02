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
    <script type="text/javascript" src="${pageContext.request.contextPath}/css/bootstrap-filestyle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css"/>
    <title><fmt:message key="local.title.additem"/></title>
</head>
<body>
<ctg:header/>
<div class="col-sm-2"></div>
<div class="col-sm-8">

    <c:if test="${sessionScope.success_message}">
        <div class="alert alert-success">
            <span>
                <fmt:message key="local.message.item.edit.success"/>
            </span>
            <c:set var="success_message" value="false" scope="session"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.error_message}">
        <div class="alert alert-danger">
            <fmt:message key="local.message.item.edit.error"/>
            <c:set var="error_message" value="false" scope="session"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.success_message_delete}">
        <div class="alert alert-success">
            <span>
                <fmt:message key="local.message.item.deleted"/>
            </span>
            <c:set var="success_message_delete" value="false" scope="session"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.error_message_delete}">
        <div class="alert alert-danger">
            <fmt:message key="local.message.item.not.deleted"/>
            <c:set var="error_message_delete" value="false" scope="session"/>
        </div>
    </c:if>
    <form role="form" action="Controller" method="post" enctype="multipart/form-data">
        <input type="hidden" name="command" value="edit-item"/>
        <input type="hidden" name="id" value="${requestScope.item.id}"/>
        <div class="form-group">
            <label for="label"><fmt:message key="local.text.label"/>:</label>
            <input type="text" id="label" name="label" class="form-control"
                   value="${requestScope.item.label}" required/>
        </div>
        <div class="form-group">
            <label for="sel1"><fmt:message key="local.text.dosage"/></label>
            <select name="dosage_form_id" class="form-control" id="sel1" required>
                <c:forEach var="dosage_form" items="${requestScope.dosage_forms}">
                    <option value="${dosage_form.id}" ${requestScope.item.dosageFormId == dosage_form.id? 'selected' : ''}>${dosage_form.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="dosage"><fmt:message key="local.text.dosage"/></label>
            <input type="text" id="dosage" class="form-control" name="dosage"
                   value="${requestScope.item.dosage}" required/>
        </div>
        <div class=" form-group">
            <label for="volume"><fmt:message key="local.text.volume"/></label>
            <input type="number" class="form-control" id="volume" step="0.01" name="volume"
                   value="${requestScope.item.volume}" required/>
        </div>
        <div class="form-group">
            <label for="sel2"><fmt:message key="local.text.unit"/></label>
            <select name="volume_type" class="form-control" id="sel2" required>
                <c:forEach var="volume_type" items="${requestScope.volume_types}">
                    <option value="${volume_type}" ${requestScope.item.volumeType eq volume_type ? 'selected' : ''}>${volume_type}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="sel3"><fmt:message key="local.text.manufacturer"/></label>
            <select name="manufacturer_id" class="form-control" id="sel3">
                <c:forEach var="company" items="${requestScope.companies}">
                    <option value="${company.id}"  ${requestScope.item.manufacturerId == company.id ? 'selected' : ''}>${company.type}
                        "${company.fullName} "
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="price"><fmt:message key="local.text.price"/></label>
            <input type="number" class="form-control" id="price" step="0.01" name="price"
                   value="${requestScope.item.price}" required/>
        </div>
        <div class="checkbox">
            <label><input type="checkbox" name="by_prescription"
                          value="true" ${requestScope.item.byPrescription ? 'checked' : ''}/><fmt:message
                    key="local.text.byprescription"/></label>
        </div>
        <div class="form-group">
            <label for="description"><fmt:message key="local.text.description"/></label>
            <textarea class="form-control" rows="5" id="description"
                      name="description">${requestScope.item.description}</textarea>
        </div>
        <c:if test="${requestScope.item.imagePath != null}">
            <img src="${pageContext.request.contextPath}${requestScope.item.imagePath}"
                 alt="${requestScope.item.label}"/>
        </c:if>
        <input type="file" class="filestyle" data-classButton="btn btn-primary" data-input="false"
               data-classIcon="icon-plus" data-buttonText="<fmt:message key="local.button.picture.change" />"
               name="image_file" accept="image/*"/>
        <div style="padding: 10px 10px 0 0">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="local.button.change"/>"/>
        </div>
    </form>
    <form action="Controller" method="post">
        <input type="hidden" name="command" value="delete-item"/>
        <input type="hidden" name="id" value="${item.id}"/>
        <input type="submit" class="btn btn-danger" value="<fmt:message key="local.button.delete"/>"/>
    </form>
</div>
<div class="col-sm-2"></div>

</body>
</html>
