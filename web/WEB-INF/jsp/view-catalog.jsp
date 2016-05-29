<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<%@include file="/WEB-INF/jsp/header.jsp" %>
<c:set var="number_of_pages" value="${number_of_items div param.limit + 1}"/>
<div style="padding: 10px;">
    <span><fmt:message key="local.text.number.of.items"/>:</span>
    <form style="display: inline-block" role="form" action="Controller" method="get">
        <input type="hidden" name="command" value="view-catalog">
        <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 5 + 1}"/>"/>
        <input type="hidden" name="limit" value="5"/>
        <input class="btn btn-default" type="submit" value="5"/>
    </form>
    <form style="display: inline-block" role="form" action="Controller" method="get">
        <input type="hidden" name="command" value="view-catalog">
        <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 10 + 1}"/>"/>
        <input type="hidden" name="limit"  value="10"/>
        <input class="btn btn-default" type="submit" value="10"/>
    </form>
    <form style="display: inline-block" role="form" action="Controller" method="get">
        <input type="hidden" name="command" value="view-catalog">
        <input type="hidden" name="page_number" value="<fmt:parseNumber integerOnly="true" type="number"
         value="${((param.page_number-1)* param.limit) / 20 + 1}"/>"/>
        <input type="hidden" name="limit" value="20"/>
        <input class="btn btn-default" type="submit" value="20"/>
    </form>

    <span><fmt:message key="local.text.go.to.page"/>:</span>
    <form style="display: inline-block" role="form" action="Controller" method="get">
        <input type="hidden" name="command" value="view-catalog">
        <input type="number" max="${number_of_pages}" min="1" name="page_number" placeholder="${param.page_number}/<fmt:parseNumber integerOnly="true" type="number"
                                                                             value="${number_of_pages}"/>"/>
        <input type="hidden" name="limit" value="${param.limit}"/>
        <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.go"/>"/>
    </form>
    <c:if test="${param.page_number != 1}">
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-catalog">
            <input type="hidden" name="page_number" value="${param.page_number - 1}"/>
            <input type="hidden" name="limit" value="${param.limit}"/>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.previous"/>"/>
        </form>
    </c:if>
    <c:if test="${param.page_number < (number_of_pages - 1)}">
        <form style="display: inline-block" role="form" action="Controller" method="get">
            <input type="hidden" name="command" value="view-catalog">
            <input type="hidden" name="page_number" value="${param.page_number + 1}"/>
            <input type="hidden" name="limit" value="${param.limit}"/>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.next"/>"/>
        </form>
    </c:if>


</div>
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
    <c:forEach var="item" items="${items}">
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
    </c:forEach>
    </tbody>
</table>
</body>
</html>
