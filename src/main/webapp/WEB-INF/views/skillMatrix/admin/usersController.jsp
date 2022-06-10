<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <title>User Configure</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">

<%--            Below table with role Area--%>

            <sec:authorize access="hasAnyRole('AREA')">
            <table
                    data-pagination="true"
                    data-search="true"
                    id="table"
                    data-toggle="table"
                    data-height="660">
                <thead>
                <tr>
                    <th data-sortable="true">Username</th>
                    <th data-sortable="true">Enabled</th>
                </tr>
                </thead>
                <c:forEach items="${area}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>
                            <form class="changeActive" method="post">
                                <input type="hidden" value="${user.id}" name="idUser">
                                <c:if test="${user.enabled == true}">
                                    <input type="checkbox" checked value="${user.enabled}" onchange="this.form.submit()">
                                </c:if>
                                <c:if test="${user.enabled == false}">
                                    <input type="checkbox" value="${user.enabled}" onchange="this.form.submit()">
                                </c:if>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </sec:authorize>


<%--            Below view table with role Admin--%>

            <sec:authorize access="hasAnyRole('ADMIN')">
                <table
                        data-pagination="true"
                        data-search="true"
                        id="table"
                        data-toggle="table"
                        data-height="660">
                    <thead>
                    <tr>
                        <th data-sortable="true">Username</th>
                        <th data-sortable="true">Role</th>
                        <th data-sortable="true">Enabled</th>
                    </tr>
                    </thead>
                    <c:forEach items="${admin}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <td>
                                <form method="post" action="/skillMatrix/admin/usersConfigure/role">
                                    <input type="hidden" value="${user.id}" name="idUser">
                                    <select name="role" onchange="this.form.submit()">
                                        <option selected>Actual: ${user.role}</option>
                                        <option value="ROLE_User">ROLE_User</option>
                                        <option value="ROLE_AREA">ROLE AREA</option>
                                        <option value="ROLE_ADMIN">ROLE ADMIN</option>
                                    </select>
                                </form>

                            </td>
                            <td>
                                <form class="changeActive" method="post">
                                    <input type="hidden" value="${user.id}" name="idUser">
                                    <c:if test="${user.enabled == true}">
                                        <input type="checkbox" checked value="${user.enabled}" onchange="this.form.submit()">
                                    </c:if>
                                    <c:if test="${user.enabled == false}">
                                        <input type="checkbox" value="${user.enabled}" onchange="this.form.submit()">
                                    </c:if>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </sec:authorize>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>