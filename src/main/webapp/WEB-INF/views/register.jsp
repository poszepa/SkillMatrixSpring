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
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 700px;
            background-image: url('https://54d422bfce946a1b00b9-db204bc33373148745d05982db84d1c6.ssl.cf1.rackcdn.com/1553841182986skill.png');
            background-size: cover;
        }

        .loginForm {
            background-color: white;
            height: 275px;
            width: 300px;
            padding: 10px;
            margin: 10px;
            border-color: black;
            border-style: solid;
            border-radius: 10%;
        }

        .loginAndRegister {
            padding: 5px;
            display: flex;
            justify-content: space-between;
        }

        .alertRegister{
            background-color: red;
            display: none;
        }

    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>

</head>

<body>
<div class="body-container">
    <div class="container">
        <div class="loginForm">
            <span>Register a new Account</span><br>
            <form:form method="post" modelAttribute="user" action="/register">

            <label for="emailControl" class="form-label"></label>
                <form:input path="username" placeholder="email" class="form-control" id="emailControl" type="email"/>

            <label for="passwordControll" class="form-label"></label>
                <form:input path="password" placeholder="password" class="form-control" id="passwordControll" type="password"/>

            <label for="passwordControllRe" class="form-label"></label>
                <input type="password" placeholder="rePassword" class="form-control" name="passwordControllRe" id="passwordControllRe">

            <div class="loginAndRegister">
                <button class="btn btn-primary" id="btnRegister" type="submit">Register Account</button>
                </form:form>
            </div>


        <div class="alertRegister"></div>
<%--        close loginForm--%>
        </div>
<%--    close container--%>
    </div>
<%--close body-container--%>
</div>

<script>
    const email = document.querySelector("#emailControll").value;
    const password = document.querySelector("#passwordControll").value;
    const rePassword = document.querySelector('#passwordControllRe').value;
    const buttonRegister = document.querySelector('#btnRegister');
    const alertRegister = document.querySelector('.alertRegister');

    function validPassword() {
        if(password != rePassword) {
            return false;
        }
        return true;
    }

    function validEmail() {
        if(!email.includes('.external@zalando.de')) {
            return false;
        }
        return true;
    }

    function valid() {
        if(validPassword == false || validEmail == false) {
            return false;
        }
        return true;
    }

    buttonRegister.addEventListener("click", (event) => {
        if(valid == false) {
            event.preventDefault();
            alertRegister.style.display = 'block';
            alertRegister.innerText = 'Wrong email or passsword not matches.'
        }
    })




</script>

</body>

</html>