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

</head>
<body>
<section id="container" class="">
    <header class="header dark-bg">
        <div class="toggle-nav">
            <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
        </div>

        <!--logo start-->
        <a href="${pageContext.request.contextPath}/park/index" class="logo">Car <span class="lite">Park</span></a>
        <!--logo end-->

        <div class="top-nav notification-row">

            <ul>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                    <span class="profile-ava">
                                        <img alt="" src="${pageContext.request.contextPath}/resources/bootstrap/img/avatar1_small.jpg">
                                    </span>
                        <span class="username"><c:out value="${sessionScope.user.firstName}"/> <c:out value="${sessionScope.user.secondName}"/></span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu extended logout">
                        <div class="log-arrow-up"></div>
                        <li class="eborder-top">
                            <a  href="${pageContext.request.contextPath}/park/driver/about"><i class="icon_profile"></i> <fmt:message key="link.own_page"/></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/park/logout"><i class="icon_key_alt"></i><fmt:message key="link.logout"/></a>
                        </li>
                    </ul>
                </li>
                <!-- user login dropdown end -->
            </ul>
            <!-- notificatoin dropdown end-->
        </div>
    </header>
    <!--header end-->

    <!--sidebar start-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu">
                <li class="">
                    <a class=""  href="${pageContext.request.contextPath}/park/index">
                        <i class="icon_house_alt"></i>
                        <span>Index page</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/park/driver/history" class="">
                        <i class="icon_desktop"></i>
                        <span><fmt:message key="link.history"/></span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>

    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-6">
                    <section class="panel">
                        <header class="panel-heading text-center">
                            <c:out value="${sessionScope.user.firstName}"/><fmt:message key="assignments"/>
                        </header>

                        <table class="table table-striped table-advance table-hover">
                            <tbody>
                            <tr>
                                <th><i class="icon_pin_alt"></i><fmt:message key="table.start"/> </th>
                                <th><i class="icon_pin_alt"></i><fmt:message key="table.finish"/></th>
                                <th><i class="icon_calendar"></i> <fmt:message key="table.time"/></th>
                            </tr>
                            <c:forEach var = "ass" items="${requestScope.assignmentsHistory}">
                                <tr class="table-secondary">
                                    <td><c:out value="${ass.route.start}"/></td>
                                    <td><c:out value="${ass.route.finish}"/></td>
                                    <td><c:out value="${ass.date}"/></td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </section>
                    <nav>
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </section>
    </section>
</section>
</body>
</html>
