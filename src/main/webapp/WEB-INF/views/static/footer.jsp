<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<footer class="container-fluid text-center">
    <ul>
    <sec:authorize access="isAuthenticated()">
        <li>User: <sec:authentication property="principal.username"/></li>
        <li>Role: <sec:authentication property="authorities"/> </li>
    </sec:authorize>
    </ul>
</footer>