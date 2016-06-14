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
    <title><fmt:message key="local.title.user.profile"/></title>

</head>
<body>
<ctg:header/>
<div class="container">
    <c:if test="${sessionScope.success_message}">
        <div class="alert alert-info">
            <span>
                <fmt:message key="local.message.user.edit.success"/>
             </span>
            <c:set var="success_message" value="false" scope="session"/>
        </div>
    </c:if>
    <c:if test="${sessionScope.error_message}">
        <div class="alert alert-danger">
            <fmt:message key="local.message.user.edit.error"/>
            <c:set var="error_message" value="false" scope="session"/>
        </div>
    </c:if>
    <form class="form-horizontal" style="max-width: 800px; margin:0 auto" action="Controller" method="POST">
        <input type="hidden" name="command" value="edit-user"/>
        <input type="hidden" name="user_id" value="${requestScope.user.id}"/>
        <fieldset>
            <div class="form-group">
                <!-- Username -->
                <label class="control-label col-sm-2" for="username"><fmt:message key="local.text.username"/></label>
                <div class="col-sm-10">
                    <input type="text" id="username" name="username" class="form-control" pattern="[a-zA-Z0-9]+"
                           value="${requestScope.user.login}" disabled>
                </div>
            </div>

            <div class="form-group">
                <!-- E-mail -->
                <label class="control-label col-sm-2" for="email"><fmt:message key="local.text.email"/></label>
                <div class="col-sm-10">
                    <input type="email" id="email" name="email" class="form-control"  value="${requestScope.user.email}">
                    <p class="help-block"><fmt:message key="local.text.email.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!--NEW Password-->
                <label class="control-label col-sm-2" for="new_password"><fmt:message key="local.text.password.new"/></label>
                <div class="col-sm-10">
                    <input type="password" id="new_password" name="password" class="form-control" pattern=".{4,}">
                    <p class="help-block"><fmt:message key="local.text.password.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!-- First Name-->
                <label class="control-label col-sm-2" for="first_name"><fmt:message key="local.text.firstname"/></label>
                <div class="col-sm-10">
                    <input type="text" id="first_name" name="first_name" class="form-control" value="${requestScope.user.firstName}">
                    <p class="help-block"><fmt:message key="local.text.firstname.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!-- Last Name-->
                <label class="control-label col-sm-2" for="last_name"><fmt:message key="local.text.lastname"/></label>
                <div class="col-sm-10">
                    <input type="text" id="last_name" name="last_name" class="form-control" value="${requestScope.user.lastName}">
                    <p class="help-block"><fmt:message key="local.text.lastname.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!-- Phone Number-->
                <label class="control-label col-sm-2" for="phone_number"><fmt:message
                        key="local.text.phonenumber"/></label>
                <div class="col-sm-10">
                    <input type="text" id="phone_number" name="phone_number" pattern="[\+]375[\(]\d{2}[\)]\d{7}"
                           class="form-control" value="${requestScope.user.phoneNumber}" required>
                    <p class="help-block"><fmt:message key="local.text.phonenumber.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!-- City-->
                <label class="control-label col-sm-2" for="city"><fmt:message key="local.text.city"/></label>
                <div class="col-sm-10">
                    <input type="text" id="city" name="city" value="${requestScope.user.city}" class="form-control" required>
                    <p class="help-block"><fmt:message key="local.text.city.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!-- Address-->
                <label class="control-label col-sm-2" for="address"><fmt:message key="local.text.address"/></label>
                <div class="col-sm-10">
                    <input type="text" id="address" name="address" value="${requestScope.user.address}" class="form-control" required>
                    <p class="help-block"><fmt:message key="local.text.address.help"/></p>
                </div>
            </div>

            <div class="form-group">
                <!-- Button -->
                <div class="col-sm-10 col-sm-offset-2">
                    <input type="submit" class="btn btn-primary"
                           value="<fmt:message key="local.button.change" />"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>