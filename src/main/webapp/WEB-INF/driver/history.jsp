<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="driver_history" prefix="history.">
<html>
    <head>
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

        <!-- javascripts -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- nicescroll -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.nicescroll.js" type="text/javascript"></script>
        <!--custome script for all page-->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/scripts.js"></script>
        <title>History</title>

    </head>
    <style>
        #lang-div img {
            width: 20px;
            height: 20px;
            opacity: 0.7;
        }

        #lang-div img:hover {
            opacity: 1;
        }
    </style>
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
                        <li class="dropdown">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="username"><fmt:message key="lang.select"/></span>
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu extended ">
                                <div class="log-arrow-up"></div>
                                <li id="lang-div">
                                    <a href="?sessionLocale=en" style="font-size: 14px;">
                                        <fmt:message key="lang.en"/> <img src="${pageContext.request.contextPath}/resources/bootstrap/img/icons/us.png"/>
                                    </a>
                                    <a href="?sessionLocale=ua" style="font-size: 14px;">
                                        <fmt:message key="lang.ua"/> <img src="${pageContext.request.contextPath}/resources/bootstrap/img/icons/ua.png"/>
                                    </a>
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
                            <a href="${pageContext.request.contextPath}/park/driver" class="">
                                <i class="icon_desktop"></i>
                                <span><fmt:message key="link.main"/></span>
                            </a>
                        </li>
                    </ul>
                    <!-- sidebar menu end-->
                </div>
            </aside>

            <section id="main-content">
                <section class="wrapper">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <section class="panel">
                                <header class="panel-heading text-center">
                                    <c:out value="${sessionScope.user.firstName}"/> <fmt:message key="assignments"/>
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
                                    <c:if test="${requestScope.pagesError != null}">
                                        <c:out value="${requestScope.pagesError}"/>
                                    </c:if>
                                    <c:if test="${requestScope.pagesError == null}">
                                        <c:forEach var="i" begin="1" end="${requestScope.pages}">
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/park/driver/history?page=${i}">${i}</a></li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </section>
            </section>
        </section>
    </body>
</html>
</fmt:bundle>