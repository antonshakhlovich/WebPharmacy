<div class="jumbotron">
    <h1>
        <fmt:message key="local.message.company.name"/>
        <small><fmt:message key="local.message.staffonly"/></small>
    </h1>
    <div class="change-locale">
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="change-locale"/>
            <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
            <input type="hidden" name="locale" value="ru_RU"/>
            <input class="btn btn-danger" type="submit" value="<fmt:message key="local.locbutton.name.ru"/>"/>
        </form>
        <form action="Controller" method="post">
            <input type="hidden" name="command" value="change-locale"/>
            <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
            <input type="hidden" name="locale" value="en_US"/>
            <input class="btn btn-primary" type="submit" value="<fmt:message key="local.locbutton.name.en"/>">
        </form>
    </div>
    <c:if test="${sessionScope.user != null}">
        <form role="form" class="form-inline" action="Controller" method="post">
            <span><fmt:message key="local.text.login"/> : ${sessionScope.user.login}</span>
            <input type="hidden" name="command" value="logout"/>
            <input class="btn btn-default" type="submit" value="<fmt:message
                    key="local.link.logout"/>">
        </form>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <form role="form" class="form-inline" action="Controller" method="post">
            <c:if test="${sessionScope.login_failed}">
                <span style="color:red"><fmt:message key="local.message.login.error"/></span>
                <c:set var="login_failed" scope="session" value="false"/>
            </c:if>
            <input type="hidden" name="command" value="login"/>
            <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="<fmt:message key="local.text.username"/>"
                       name="login">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="<fmt:message key="local.text.password"/>"
                       name="password">
            </div>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.button.name.login"/>">
        </form>
    </c:if>
</div>
<a href="/">
    <span class="glyphicon glyphicon-home"></span>
</a>