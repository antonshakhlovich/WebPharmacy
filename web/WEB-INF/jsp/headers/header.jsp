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
    <div class="messages">
        <c:if test="${sessionScope.user != null}">
            <span><fmt:message key="local.text.login"/> : ${sessionScope.user.login}</span>
        </c:if>
        <c:if test="${sessionScope.login_failed}">
            <span style="color:red"><fmt:message key="local.message.login.error"/></span>
            <c:set var="login_failed" scope="session" value="false"/>
        </c:if>
    </div>
</div>
<a>
    <span class="glyphicon glyphicon-home"></span>
</a>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="local.message.company.name"/> </a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/" class="current"><fmt:message key="local.link.home"/></a>
            </li>
            <li class="active dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="local.link.users"/><b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#">New Employee</a>
                    </li>
                    <li class="divider"></li>
                    <li class="nav-header">Reports</li>
                    <li>
                        <a href="#" target="_blank">Employees List</a>
                    </li>
                </ul>
            </li>
        </ul>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <form class="navbar-form navbar-right" action="Controller" method="post">
                    <div class="form-group">
                        <input type="hidden" name="command" value="logout"/>
                    </div>
                    <button type="submit" class="btn btn-link"><fmt:message
                            key="local.link.logout"/></button>
                </form>
                <form class="navbar-form navbar-right" role="search" action="Controller" method="get">
                    <input type="hidden" name="command" value="search-item">
                    <input type="hidden" name="page_number" value="1"/>
                    <input type="hidden" name="limit" value="20"/>
                    <div class="form-group">
                        <input type="text" name="search" class="form-control" style="width: 300px;"
                               placeholder="<fmt:message key="local.text.enter.drug.name"/> "/>
                    </div>
                    <input class="btn btn-default" type="submit" value="<fmt:message key="local.text.search"/>"/>
                </form>
            </c:when>
            <c:otherwise>
                <form class="navbar-form navbar-right" action="Controller" method="post" role="search">
                    <div class="form-group">
                        <input type="hidden" name="command" value="login"/>
                        <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
                        <input type="text" class="form-control" placeholder="<fmt:message key="local.text.username"/>"
                               name="login">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control"
                               placeholder="<fmt:message key="local.text.password"/>"
                               name="password">
                    </div>
                    <input class="btn btn-default" type="submit"
                           value="<fmt:message key="local.button.name.login"/>">
                </form>
                <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/register">
                    <button type="submit" class="btn btn-link"><fmt:message
                            key="local.link.register"/></button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</nav>