<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="index" prefix="index.">
<html lang="${language}">
<head>
    <title>The all-new Uber</title>
</head>
    <body>
    <form>
        <select id = "language" name = "language" onchange = "submit()">
            <option value = "en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value = "ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
        </select>
    </form>
    <h2 align = "center">
            <fmt:message key="welcome"/>! <br/>
        </h2>
    <div align = "center">
        <a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="login"/></a>
        <br>
    </div>
    <div align = "center">
        <fmt:message key="signup1"/> <a href="${pageContext.request.contextPath}/register"><fmt:message key="signup2"/></a>
        <br>
    </div>
    <div align = "center">
    <a>
        <br>
        <fmt:message key="schedule"/>
    </a>
    </div>
    <table border="1" style="width:75%" align="center">
            <tr>
                <th><fmt:message key="from"/></th>
                <th><fmt:message key="to"/></th>
                <th><fmt:message key="time"/></th>
            </tr>
        <c:forEach var="i" items="${requestScope.assignmentList}">
            <tr>
                <td><c:out value="${i.route.start}"/></td>
                <td><c:out value="${i.route.finish}"/></td>
                <td><c:out value="${i.date}"/></td>
            </tr>
        </c:forEach>
         </table>
        <br>
    </body>
</html>
</fmt:bundle>