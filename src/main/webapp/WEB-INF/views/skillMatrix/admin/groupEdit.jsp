<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
  <title>Group Edit</title>
  <jsp:include page="/WEB-INF/views/static/headPreference.jsp"/>
</head>
<body>
<jsp:include page="/WEB-INF/views/static/navbar.jsp"/>

<div class="container-fluid text-center">
  <div class="row content">
    <jsp:include page="/WEB-INF/views/static/leftBar.jsp"/>
    <div class="col-sm-8 text-left">
      <form:form method="post" action="/skillMatrix/admin/group/edit" modelAttribute="group">
        <form:hidden path="id"/>
        Change name of departments:<form:input path="nameGroup"/>
        <button type="submit">CHANGE</button>
      </form:form>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>

</body>
</html>