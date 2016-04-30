<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Login Successful</title>
</head>
<body>
<jsp:useBean id="user" type="by.epam.webpharmacy.entity.User" scope="session"/>
<jsp:getProperty name="user" property="address"/>
<jsp:getProperty name="user" property="firstName"/>
<jsp:getProperty name="user" property="lastName"/>
<jsp:getProperty name="user" property="login"/>
<jsp:getProperty name="user" property="banned"/>
<jsp:getProperty name="user" property="id"/>
<jsp:getProperty name="user" property="city"/>
<jsp:getProperty name="user" property="email"/>
<h1>You are lucky!</h1>
</body>
</html>
