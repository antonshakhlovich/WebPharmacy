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
