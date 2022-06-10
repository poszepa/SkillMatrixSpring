<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        .mainContent{
            background-image: url('https://54d422bfce946a1b00b9-db204bc33373148745d05982db84d1c6.ssl.cf1.rackcdn.com/1553841182986skill.png');
            background-size: cover;
        }
        .container{
            display: flex;
            justify-content: center;
            align-items: center;
            height: 700px;
        }
        .loginForm{
            background-color: white;
            height: 250px;
            width: 300px;
            padding: 10px;
            margin: 10px;
            border-color: black;
            border-style: solid;
            border-radius: 10%;
        }
        .loginAndRegister{
            padding: 5px;
            display: flex;
            justify-content: space-between;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</head>
<body>
<div class="mainContent">
<div class="container">
    <div class="loginForm">
    <form:form method="post" modelAttribute="user">
        <label for="emailControll" class="form-label"></label>
        <form:input path="username" placeholder="email" class="form-control" id="emailControll" type="email"/>

        <label for="passwordControll" class="form-label"></label>
        <form:input path="password" placeholder="password" class="form-control" id="passwordControll" type="password"/>
            <div class="loginAndRegister">
                <form:button  class="btn btn-primary">Login</form:button>
    </form:form>
                <a class="btn btn-primary" href="/register" type="button">Register</a>
            </div>
    </div>
</div>
</div>

</body>
</html>