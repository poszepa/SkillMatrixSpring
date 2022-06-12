<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    data-height="660"
                    data-show-columns="true">
                <thead>
                <tr>
                    <th data-field="Expertis" data-sortable="true">Expertis</th>
                    <th data-field="First name" data-sortable="true">First Name</th>
                    <th data-field="Last name" data-sortable="true">Last Name</th>
                    <th data-field="Department" data-sortable="true">Departments</th>
                    <th data-field="Function" data-sortable="true">Function</th>
                    <th data-field="Group" data-sortable="true">Group</th>
                    <th data-field="Team" data-sortable="true">Team</th>
                    <c:forEach items="${departments}" var="department">
                        <th data-field="${department.nameDepartment}" data-sortable="true">${department.nameDepartment}</th>
                    </c:forEach>
                    <th data-field="Description">Edit</th>
                </tr>
                </thead>
                <c:forEach items="${allPerson}" var="person" varStatus="status">
                    <tr style="text-align: center">
                        <td>${person.expertis}</td>
                        <td>${person.name}</td>
                        <td>${person.surname}</td>
                        <td>${person.departmentsInWarehouse.nameDepartment}</td>
                        <td>${person.functionInWarehouse.functionName}</td>
                        <td>${person.groupsInWarehouse.nameGroup}</td>
                        <td>${person.teamsInWarehouse.nameTeam}</td>
                            <c:forEach items="${skillsPerson[status.index].valuePercentGainedSkill}" var="pSkill">
                                <td>
                                        <c:if test="${pSkill == 1.0}">
                                            <i class="bi bi-check-lg"></i>
                                        </c:if>
                                        <c:if test="${pSkill < 1.0}">
                                        <i class="bi bi-x-lg"></i>
                                         </c:if>
                                </td>
                            </c:forEach>
                        <th><a type="button" href="/skillMatrix/person/${person.id}">Description</a> </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<script>
    const departmentSelect = document.getElementById("departmentSelect");
    const groupSelect = document.getElementById("groupSelect");
    const teamSelect = document.getElementById("teamSelect");

    function showAndHideGroup() {
        groupSelect.style.display = "none";
        teamSelect.style.display = "none";

        if(departmentSelect.value !== "everyPeople" && departmentSelect.value !== "") {
            groupSelect.style.display = "inline-block";
        }
    }

    function showAndHideTeam() {
        teamSelect.style.display = "none";
        if(groupSelect.value !== "") {
            teamSelect.style.display = "inline-block";
        }
    }

    showAndHideGroup();


</script>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>