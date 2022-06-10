<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Skills Edit</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <form:form class="row g-3" modelAttribute="skill" method="post" action="/skillMatrix/admin/skills/edit">
                <form:hidden path="id"/>
                <div class="col-md-2">
                    <label for="modifyName" class="form-label">Skill Name</label>
                    <form:input type="text" class="form-control" id="modifyName" path="nameSkill"/>
                    <form:errors path="nameSkill"/>
                </div>
                <div class="col-md-2">
                    <label for="modifyDepatments" class="form-label">Departments</label>
                    <form:select type="text" class="form-control" id="modifyDepatments" path="departmentsInWarehouse.id" itemValue="id" itemLabel="nameDepartment" items="${departments}"/>
                    <form:errors path="departmentsInWarehouse.id"/>
                </div>
                <div class="col-md-2">
                    <label for="modifyRequiredSkills" class="form-label">Required Skill</label>
                    <form:checkbox class="form-control" id="modifyRequiredSkills" path="isRequired"/>
                    <form:errors path="isRequired"/>
                </div>
                <div class="buttonContener d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Edit skill</button>
                </div>
            </form:form>
            <c:if test="${not empty successEditSkill}">
                Poprawnie zedytowano skill
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>
















