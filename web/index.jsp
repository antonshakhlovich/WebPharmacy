<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <input type="text" name="login">
        <input type="password" name="password">
        <input type="submit" value="Login">
    </form>

</body>
</html>
