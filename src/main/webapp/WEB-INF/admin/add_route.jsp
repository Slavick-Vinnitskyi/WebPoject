<%--
  Created by IntelliJ IDEA.
  User: Славик Винницкий
  Date: 04.12.2018
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding route</title>
</head>
<body>
    <h2>Here you can add new route</h2>
    <form method="post">
        <label>
            Start
            <input type="text" name="model">
        </label>
        <label>
            Finish
            <input type="text" name="year">
        </label>
        <br>
        <label>
            Початок
            <input type="text" name="model">
        </label>
        <label>
            Кінець
            <input type="text" name="year">
        </label>
        <br><br>
        <input class="button" type="submit" value="Add route">
    </form>
</body>
</html>