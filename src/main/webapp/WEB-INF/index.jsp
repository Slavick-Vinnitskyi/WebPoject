<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language
 : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" scope = "session"/>
<fmt:bundle basename="index" prefix="index.">
<html lang="${language}">
<head>
    <%--<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">--%>
    <%--<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>--%>
        <!-- Bootstrap CSS -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- bootstrap theme -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
        <!--external css-->
        <!-- font icon -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/elegant-icons-style.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.min.css" rel="stylesheet" />
        <!-- Custom styles -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style-responsive.css" rel="stylesheet" />


        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/html5shiv.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/respond.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/lte-ie7.js" type="text/javascript"></script>

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
        <fmt:message key="signup1"/> <a href="${pageContext.request.contextPath}/park/register"><fmt:message key="signup2"/></a>
        <br>
    </div>
    <div align = "center">
    <a>
        <br>
        <fmt:message key="schedule"/>
    </a>
    </div>
    <table border="1" style="width:75%" align="center" class="table table-hover">
            <tr class="table-dark">
                <th><fmt:message key="from"/></th>
                <th><fmt:message key="to"/></th>
                <th><fmt:message key="time"/></th>
            </tr>
        <c:forEach var="i" items="${requestScope.assignmentList}">
            <tr class="table-secondary">
                <td><c:out value="${i.route.start}"/></td>
                <td><c:out value="${i.route.finish}"/></td>
                <td><c:out value="${i.date}"/></td>
            </tr>
        </c:forEach>
         </table>

<%--    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Type</th>
            <th scope="col">Column heading</th>
            <th scope="col">Column heading</th>
            <th scope="col">Column heading</th>
        </tr>
        </thead>
        <tbody>
        <tr class="table-active">
            <th scope="row">Active</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr>
            <th scope="row">Default</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-primary">
            <th scope="row">Primary</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-secondary">
            <th scope="row">Secondary</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-success">
            <th scope="row">Success</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-danger">
            <th scope="row">Danger</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-warning">
            <th scope="row">Warning</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-info">
            <th scope="row">Info</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-light">
            <th scope="row">Light</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        <tr class="table-dark">
            <th scope="row">Dark</th>
            <td>Column content</td>
            <td>Column content</td>
            <td>Column content</td>
        </tr>
        </tbody>
    </table>--%>
    <br>
    </body>
</html>
</fmt:bundle>