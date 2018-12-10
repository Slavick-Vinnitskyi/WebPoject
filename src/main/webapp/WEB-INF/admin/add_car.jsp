<%--
  Created by IntelliJ IDEA.
  User: Славик Винницкий
  Date: 04.12.2018
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <title>Adding car</title>
</head>
<body>
    <h2>Here you can add new car</h2>
    <form method="post">
        <label>
            Model
            <input type="text" name="model">
        </label>
        <br>
        <label>
            Year
            <input type="number" name="year">
        </label>
        <br><br>
        <input class="button" type="submit" value="Add car">
    </form>
</body>
</html>
