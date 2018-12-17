<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language
 : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}" scope = "session"/>
<fmt:bundle basename="admin" prefix="admin.">
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

    <title><fmt:message key="title"/></title>

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
        <a href="${pageContext.request.contextPath}/park/logout" class="logo">Car <span class="lite">Park</span></a>
        <!--logo end-->
        <div class="top-nav notification-row">
            <ul>
                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="username"><c:out value="${sessionScope.firstName}"/> <c:out value="${sessionScope.secondName}"/></span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu extended logout">
                        <div class="log-arrow-up"></div>
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
                    <a class="" href="${pageContext.request.contextPath}/park/logout">
                        <i class="icon_house"></i>
                        <span>Index page</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="${pageContext.request.contextPath}/park/admin/add_route" class="">
                        <i class="icon_plus_alt"></i>
                        <span>Add route</span>
                    </a>
                </li>
                <li class="sub-menu">
                        <a class="" href="${pageContext.request.contextPath}/park/admin/add_car">
                            <i class="icon_plus_alt"></i>
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
                <form method="post" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="table.route"/></label>
                                <select class="form-control m-bot15">
                                    <option>Route 1</option>
                                    <option>Option 2</option>
                                    <option>Option 3</option>
                                </select>
                                <%--<input type="text" class="form-control round-input" name="mark">--%>
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="input.date"/></label>
                                <input id="dp1" type="text"  value="28-10-2013" class="form-control round-input" name="date">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="input.auto"/></label>
                                <select class="form-control m-bot15">
                                    <option>Car 1</option>
                                    <option>Option 2</option>
                                    <option>Option 3</option>
                                </select>
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="input.driver"/></label>
                                <select class="form-control m-bot15">
                                    <option>Driver 1</option>
                                    <option>Option 2</option>
                                    <option>Option 3</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-1">
                            <input class="btn btn-info" type="submit" value="<fmt:message key="input.submit"/>">
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <section class="panel">
                        <header class="text-center">
                            <fmt:message key="assign"/>
                        </header>

                        <table class="table table-striped table-advance table-hover">
                            <tbody>
                            <tr>
                                <th><i class="icon_profile"></i> <fmt:message key="table.login"/></th>
                                <th><i class="icon_calendar"></i> <fmt:message key="table.date"/></th>
                                <th><i class="icon_drive_alt"></i> <fmt:message key="table.car"/></th>
                                <th><i class="icon_pin_alt"></i> <fmt:message key="table.route"/></th>
                            </tr>
                            <tr>
                                <td>Angeline232</td>
                                <td>2004-07-06</td>
                                <td>Mercedez</td>
                                <td>Kyiv - Kharkiv</td>
                            </tr>
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
                <div class="col-lg-6">
                    <section class="panel">
                        <header class="text-center">
                            <fmt:message key="applied"/>
                        </header>

                        <table class="table table-striped table-advance table-hover">
                            <tbody>
                            <tr>
                                <th><i class="icon_profile"></i> <fmt:message key="table.login"/></th>
                                <th><i class="icon_calendar"></i> <fmt:message key="table.date"/></th>
                                <th><i class="icon_drive_alt"></i> <fmt:message key="table.car"/></th>
                                <th><i class="icon_pin_alt"></i> <fmt:message key="table.route"/></th>
                            </tr>
                            <tr>
                                <td>Angeline232</td>
                                <td>2004-07-06</td>
                                <td>Mercedez</td>
                                <td>Kyiv - Kharkiv</td>
                            </tr>
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
            <!-- page end-->
        </section>
    </section>
</section>
</body>
</html>
</fmt:bundle>