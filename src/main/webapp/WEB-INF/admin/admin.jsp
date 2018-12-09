<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>

<h1 align="center">Admin page</h1>

<table  border="1" style="width:75%" align="center">
    <tr><th>Route</th><th>Date</th><th>Driver</th><th>Appointment</th></tr>
    <c:forEach var="i" items="${requestScope.routeList}">
    <tr><td>${i.start} <c:out value="${i.finish}"/></td><td>${i.startUa}</td><td>${i.finishUa}</td>
        <td>
            <form>
                <c:set var="driver" value="${'Valeriy' ? 'Valeriy' : 'Anton'}"  scope = "session"/>
                <select id = "driver" name = "driver" onchange = "submit()">
                    <option value = "Anton" ${driver == 'Anton' ? 'selected' : ''}>Anton</option>
                    <option value = "Valeriy" ${driver == 'Valeriy' ? 'selected' : ''}>Valeriy</option>
                </select>
            </form>
        <form method="post" action="<c:url value='/add'/>">
            <input type="number" hidden name="id" value="${i.id}" />
            <input class="w3-input w3-border" type="submit" name="appoint" value="Appoint"/>
        </form>
        </td>
        </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/admin/add_car">Add new car</a>
<a href="${pageContext.request.contextPath}/admin/add_route">Add new route</a>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>