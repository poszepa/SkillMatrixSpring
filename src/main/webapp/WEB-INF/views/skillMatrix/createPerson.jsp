<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html lang="en">
<head>
    <title>Create a Person</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <form:form class="row g-3" modelAttribute="person" method="post" action="/skillMatrix/person/create">
                <div class="col-md-2">
                    <label for="modifyExpertis" class="form-label">Expertis</label>
                    <form:input type="text" class="form-control" id="modifyExpertis" path="expertis"/>
                    <form:errors path="expertis" element="span"/>
                </div>
                <div class="col-md-2">
                    <label for="modifyName" class="form-label">First Name</label>
                    <form:input type="text" class="form-control" id="modifyName" path="name"/>
                    <form:errors path="name" element="span"/>
                </div>
                <div class="col-md-2">
                    <label for="modifySurname" class="form-label">Nazwisko</label>
                    <form:input type="text" class="form-control" id="modifySurname" path="surname"/>
                    <form:errors path="surname" element="span"/>
                </div>
                <div class="col-md-2">
                    <label for="modifyDepatments" class="form-label">Departments</label>
                    <form:select type="text" class="form-control" id="modifyDepatments" path="departmentsInWarehouse.id" itemValue="id" itemLabel="nameDepartment" items="${departmentsWithoutGeneral}"/>
                    <form:errors path="departmentsInWarehouse.id" element="span"/>
                </div>
                <div class="col-md-2">
                    <label for="modifyFunction" class="form-label">Function</label>
                    <form:select type="text" class="form-control" id="modifyFunction" path="functionInWarehouse.id" itemValue="id" itemLabel="functionName" items="${functions}"/>
                    <form:errors path="functionInWarehouse.id" element="span"/>
                </div>
                <div class="col-md-1">
                    <label for="modifyGroups" class="form-label">Groups</label>
                    <form:select type="text" class="form-control" id="modifyGroups" path="groupsInWarehouse.id" itemLabel="nameGroup" itemValue="id" items="${groups}"/>
                    <form:errors path="groupsInWarehouse.id" element="span"/>
                </div>
                <div class="col-md-1">
                    <label for="ModifyTeam" class="form-label">Team</label>
                    <form:select type="text" class="form-control" id="ModifyTeam" path="teamsInWarehouse.id" itemValue="id" itemLabel="nameTeam" items="${teams}"/>
                    <form:errors path="teamsInWarehouse.id" element="span"/>
                </div>
                <div class="buttonContener d-flex justify-content-start">
                    <button type="submit" class="btn btn-primary">Create a new person</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>
















