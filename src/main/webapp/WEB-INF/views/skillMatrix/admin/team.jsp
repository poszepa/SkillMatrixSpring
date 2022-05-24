<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <title>Team</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <span style="font-size: 20px"><a href="/skillMatrix/admin/team/create">Create a new Team</a></span>
            <table
                    data-pagination="true"
                    data-search="true"
                    id="table"
                    data-toggle="table"
                    data-height="660">
                <thead>
                <tr>
                    <th data-sortable="true">Name</th>
                    <th data-sortable="true">Edit</th>
                    <th data-sortable="true">Remove</th>
                </tr>
                </thead>
                <c:forEach items="${teams}" var="team">
                    <tr>
                        <td><input type="text" name="teamName" value="${team.nameTeam}"/>
                        <td><a type="button" href="/skillMatrix/admin/team/edit/${department.id}">EDIT</a></td>
                        <td><a type="button" href="/skillMatrix/admin/team/remove/${department.id}">Remove</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>