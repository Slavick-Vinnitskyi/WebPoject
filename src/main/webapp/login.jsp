<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
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
    <title>Login</title>


    </head>
        <body class="login-img3-body">
            <div class="container">
                <form class="login-form" method="post" action="${pageContext.request.contextPath}/park/login">
                    <div><p><c:out value="${requestScope.info}"/></p><div>
                    <div class="login-wrap">
                        <p class="login-img"><i class="icon_lock_alt"></i></p>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="icon_profile"></i></span>
                            <input type="text" class="form-control" placeholder="Username" autofocus name="name">
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                            <input type="password" class="form-control" placeholder="Password" name="pass">
                        </div>
                        <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
                        <div class="text-center">
                            <a href="${pageContext.request.contextPath}/park/logout">Deny</a>
                        </div>
                    </div>
                </form>
            </div>
        </body>
</html>