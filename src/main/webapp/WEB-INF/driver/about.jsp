<%--
  Created by IntelliJ IDEA.
  User: Славик Винницкий
  Date: 03.12.2018
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Your page</title>
    </head>
    <body>
        <h2 align="center">
            Ваша личная информация
        </h2>
        <div>
            <p>
                Водитель
            </p>
        </div>
        <div>
        <ul>
            <li><b>Имя : </b><c:out value="${sessionScope.user.firstName}"/></li>
            <li><b>Фамилия : </b><c:out value="${sessionScope.user.secondName}"/></li>
            <li><b>Login : </b><c:out value="${sessionScope.user.login}"/></li>
            <li><b>Password : </b><c:out value="${sessionScope.user.password}"/></li>
            <li><a href="history"><b>История</b></a></li>
        </ul>
        </div>
    </body>
</html>
