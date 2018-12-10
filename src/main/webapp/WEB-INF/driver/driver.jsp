<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="driver" prefix="driver.">
<html lang="${language}">
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script><title>
    <fmt:message key="welcome"/></title>
</head>
<body>
<form>
    <select id = "language" name = "language" onchange = "submit()">
        <option value = "en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value = "ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
    </select>
</form>

<h1 align="center">

    <c:out value="${sessionScope.user.firstName}"/><fmt:message key="assignments"/></h1>
<table  border="1" style="width:75%" align="center" class="table table-hover">
    <tr class="table-dark">
        <th><fmt:message key="table.start"/> </th>
        <th><fmt:message key="table.finish"/></th>
        <th><fmt:message key="table.time"/></th>
        <th><fmt:message key="table.status"/></th>
    </tr>
    <c:forEach var = "ass" items="${requestScope.assignmentsAssignedList}">
    <tr class="table-secondary">
        <td><c:out value="${ass.route.start}"/></td>
        <td><c:out value="${ass.route.finish}"/></td>
        <td><c:out value="${ass.date}"/></td>
        <td>
        <form method="post" action="<c:url value='/driver'/>">
            <c:out value="${ass.status}"/>
            <input type="number" hidden name="id" value="${ass.id}" />
            <input type="submit" name="apply" value="<fmt:message key="table.apply"/>"/>
        </form>
        </td>
    </tr>
    </c:forEach>
</table>

<h1 align="center">
    <c:out value="${sessionScope.user.firstName}"/><fmt:message key="routes"/>
</h1>
<table  border="1" style="width:75%" align="center" class="table table-hover">
    <tr class="table-dark">
        <th><fmt:message key="table.start"/> </th>
        <th><fmt:message key="table.finish"/></th>
        <th><fmt:message key="table.time"/></th>
        <th><fmt:message key="table.status"/></th>
    </tr>
    <c:forEach var = "ass" items="${requestScope.assignmentsAppliedList}">
        <tr class="table-secondary">
            <td><c:out value="${ass.route.start}"/></td>
            <td><c:out value="${ass.route.finish}"/></td>
            <td><c:out value="${ass.date}"/></td>
            <td><c:out value="${ass.status}"/></td>
        </tr>
    </c:forEach>
</table>
<div align="left">
    <a href="${pageContext.request.contextPath}/park/driver/history"><fmt:message key="link.history"/></a>
    <br>
    <a href="${pageContext.request.contextPath}/park/driver/about"><fmt:message key="link.own_page"/></a>
    <br>
    <a href="${pageContext.request.contextPath}/park/logout"><fmt:message key="link.logout"/></a>
</div>
</body>
</html>
</fmt:bundle>