<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="bus" prefix="bus.">
<html>
    <head>
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
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-ui-1.9.2.custom.min.js"></script>

        <!--custom checkbox & radio-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/ga.js"></script>
        <!--custom switch-->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-switch.js"></script>
        <!--custom tagsinput-->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.tagsinput.js"></script>

        <!-- colorpicker -->

        <!-- bootstrap-wysiwyg -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.hotkeys.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-wysiwyg.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-wysiwyg-custom.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/moment.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-colorpicker.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/daterangepicker.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-datepicker.js"></script>
        <!-- ck editor -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/assets/ckeditor/ckeditor.js"></script>
        <!-- custom form component script for this page-->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/form-component.js"></script>
        <!-- custome script for all page -->
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/scripts.js"></script>

        <title><fmt:message key="title"/></title>
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
                            <li>
                                <a href="${pageContext.request.contextPath}/park/logout"><i class="icon_key_alt"></i><fmt:message key="ava.logout"/></a>
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
                        <a class=""  href="${pageContext.request.contextPath}/park/">
                            <i class="icon_house"></i>
                            <span><fmt:message key="navigation.index"/></span>
                        </a>
                    </li>
                    <li class="sub-menu">
                        <a href="${pageContext.request.contextPath}/park/admin" class="">
                            <i class="icon_house_alt"></i>
                            <span><fmt:message key="navigation.home"/></span>
                        </a>
                    </li>
                    <li class="sub-menu">
                        <a class="" href="${pageContext.request.contextPath}/park/admin/add_route">
                            <i class="icon_plus_alt"></i>
                            <span><fmt:message key="navigation.add_route"/></span>
                        </a>
                    </li>
                </ul>
                <!-- sidebar menu end-->
            </div>
        </aside>
        <!--header end-->
        <section id="main-content">
            <section class="wrapper">
                <div class="row">
                    <div class="text-center">
                        <h2><fmt:message key="welcome"/></h2>
                    </div>
                </div>
                <div class="row">
                    <form method="post" class="form-horizontal" action="<c:url value="/park/admin/add_new_car"/>">
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-4">
                                <div class="col-sm-6">
                                    <label class="col-sm-2 control-label"><fmt:message key="add.mark"/></label>
                                    <input type="text" class="form-control round-input" name="mark">
                                </div>
                                <div class="col-sm-6">
                                    <label class="col-sm-2 control-label"><fmt:message key="add.year"/></label>
                                    <input id="dp1" type="text"  value="28-10-2013" class="form-control round-input" name="date">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-1 col-sm-offset-7">
                                <div class="radios">
                                    <label class="label_radio" for="radio-01">
                                        <input name="sample-radio" id="radio-01" value="A" type="radio" checked/><fmt:message key="add.type"/> "A"
                                    </label>
                                    <label class="label_radio" for="radio-02">
                                        <input name="sample-radio" id="radio-02" value="B" type="radio"/><fmt:message key="add.type"/> "B"
                                    </label>
                                </div>
                            </div>
                            <div class="col-sm-1">
                            <input class="btn btn-info" type="submit" value="<fmt:message key="add.add"/>" >
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <section class="panel">
                            <header class="panel-heading text-center">
                                <fmt:message key="cars"/>
                            </header>

                            <table class="table table-striped table-advance table-hover">
                                <tbody>
                                <tr>
                                    <th><i class="icon_tool"></i> <fmt:message key="add.mark"/> </th>
                                    <th><i class="icon_calendar"></i> <fmt:message key="add.year"/></th>
                                    <th><i class="icon_info"></i> <fmt:message key="add.type"/></th>
                                </tr>
                                <c:forEach var = "route" items="${requestScope.carList}">
                                    <tr class="table-secondary">
                                        <td><c:out value="${route.model}"/></td>
                                        <td><c:out value="${route.year}"/></td>
                                        <td><c:out value="${route.licenseType}"/></td>
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
                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/park/admin/add_car?page=${i}">${i}</a></li>
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
                </div>
            </section>
        </section>
    </section>
    </body>
    </html>
</fmt:bundle>