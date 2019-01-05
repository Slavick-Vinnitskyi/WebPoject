<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language
 : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="route" prefix="route.">
    <html lang="${language}">
    <head>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- bootstrap theme -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
        <!--external css-->
        <!-- font icon -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/elegant-icons-style.css"
              rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.min.css" rel="stylesheet"/>
        <!-- Custom styles -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style-responsive.css" rel="stylesheet"/>
        <!-- javascripts -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- nicescroll -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.scrollTo.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.nicescroll.js"
                type="text/javascript"></script>
        <!--custome script for all page-->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/scripts.js"></script>

        <title>Adding route</title>
    </head>
    <body>
    <section id="container" class="">
        <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i
                        class="icon_menu"></i></div>
            </div>

            <!--logo start-->
            <a href="${pageContext.request.contextPath}/park/index" class="logo">Car <span class="lite">Park</span></a>
            <!--logo end-->
            <div class="top-nav notification-row">
                <ul>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img alt=""
                                     src="${pageContext.request.contextPath}/resources/bootstrap/img/avatar1_small.jpg">
                            </span>
                            <span class="username"><c:out value="${sessionScope.user.firstName}"/> <c:out
                                    value="${sessionScope.user.secondName}"/></span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li>
                                <a href="${pageContext.request.contextPath}/park/logout"><i class="icon_key_alt"></i>Log
                                    Out</a>
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
                        <a class="" href="${pageContext.request.contextPath}/park/">
                            <i class="icon_house"></i>
                            <span><fmt:message key="index_page"/></span>
                        </a>
                    </li>
                    <li class="sub-menu">
                        <a href="${pageContext.request.contextPath}/park/admin" class="">
                            <i class="icon_house_alt"></i>
                            <span><fmt:message key="home_page"/></span>
                        </a>
                    </li>
                    <li class="sub-menu">
                        <a class="" href="${pageContext.request.contextPath}/park/admin/add_car">
                            <i class="icon_plus_alt"></i>
                            <span><fmt:message key="bus_page"/></span>
                        </a>
                    </li>
                </ul>
                <!-- sidebar menu end-->
            </div>
        </aside>
        <!--header end-->
        <section id="main-content">
            <section class="wrapper">
                <div class="col-lg-4 col-lg-offset-2">
                    <div class="row">
                        <div class="text-center">
                            <h2><fmt:message key="welcome"/></h2>
                        </div>
                    </div>
                    <div class="row">
                        <form method="post" class="form-horizontal" action="<c:url value="/park/admin/add_new_route"/>">
                            <div class="row">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <label class="col-sm-2 control-label"><fmt:message key="from_en"/></label>
                                            <input type="text" class="form-control round-input" name="start">
                                        </div>
                                        <div class="col-sm-6">
                                            <label class="col-sm-2 control-label"><fmt:message key="to_en"/></label>
                                            <input type="text" class="form-control round-input" name="finish">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <div class="form-group">
                                        <div class="col-sm-6">
                                            <label class="col-sm-2 control-label"><fmt:message key="from_ua"/></label>
                                            <input type="text" class="form-control round-input" name="start_ua">
                                        </div>
                                        <div class="col-sm-6">
                                            <label class="col-sm-2 control-label"><fmt:message key="to_ua"/></label>
                                            <input type="text" class="form-control round-input" name="finish_ua">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-5">
                                        <br/><br/>
                                        <input class="btn btn-info" type="submit" value="<fmt:message key="add"/>">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-lg-6">
                    <section class="panel">
                        <header class="panel-heading text-center">
                            <fmt:message key="routes"/>
                        </header>
                        <table class="table table-striped table-advance table-hover">
                            <tbody>
                            <tr>
                                <th><i class="icon_pin_alt"></i><fmt:message key="from"/></th>
                                <th><i class="icon_pin_alt"></i><fmt:message key="to"/></th>
                            </tr>
                            <c:forEach var="route" items="${requestScope.routeList}">
                                <tr class="table-secondary">
                                    <td><c:out value="${route.start}"/></td>
                                    <td><c:out value="${route.finish}"/></td>
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
                                    <span class="sr-only"><fmt:message key="pagin.prev"/></span>
                                </a>
                            </li>
                            <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/park/admin/add_route?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only"><fmt:message key="pagin.next"/></span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </section>
        </section>
    </section>
    </body>
    </html>
</fmt:bundle>