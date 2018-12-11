<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

    <!-- bootstrap theme -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/style-responsive.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js" type="text/javascript"></script>
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
</table>
<div class="row">
    <div class="col-lg-12">
        <section class="panel">
            <header class="panel-heading">
                Advanced Table
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
                <tr>
                    <td>Dalia Marquez</td>
                    <td>2011-12-15</td>
                    <td>angeline.frieda@thick.com</td>
                    <td>Alton</td>
                    <td>690-601-1922</td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                            <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                            <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Selina Fitzgerald</td>
                    <td>2003-01-06</td>
                    <td>moshe.mikel@parcelpart.info</td>
                    <td>Waelder</td>
                    <td>922-810-0915</td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                            <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                            <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Abraham Avery</td>
                    <td>2006-07-14</td>
                    <td>harvey.jared@pullpump.org</td>
                    <td>Harker Heights</td>
                    <td>511-175-7115</td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                            <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                            <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Caren Mcdowell</td>
                    <td>2002-03-29</td>
                    <td>valeria@hookhope.org</td>
                    <td>Blackwell</td>
                    <td>970-147-5550</td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                            <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                            <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Owen Bingham</td>
                    <td>2013-04-06</td>
                    <td>thomas.christopher@firstfish.info</td>
                    <td>Rule</td>
                    <td>934-118-6046</td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                            <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                            <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Ahmed Dean</td>
                    <td>2008-03-19</td>
                    <td>lakesha.geri.allene@recordred.com</td>
                    <td>Darrouzett</td>
                    <td>338-081-8817</td>
                    <td>
                        <div class="btn-group">
                            <a class="btn btn-primary" href="#"><i class="icon_plus_alt2"></i></a>
                            <a class="btn btn-success" href="#"><i class="icon_check_alt2"></i></a>
                            <a class="btn btn-danger" href="#"><i class="icon_close_alt2"></i></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Mario Norris</td>
                    <td>2010-02-08</td>
                    <td>mildred@hour.info</td>
                    <td>Amarillo</td>
                    <td>945-547-5302</td>
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
<a href="${pageContext.request.contextPath}/park/admin/add_car">Add new car</a>
<a href="${pageContext.request.contextPath}/park/admin/add_route">Add new route</a>
<a href="${pageContext.request.contextPath}/park/logout">Logout</a>
</body>
</html>