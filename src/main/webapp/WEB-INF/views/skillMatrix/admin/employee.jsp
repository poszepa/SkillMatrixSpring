<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Employee</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <table
                    data-pagination="true"
                    data-search="true"
                    id="table"
                    data-toggle="table"
                    data-height="660">
                <thead>
                <tr>
                    <th data-sortable="true">Expertis</th>
                    <th data-sortable="true">First Name</th>
                    <th data-sortable="true">Last Name</th>
                    <th data-sortable="true">Departments</th>
                    <th data-sortable="true">Function</th>
                    <th data-sortable="true">Group</th>
                    <th data-sortable="true">Team</th>
                    <th data-field="Description">Active</th>
                </tr>
                </thead>
                <c:forEach items="${employees}" var="employee">
                    <tr>
                        <th>${employee.expertis}</th>
                        <th>${employee.name}</th>
                        <th>${employee.surname}</th>
                        <th>${employee.departmentsInWarehouse.nameDepartment}</th>
                        <th>${employee.functionInWarehouse.functionName}</th>
                        <th>${employee.groupsInWarehouse.nameGroup}</th>
                        <th>${employee.teamsInWarehouse.nameTeam}</th>
                        <th>
                            <form class="changeActive" method="post">
                                <input type="hidden" value="${employee.id}" name="idEmployee">
                                    <c:if test="${employee.active == true}">
                                        <input type="checkbox" checked value="${employee.active}" onchange="this.form.submit()">
                                    </c:if>
                                    <c:if test="${employee.active == false}">
                                        <input type="checkbox" value="${employee.active}" onchange="this.form.submit()">
                                    </c:if>
                            </form>
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>