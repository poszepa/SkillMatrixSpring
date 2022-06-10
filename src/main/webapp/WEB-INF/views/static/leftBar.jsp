<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="col-sm-2 sidenav d-flex justify-content-start">
    <h2>SKILLS:</h2>
    <ul>
        <c:forEach items="${departments}" var="department">
        <li><a class="dropdown-item" href="/skillMatrix/skills/${department.nameDepartment}">${department.nameDepartment}</a></li>
        </c:forEach>
    </ul>
</div>