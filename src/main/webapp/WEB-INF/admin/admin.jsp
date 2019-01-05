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
                        <span class="username"><c:out value="${sessionScope.user.firstName}"/> <c:out value="${sessionScope.user.secondName}"/></span>
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
                <%--FORM TO INPUT ROUTE AND DATE--%>
                <form method="post" class="form-horizontal" action="<c:url value="/park/admin/find_free_drivers_and_buses"/>">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="table.route"/></label>
                                <select class="form-control m-bot15" name="route">
                                    <option value="0"><c:out value="${sessionScope.selectedRoute.start}"/> - <c:out value="${sessionScope.selectedRoute.finish}"/></option>
                                    <c:forEach var="route" items="${requestScope.routes}">
                                        <option value="${route.id}"><c:out value="${route.start}"/> - <c:out value="${route.finish}"/></option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="input.date"/></label>
                                <input id="dp1" type="text"  value="12-26-2018" class="form-control round-input" name="date">
                            </div>
                        </div>
                        <div class="col-sm-1">
                            <input class="btn btn-info" type="submit" value="<fmt:message key="input.find"/>">
                        </div>
                    </div>
                </form>
            </div>
            <div class="row">

                <%--FORM TO SELECT DRIVER AND HIS CAR--%>
                <%--AJAX SCRIPT FOR THIS FORM--%>
                    <script type="text/javascript">

                        $(document).ready(function () {
                            $('#driverId').on('change', send_id_to_controller);

                        
                    function send_id_to_controller() {
                        $("#carId").empty();
                        var cars_select = $("#carId");
                        var selected = $('#driverId').val();
                        $.ajax({
                            url: "<c:url value="/park/admin/selected_driver_id"/>",
                            method: 'POST',
                            data: {driverId : selected},
                            dataType: "json",
                            success : function(cars) {
                                $.each(cars, function () {
                                    cars_select.append("<option value=\""+this.id+"\">"
                                        + this.model + ", year:" + this.year
                                        + "</option>");
                                });
                                },
                            error: function () {
                                cars_select.append("<option value=\"null\">error</option>");
                            }
                            });
                        }
                        })
                    </script>
                <%--THE FROM ITSELF--%>
                <form method="post"  class="form-horizontal" action="<c:url value="/park/admin/insert_assignment"/>" >
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label"><fmt:message key="input.driver"/></label>
                                <select class="form-control m-bot15" name="driver_id" id="driverId">
                                    <option>-</option>
                                    <c:forEach var="driver" items="${sessionScope.drivers}">
                                        <option value="${driver.id}">
                                            <c:out value="${driver.login}"/>, license:<c:out value="${driver.licenseType}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-6">
                                <label for="carId" class="col-sm-2 control-label"><fmt:message key="input.auto"/></label>
                                <select class="form-control m-bot15" name="car_id" id = "carId">
                                    <%--<option>-</option>--%>
                                    <%--<c:forEach var="car" items="${requestScope.allcars}">--%>
                                    <%--<option value = "${car.id}"> <c:out value = "${car.model}"/></option>--%>
                                    <%--</c:forEach>--%>
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
                            <c:forEach var="assign" items="${requestScope.assigned}">
                                <tr>
                                    <td><c:out value="${assign.driver.login}"/></td>
                                    <td><c:out value="${assign.date}"/></td>
                                    <td><c:out value="${assign.bus.model}"/></td>
                                    <td><c:out value="${assign.route.start}"/> - <c:out value="${assign.route.finish}"/></td>
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
                            <c:forEach var="assign" items="${requestScope.applied}">
                            <tr>
                                <td><c:out value="${assign.driver.login}"/></td>
                                <td><c:out value="${assign.date}"/></td>
                                <td><c:out value="${assign.bus.model}"/></td>
                                <td><c:out value="${assign.route.start}"/> - <c:out value="${assign.route.finish}"/></td>
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
            <strong>Ajax Response</strong>:
            <div id="ajaxGetUserServletResponse"></div>
            <!-- page end-->
        </section>
    </section>
</section>
</body>
</html>
</fmt:bundle>