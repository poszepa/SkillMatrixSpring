<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <title>Dashboard</title>
    <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
    
    <style>
        table{
            border-style: solid;
        }
        th,td{
            padding: 3px;
        }
    </style>
    
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <table>
                <thead>Count people by department</thead>
                <tr>
                    <th>Department</th>
                    <th>count</th>
                </tr>
                <c:forEach items="${personsCount}" var="personCount">
                    <tr>
                        <td>${personCount.departmentName}</td>
                        <td>${personCount.countPeople}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>