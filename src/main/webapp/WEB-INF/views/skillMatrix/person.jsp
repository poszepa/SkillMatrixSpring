<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Person</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <span style="font-size: 20px"><a href="/skillMatrix/person/create">Create a new Person</a></span>


            <form method="post">
                <select name="department" id="departmentSelect" onchange="showAndHideGroup()">
                    <option name="department" value="">>Choose department<</option>
                    <option name="department" value="everyPeople">Every people</option>
                    <c:forEach items="${departments}" var="department">
                        <option name="department" value="${department.nameDepartment}">${department.nameDepartment}</option>
                    </c:forEach>
                </select>
                <select name="group" id="groupSelect" onchange="showAndHideTeam()">
                    <option name="group" value="">>Choose group<</option>
                    <c:forEach items="${groups}" var="group">
                        <option name="group" value="${group.nameGroup}">${group.nameGroup}</option>
                    </c:forEach>
                </select>
                <select name="team" id="teamSelect">
                    <option name="team" value="">>Choose Team<</option>
                    <c:forEach items="${teams}" var="team">
                        <option name="team" value="${team.nameTeam}">${team.nameTeam}</option>
                    </c:forEach>
                </select>
                <button type="submit" name="seachGroup">Search</button>
            </form>

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
                    <th data-field="Description">Edit</th>
                </tr>
                </thead>
                <c:forEach items="${allPerson}" var="person">
                    <tr>
                        <th>${person.expertis}</th>
                        <th>${person.name}</th>
                        <th>${person.surname}</th>
                        <th>${person.departmentsInWarehouse.nameDepartment}</th>
                        <th>${person.functionInWarehouse.functionName}</th>
                        <th>${person.groupsInWarehouse.nameGroup}</th>
                        <th>${person.teamsInWarehouse.nameTeam}</th>
                        <th><a type="button" href="/skillMatrix/person/${person.id}">Description</a> </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>