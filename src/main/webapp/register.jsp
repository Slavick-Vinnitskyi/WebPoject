<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="index" prefix="register.">
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
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery-1.8.3.min.js"></script>
    <!--custom tagsinput-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.tagsinput.js"></script>

    <!-- custom form component script for this page-->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/form-component.js"></script>
    <!-- custome script for all page -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/scripts.js"></script>

    <!-- javascripts -->
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/jquery-1.8.3.min.js"></script>
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/bootstrap.min.js"></script>
    <!-- nice scroll -->
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/jquery.scrollTo.min.js"></script>
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!-- jquery validate js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}resources/bootstrap/js/jquery.validate.min.js"></script>
    <!-- custom form validation script for this page-->
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/form-validation-script.js"></script>
    <!--custome script for all page-->
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/scripts.js"></script>
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/gritter.js" type="text/javascript"></script>

    <title><fmt:message key="title"/></title>
</head>

<body>
<!-- container section start -->
<section id="container" class="">
    <!--header start-->
    <header class="navbar-header header dark-bg">
        <!--logo start-->
        <a href="${pageContext.request.contextPath}/park/index" class="logo"><c:out value="Car"/><span class="lite"><c:out value="Park"/></span></a>
        <!--logo end-->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
        <div class="nav navbar-nav">
            <ul>

                <!-- user login dropdown end -->
            </ul>
            <!-- notificatoin dropdown end-->
        </div>
        </div>
    </header>
    <!--header end-->


    <!--main content start-->
    <section id="container main-content">
        <section class="wrapper">

            <div class="row">
                <div class="col-lg-4 col-lg-offset-4">
                    <section class="panel">
                        <header class="panel-heading text-center">
                            <fmt:message key="input"/>
                        </header>
                        <div class="panel-body">
                            <div class="form">
                                <form class="form-validate form-horizontal" id="register_form" method="post" action="${pageContext.request.contextPath}/park/register">
                                    <div class="text-center text-danger"><p><c:out value="${requestScope.info}"/></p></div>
                                    <div class="form-group ">
                                        <div class="text-center text-danger"><p><c:out value="${requestScope.wrongFirstName}"/></p></div>
                                        <label for="first_name" class="control-label col-lg-2"><fmt:message key="firstName"/> <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="first_name" name="first_name" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <div class="text-center text-danger"><p><c:out value="${requestScope.wrongSecondName}"/></p></div>
                                        <label for="second_name" class="control-label col-lg-2"><fmt:message key="secondName"/> <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class=" form-control" id="second_name" name="second_name" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <div class="text-center text-danger"><p><c:out value="${requestScope.wrongLogin}"/></p></div>
                                        <label for="username" class="control-label col-lg-2"><fmt:message key="login"/> <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="username" name="username" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <div class="text-center text-danger"><p><c:out value="${requestScope.wrongPassword}"/></p></div>
                                        <label for="password" class="control-label col-lg-2"><fmt:message key="password"/> <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="password" name="password" type="password" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="confirm_password" class="control-label col-lg-2"><fmt:message key="password.repeat"/> <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="confirm_password" name="confirm_password" type="password" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="license" class="control-label col-lg-2"><fmt:message key="license"/><span class="required"></span></label>
                                        <div class="col-lg-10">
                                            <select class="form-control m-bot15" id="license" name="license">
                                                <option value="A"><fmt:message key="license.A"/></option>
                                                <option value="B"><fmt:message key="license.B"/></option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="text-center">
                                            <button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
                                            <button class="btn btn-outline-dark" type="button"><a href="${pageContext.request.contextPath}/park/logout"><fmt:message key="deny"/></a></button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            <!-- page end-->
        </section>
    </section>
</section>
<!-- container section end -->

</body>
</html>
</fmt:bundle>