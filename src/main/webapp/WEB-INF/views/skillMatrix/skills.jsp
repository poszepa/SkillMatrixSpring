<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Skills</title>
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
                    <th data-sortable="true">Name Skill</th>
                    <th data-sortable="true">is Required</th>
                    <th data-sortable="true">Create Time</th>
                    <th data-sortable="true">Update Time</th>
                    <th data-sortable="true"></th>
                </tr>
                </thead>
                <c:forEach items="${skills}" var="skill">
                    <tr>
                        <th>${skill.nameSkill}</th>
                        <th>${skill.isRequired}</th>
                        <th>${skill.createTime}</th>
                        <th>${skill.updateTime}</th>
                        <th><a type="button" href="${skill.departmentsInWarehouse.nameDepartment}/${skill.nameSkill}">Description</a> </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>