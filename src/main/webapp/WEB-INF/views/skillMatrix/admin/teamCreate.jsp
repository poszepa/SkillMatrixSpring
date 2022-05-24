<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <title>Team Create</title>
  <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
  <div class="row content">
    <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
    <div class="col-sm-8 text-left">
      <form:form modelAttribute="team" method="post">
        Input name a new Team:<form:input path="nameTeam"></form:input>
        <form:errors path="nameTeam"/>
        <button type="submit">send</button>
      </form:form>
      <c:if test="${sucess} is not null">
        <span>Correctly added a new Team</span>
      </c:if>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>