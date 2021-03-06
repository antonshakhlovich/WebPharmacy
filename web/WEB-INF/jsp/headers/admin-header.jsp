<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.local"/>
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
    </div>
</div>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message
                key="local.message.company.name"/> </a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="current"><fmt:message key="local.link.home"/></a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="local.link.orders"/><b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=view-orders&is_canceled=false"><fmt:message
                                key="local.link.orders.view.own"/></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=view-all-orders&is_canceled=false&limit=20&page_number=1&processing=true&shipping=true&completed=true"><fmt:message
                                key="local.link.orders.view.all"/></a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/" class="current"><fmt:message
                        key="local.link.prescriptions"/></a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="local.link.users"/><b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/register"><fmt:message
                                key="local.link.register.new.user"/> </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=view-all-users&page_number=1&limit=20"><fmt:message key="local.link.manage.users"/> </a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="local.title.catalog"/><b
                        class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=view-add-item"><fmt:message
                                key="local.button.item.add"/></a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="${pageContext.request.contextPath}/Controller?command=view-catalog&page_number=1&limit=10">
                            <fmt:message key="local.link.view.catalog"/>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
        <form class="navbar-form navbar-right" action="Controller" method="post">
            <div class="form-group">
                <input type="hidden" name="command" value="logout"/>
            </div>
            <button type="submit" class="btn btn-link"><fmt:message
                    key="local.link.logout"/></button>
        </form>
        <form class="navbar-form navbar-right" action="Controller" method="get">
            <div class="form-group">
                <input type="hidden" name="command" value="view-user"/>
                <input type="hidden" name="user_id" value="${sessionScope.user.id}"/>
            </div>
            <button type="submit" class="btn btn-link"><fmt:message
                    key="local.link.profile"/></button>
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
        <form class="navbar-form navbar-right" action="Controller" method="get">
            <input type="hidden" name="command" value="view-shopping-cart"/>
            <input class="btn btn-default" type="submit" value="<fmt:message key="local.link.shopping.cart"/>"/>
        </form>
    </div>
</nav>