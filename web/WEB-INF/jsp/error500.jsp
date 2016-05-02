<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Page</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="resources.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.message.error.500" var="error500_message"/>

</head>
<body>
<h1><c:out value="${error500_message}"/></h1>
</body>
</html>
