<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Function Remove</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <span>Do you agree to remove those function? That can destroy db,
            maybe better choice will be modify/add new function.</span><br>
            <form:form method="post" action="/skillMatrix/admin/function/remove/remove" modelAttribute="function">
                <form:hidden path="id"></form:hidden>
                <button type="submit">Yes, remove</button>
            </form:form>

            <a href="/skillMatrix/admin/function/">No, thanks</a>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>