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
            <form method="post">
                <select name="department">
                    <option name="department" value="">>Choose department<</option>
                    <option name="department" value="everyPeople">Every people</option>
                    <c:forEach items="${departments}" var="department">
                        <option name="department" value="${department.nameDepartment}">${department.nameDepartment}</option>
                    </c:forEach>
                </select>
                <select name="group">
                    <option name="group" value="">>Choose group<</option>
                    <c:forEach items="${groups}" var="group">
                        <option name="group" value="${group.nameGroup}">${group.nameGroup}</option>
                    </c:forEach>
                </select>
                <select name="team">
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
                    <th data-sortable="true">Name</th>
                    <th data-sortable="true">Surname</th>
                    <th data-sortable="true">Department</th>
                    <th data-sortable="true">Function</th>
                    <th data-sortable="true">Group</th>
                    <th data-sortable="true">Team</th>
                    <th data-sortable="true">Skilled</th>
                </tr>
                </thead>
                    <c:forEach items="${ownedskills}" var="ownedskill">
                    <tr>
                        <th>${ownedskill.person.expertis}</th>
                        <th>${ownedskill.person.name}</th>
                        <th>${ownedskill.person.surname}</th>
                        <th>${ownedskill.person.departmentsInWarehouse.nameDepartment}</th>
                        <th>${ownedskill.person.functionInWarehouse.functionName}</th>
                        <th>${ownedskill.person.groupsInWarehouse.nameGroup}</th>
                        <th>${ownedskill.person.teamsInWarehouse.nameTeam}</th>
                        <th>
                            <form method="post">
                                <input type="hidden" value="${ownedskill.id}" name="id"/>
                                <c:if test="${ownedskill.gainSkill != true}">
                                    <input type="checkbox" value="${ownedskill.gainSkill}" name="gainSkill" class="Skilled" onchange="this.form.submit()"/>
                                </c:if>

                                <c:if test="${ownedskill.gainSkill == true}">
                                    <input type="checkbox" value="${ownedskill.gainSkill}" name="gainSkill" class="Skilled" checked="checked" onchange="this.form.submit()"/>
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