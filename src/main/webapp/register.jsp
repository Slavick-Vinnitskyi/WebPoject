<%--
  Created by IntelliJ IDEA.
  User: Славик Винницкий
  Date: 03.12.2018
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <title>Sing up</title>
</head>
    <body>
        <h1 align="center">Регистрация</h1><br/>
        <div align="center">
            <form method="post" action="${pageContext.request.contextPath}/register" style="width: 200px; height: 35px;">
                <label>
                    Login:
                    <input type="text" name="name">
                </label>
                <br/>
                <label>
                    Password:
                    <input type="password" name="pass">
                </label>
                <br/><br/>
                <label>
                    First name:
                    <input type="text" name="name">
                </label>
                <br/>
                <label>
                    Second name:
                    <input type="text" name="pass">
                </label>
                <br/>
                <label>
                    Їм'я українською:
                    <input type="text" name="name">
                </label>
                <br/>
                <label>
                    Прізвище українською:
                    <input type="text" name="pass">
                </label>
                <br/><br/>
                <input class="button" type="submit" value="Зарегистрироваться">
            </form>

        </div>
        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        <div align="center">
            <a href="${pageContext.request.contextPath}/logout">На головну</a>
        </div>
    </body>
</html>
