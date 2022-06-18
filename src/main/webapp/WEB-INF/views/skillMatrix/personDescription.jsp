<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
  <title>Person Description</title>
  <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
  <style>
    .fieldChart{
      display: flex;
      justify-content: start;
      align-items: start;
      flex-wrap: wrap;

    }
  </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
  <div class="row content">
    <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
    <div class="col-sm-8 text-left">
      <form:form class="row g-3" modelAttribute="person" method="post" action="/skillMatrix/person/edit">
        <form:hidden path="id"></form:hidden>
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
          <form:select type="text" class="form-control" id="modifyDepatments" path="departmentsInWarehouse.id" itemValue="id" itemLabel="nameDepartment" items="${departments}"/>
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
          <button type="submit" class="btn btn-primary">Modify person</button>
          <a type="button" href="/skillMatrix/person/remove/${id}" class="btn btn-primary">Remove person</a>
        </div>
        <a href="/skillMatrix/person/${id}/leftRequiredSkills">Show left required skills</a>
      </form:form>
      <div class="fieldChart">
        <div>
          <canvas id="requiredSkillChart" style="height: 250px; width: 1000px"></canvas>
        </div>
        <hr>
        <div>
          <canvas id="everySkillChart" style="height: 250px; width: 1000px"></canvas>
        </div>
      </div>

      </div>
    </div>

  </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
<%--  CONFIG to REQUIRED SKILL --%>
const labels = [
        <c:forEach items="${departments}" var="department">
          '${department.nameDepartment}',
        </c:forEach>
]

const data = {
  labels: labels,
  datasets: [{
    label: 'Skill Required',
    data: [   <c:forEach items="${percentSkillsRequired}" var="percentSkillRequired">
      ${percentSkillRequired},
      </c:forEach>
    ],
    backgroundColor: [
      <c:forEach items="${colorBackground}" var="bcolor">
      'rgba(${bcolor.firstColor}, ${bcolor.secondColor}, ${bcolor.thirdColor}, ${bcolor.opacity})',
      </c:forEach>
    ],
    borderColor: [
      <c:forEach items="${colorBackground}" var="colorB">
      'rgba(${colorB.firstColor}, ${colorB.secondColor}, ${colorB.thirdColor},)',
      </c:forEach>
    ],
    borderWidth: 1
  }]
};

const dataEverySkill = {
  labels: labels,
  datasets: [{
    label: 'Every Skill',
    data: [   <c:forEach items="${percentSkills}" var="percenSkill">
      ${percenSkill},
      </c:forEach>
    ],
    backgroundColor: [
      <c:forEach items="${colorBackground}" var="bcolor">
      'rgba(${bcolor.firstColor}, ${bcolor.secondColor}, ${bcolor.thirdColor}, ${bcolor.opacity})',
      </c:forEach>
    ],
    borderColor: [
      <c:forEach items="${colorBackground}" var="colorB">
      'rgba(${colorB.firstColor}, ${colorB.secondColor}, ${colorB.thirdColor},)',
      </c:forEach>
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
        max: 1,
        beginAtZero: true
      }
    }
  },
};

const configEverySkill = {
  type: 'bar',
  data: dataEverySkill,
  options: {
    scales: {
      y: {
        max: 1,
        beginAtZero: true
      }
    }
  },
};

const myChart = new Chart(
        document.getElementById('requiredSkillChart'),
        configRequiredSkill
);

const allSkillChart = new Chart(
        document.getElementById('everySkillChart'),
        configEverySkill
)
</script>



</body>
</html>