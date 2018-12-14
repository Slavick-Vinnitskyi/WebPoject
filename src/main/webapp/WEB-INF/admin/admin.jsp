<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<html>
<head>
    &lt;%&ndash;<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">&ndash;%&gt;

    &lt;%&ndash;<!-- bootstrap theme -->&ndash;%&gt;
    &lt;%&ndash;<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">&ndash;%&gt;
    &lt;%&ndash;<!--external css-->&ndash;%&gt;
    &lt;%&ndash;<!-- font icon -->&ndash;%&gt;
    &lt;%&ndash;<link href="${pageContext.request.contextPath}/resources/bootstrap/css/elegant-icons-style.css" rel="stylesheet" />&ndash;%&gt;
    &lt;%&ndash;<link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.min.css" rel="stylesheet" />&ndash;%&gt;
    &lt;%&ndash;<!-- Custom styles -->&ndash;%&gt;
    &lt;%&ndash;<link href="${pageContext.request.contextPath}/resources/bootstrap/css/style.css" rel="stylesheet">&ndash;%&gt;
    &lt;%&ndash;<link href="${pageContext.request.contextPath}/resources/bootstrap/css/style-responsive.css" rel="stylesheet" />&ndash;%&gt;
    &lt;%&ndash;<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>&ndash;%&gt;
    <title>ADMIN</title>
</head>
<body>

<h1 align="center">Admin page</h1>

<table  border="1" style="width:75%" align="center" class="table table-hover">
    <tr class="table-dark"><th>Route</th>
        <th>Date</th>
        <th>Driver</th>
        <th>Appointment</th>
    </tr>
    <c:forEach var="i" items="${requestScope.routeList}">
    <tr class="table-secondary"><td>${i.start} <c:out value="${i.finish}"/></td>
        <td>${i.startUa}</td>
        <td>${i.finishUa}</td>
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
    </tr>
        </c:forEach>
</table>--%>
<!DOCTYPE html>
<html lang="en">

<head>

    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/elegant-icons-style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom styles -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style-responsive.css" rel="stylesheet"/>

    <!-- javascripts -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- nicescroll -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.scrollTo.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!--custome script for all page-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/scripts.js"></script>

    <title>Admin Page</title>

</head>

<body>
<!-- container section start -->
<section id="container" class="">
    <!--header start-->
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
                        <span class="username">Jenifer Smith</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu extended logout">
                        <div class="log-arrow-up"></div>
                        <li class="eborder-top">
                            <a href="#"><i class="icon_profile"></i> My Profile</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/park/logout"><i class="icon_key_alt"></i>Log Out</a>
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
                    <a class="" href="index.html">
                        <i class="icon_house_alt"></i>
                        <span>Index page</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/park/admin/add_route" class="">
                        <i class="icon_desktop"></i>
                        <span>Add route</span>
                        <span class="menu-arrow arrow_carrot-right"></span>
                    </a>
                </li>
                <li class="sub-menu">
                        <a class="" href="${pageContext.request.contextPath}/park/admin/add_car">
                            <i class="icon_house_alt"></i>
                            <span>Add bus</span>
                        </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>

    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading">
                            Give a few assignments to your driver`s!
                        </header>

                        <table class="table table-striped table-advance table-hover">
                            <tbody>
                            <tr>
                                <th><i class="icon_profile"></i> Full Name</th>
                                <th><i class="icon_calendar"></i> Date</th>
                                <th><i class="icon_mail_alt"></i> Email</th>
                                <th><i class="icon_pin_alt"></i> City</th>
                                <th><i class="icon_mobile"></i> Mobile</th>
                                <th><i class="icon_cogs"></i> Action</th>
                            </tr>
                            <tr>
                                <td>Angeline Mcclain</td>
                                <td>2004-07-06</td>
                                <td>dale@chief.info</td>
                                <td>Rosser</td>
                                <td>176-026-5992</td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                                        <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                                        <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Sung Carlson</td>
                                <td>2011-01-10</td>
                                <td>ione.gisela@high.org</td>
                                <td>Robert Lee</td>
                                <td>724-639-4784</td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                                        <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                                        <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Bryon Osborne</td>
                                <td>2006-10-29</td>
                                <td>sol.raleigh@language.edu</td>
                                <td>York</td>
                                <td>180-456-0056</td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                                        <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                                        <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                </div>
            </div>
            <!-- page end-->
        </section>
    </section>
</section>


</body>
</html>