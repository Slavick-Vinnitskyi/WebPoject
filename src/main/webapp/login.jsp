<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>
        <title>Login page</title>
    </head>
    <body>
        <div align="center">
            <h1>Вход в систему</h1><br/>
            <form method="post" action="${pageContext.request.contextPath}/park/login" style="width: 200px; height: 35px;">
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
                <input class="button" type="submit" value="Войти">
        </form>
        </div>
        <br/><br/><br/><br/><br/><br/>
        <div align="center">
        <a href="${pageContext.request.contextPath}/park/logout">На головну</a>

        </div>
    </body>
</html>