<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Person Remove</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <h1>Remove person</h1>
            <p>Want you remove person?</p>
            <form:form modelAttribute="person" method="post" action="/skillMatrix/person/remove">
                <form:hidden path="id"></form:hidden>
                <button type="submit" class="btn btn-primary">Remove</button>
                <a type="button" class="btn btn-primary" href="/skillMatrix/person">Back</a>
            </form:form>
            <hr>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>