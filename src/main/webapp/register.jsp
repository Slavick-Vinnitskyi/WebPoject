<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

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

    <!-- javascripts -->
    <script src="${pageContext.request.contextPath}resources/bootstrap/js/jquery.js"></script>
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

    <title>Registration</title>
</head>

<body>
<!-- container section start -->
<section id="container" class="">
    <!--header start-->
    <header class="navbar-header header dark-bg">
        <!--logo start-->
        <a href="${pageContext.request.contextPath}/park/index" class="logo">Car <span class="lite">Park</span></a>
        <!--logo end-->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
        <div class="nav navbar-nav">
            <ul>
                <li class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-close-others="true" aria-expanded="true">
                            <img src="https://demo.neontheme.com/assets/images/flags/flag-uk.png" width="16" height="16"> </a>
                        </li>
                        <li>
                            <a href="#"> <img src="https://demo.neontheme.com/assets/images/flags/flag-uk.png" width="16" height="16">
                            <span>English</span> </a>
                        </li>
                    </ul>
                </li>
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
                            Input your data here
                        </header>
                        <div class="panel-body">
                            <div class="form">
                                <form class="form-validate form-horizontal" id="register_form" method="get" action="${pageContext.request.contextPath}/park/register">
                                    <div class="form-group ">
                                        <label for="first_name" class="control-label col-lg-2">First name <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="first_name" name="first_name" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="second_name" class="control-label col-lg-2">Second name <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class=" form-control" id="second_name" name="second_name" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="first_name_ua" class="control-label col-lg-2">Ім`я <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="first_name_ua" name="first_name_ua" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="second_name_ua" class="control-label col-lg-2">Прізвище <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="second_name_ua" name="second_name_ua" type="text" />
                                        </div>
                                    </div>
                                        <div class="form-group ">
                                        <label for="username" class="control-label col-lg-2">Login <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="username" name="username" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                    <label for="password" class="control-label col-lg-2">Password <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="password" name="password" type="password" />
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label for="confirm_password" class="control-label col-lg-2">Confirm Password <span class="required">*</span></label>
                                        <div class="col-lg-10">
                                            <input class="form-control" id="confirm_password" name="confirm_password" type="password" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="text-center">
                                            <button class="btn btn-primary" type="submit">Submit</button>
                                            <button class="btn btn-outline-dark" type="button"><a href="${pageContext.request.contextPath}/park/logout">Deny</a></button>
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