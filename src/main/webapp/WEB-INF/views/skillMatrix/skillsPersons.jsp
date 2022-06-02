<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Skills Person</title>
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
                    <th data-sortable="true">Name</th>
                    <th data-sortable="true">Surname</th>
                    <th data-sortable="true">Department</th>
                    <th data-sortable="true">Function</th>
                    <th data-sortable="true">Group</th>
                    <th data-sortable="true">Team</th>
                    <th data-sortable="true">Skilled</th>
                </tr>
                </thead>
                    <c:forEach items="${skills.personList}" var="person">
                    <tr>
                        <th>${person.expertis}</th>
                        <th>${person.name}</th>
                        <th>${person.surname}</th>
                        <th>${person.departmentsInWarehouse.nameDepartment}</th>
                        <th>${person.functionInWarehouse.functionName}</th>
                        <th>${person.groupsInWarehouse.nameGroup}</th>
                        <th>${person.teamsInWarehouse.nameTeam}</th>
                        <th>${skills.gainSkill}</th>
                    </tr>
                    </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>