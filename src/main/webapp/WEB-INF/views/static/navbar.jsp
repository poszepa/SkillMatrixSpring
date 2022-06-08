<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/skillMatrix/home">Home</a></li>
                <li><a href="/skillMatrix/person">Employees</a></li>
                <li><a href="/skillMatrix/admin/dashboard">Dashboard</a></li>
                <li><a href="/skillMatrix/admin/departments">Departments</a></li>
                <li><a href="/skillMatrix/admin/skills">Skills</a></li>
                <li><a href="/skillMatrix/admin/function">Function</a></li>
                <li><a href="/skillMatrix/admin/group">Group</a></li>
                <li><a href="/skillMatrix/admin/team">Team</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span>Login</a></li>
            </ul>
        </div>
    </div>
</nav>