<%--
  Created by IntelliJ IDEA.
  User: Славик Винницкий
  Date: 03.12.2018
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <title>History</title>
    <div align="center">
        <a>
            <br>
            История поездок
        </a>
    </div>
    <table  border="1" style="width:75%" align="center" class="table table-hover">
        <tr class="table-dark">
            <th>Отправка</th>
            <th>Прибытие</th>
            <th>Время отправки</th>
        </tr>
        <c:forEach var="i" items="${requestScope.assignmentsHistory}">
            <tr class="table-secondary">
                <td><c:out value="${i.route.start}"/></td>
                <td><c:out value="${i.route.finish}"/></td>
                <td><c:out value="${i.date}"/></td>
            </tr>
        </c:forEach>
    </table>
</head>
<body>

</body>
</html>
