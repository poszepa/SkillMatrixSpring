<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/skillMatrix/home">Home</a></li>
                <li><a href="/skillMatrix/person">Employees</a></li>
                <sec:authorize access="hasAnyRole('ADMIN', 'AREA')">
                    <li><a href="/skillMatrix/admin/dashboard">Dashboard</a></li>
                    <li><a href="/skillMatrix/admin/skills">Skills</a></li>
                    <li><a href="/skillMatrix/admin/usersConfigure">User configure</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                <li><a href="/skillMatrix/admin/departments">Departments</a></li>
                <li><a href="/skillMatrix/admin/function">Function</a></li>
                <li><a href="/skillMatrix/admin/group">Group</a></li>
                <li><a href="/skillMatrix/admin/team">Team</a></li>
                <li><a href="/skillMatrix/admin/employee">Employee Admin</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>