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
        table {
            border-style: solid;
            text-align: center;
        }

        th, td {
            padding: 3px;
        }

        .tableAndFinderDepartment {
            display: flex;
            flex-wrap: wrap;
            margin-top: 20px;
        }

        .selectDepartment {
            text-align: center;
            font-size: 20px;
        }

    </style>

</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
        <div class="col-sm-8 text-left">
            <div class="tableAndFinderDepartment">
                <table>
                    <tr>
                        <th>Department</th>
                        <th>People Count</th>
                    </tr>
                    <c:forEach items="${personsCount}" var="personCount">
                        <tr>
                            <td>${personCount.departmentName}</td>
                            <td>${personCount.countPeople}</td>
                        </tr>
                    </c:forEach>
                </table>
                <h4>Chose description how much people was skilled from choosed department: </h4>
                <form:form method="post" modelAttribute="department" action="/skillMatrix/admin/dashboard">
                    <form:select class="selectDepartment" path="nameDepartment" onchange="this.form.submit()">
                        <form:option value="NONE">--SELECT--</form:option>
                        <form:options items="${departments}" itemValue="nameDepartment" itemLabel="nameDepartment"/>
                    </form:select>
                </form:form>
                    <canvas id="chart" style="height: 100px; width: max-content"></canvas>

            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    const labels = [
        <c:forEach items="${departments}" var="department">
        '${department.nameDepartment}',
        </c:forEach>
    ]

    const data = {
        labels: labels,
        datasets: [{
            label: 'Count of People who got every Skill from: ${departmentFromPath}',
            data: [   <c:forEach items="${personCountFromDepartment}" var="person">
                ${person},
                </c:forEach>
            ],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(110, 120, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgba(110, 120, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1
        }]
    };

    const configRequiredSkill = {
        type: 'bar',
        data: data,
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        },
    };

    const myChart = new Chart(
        document.getElementById('chart'),
        configRequiredSkill
    );

</script>

</body>

</html>