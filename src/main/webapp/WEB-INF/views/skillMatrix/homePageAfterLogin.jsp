<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <title>HomePage</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
    <style>
        .tdWithSkillRequired{
            width: auto;
        }
        table{
            text-align: center;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <h1>Hi, below you have 10 the latest added skills </h1>
            <table
                    data-pagination="true"
                    data-search="true"
                    id="table"
                    data-toggle="table"
                    data-height="660">
                <thead>
                <tr>
                    <th data-sortable="true">Name</th>
                    <th data-sortable="true">Department</th>
                    <th data-sortable="true">Skill Required</th>
                    <th data-sortable="true">Skill Created</th>
                    <th data-sortable="true">Skill Modified</th>
                    <th data-sortable="true">Go to Skill</th>
                </tr>
                </thead>
                <c:forEach items="${skills}" var="skill">
                    <tr>
                        <input type="hidden" name="id" value="${skill.id}"/>
                        <td><input type="text" name="nameSkill" value="${skill.nameSkill}"/>
                        <td><input type="text" name="department" value="${skill.departmentsInWarehouse.nameDepartment}"></td>
                        <td class="tdWithSkillRequired"><input type="checkbox" ${skill.isRequired == true? "checked": ""} name="skillRequired" value="${skill.isRequired}"></td>
                        <td><input type="datetime-local" name="skillCreated" value="${skill.createTime}"></td>
                        <td><input type="datetime-local" name="updateTime" value="${skill.updateTime}"></td>
                        <td data-width="100"><a type="button" href="/skillMatrix/skills/${skill.departmentsInWarehouse.nameDepartment}/${skill.nameSkill}">Go to the skill</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>