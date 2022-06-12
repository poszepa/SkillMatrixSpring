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

      <table
              data-pagination="true"
              data-search="true"
              id="table"
              data-toggle="table"
              data-height="660"
              data-show-columns="true">
        <thead>
        <tr>
          <th data-field="Department" data-sortable="true">Department</th>
          <th data-field="Name skill" data-sortable="true">Name skill</th>
          <th data-field="Date created skill" data-sortable="true">Date created skill</th>
          <th data-field="Date last modified skill" data-sortable="true">Date last modified skill</th>
          <th data-field="Go to skill" data-sortable="true"></th>
        </tr>
        </thead>
        <c:forEach items="${leftSkills}" var="leftSkill" varStatus="status">
          <tr style="text-align: center">
            <td>${leftSkill.skills.departmentsInWarehouse.nameDepartment}</td>
            <td>${leftSkill.skills.nameSkill}</td>
            <td>${leftSkill.skills.createTime}</td>
            <td>${leftSkill.skills.updateTime}</td>
            <td><a type="button" href="/skillMatrix/skills/${leftSkill.skills.departmentsInWarehouse.nameDepartment}/${leftSkill.skills.nameSkill}">Go to skill</a></td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>



</script>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>