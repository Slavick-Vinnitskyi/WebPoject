<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language
 : not empty language ? language : pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}" scope = "session"/>
<fmt:bundle basename="index" prefix="index.">
<html lang="${language}">
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


        <title>The all-new Uber</title>
</head>
    <body>
   <%-- <form>
        <select id = "language" name = "language" onchange = "submit()">
            <option value = "en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value = "ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
        </select>
--%>
   <section id="container" class="">
       <header class="header dark-bg">
           <!--logo start-->
           <a href="${pageContext.request.contextPath}/park/index" class="logo">Car <span class="lite">Park</span></a>
           <!--logo end-->
       </header>
       <!--header end-->

       <section id="container main-content">
           <section class="wrapper">
               <div class="row">
                   <div align = "center">
                       <a class="btn btn-primary" href="${pageContext.request.contextPath}/login.jsp" ><fmt:message key="login"/></a>
                       <a class="btn btn-success" href="${pageContext.request.contextPath}/park/register"><fmt:message key="signup"/></a>
                       <br><br>
                   </div>
               </div>
               <div class="row">
                   <div class="col-lg-8 col-lg-offset-2">
                       <section class="panel">
                           <header class="panel-heading text-center">
                               <fmt:message key="welcome"/> <fmt:message key="schedule"/>
                           </header>

                           <table class="table table-striped table-advance table-hover">
                               <tbody>
                               <tr>
                                   <th><i class="icon_pin_alt"></i><fmt:message key="from"/> </th>
                                   <th><i class="icon_pin_alt"></i><fmt:message key="to"/></th>
                                   <th><i class="icon_calendar"></i> <fmt:message key="time"/></th>
                               </tr>
                               <c:forEach var = "ass" items="${requestScope.assignmentList}">
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
           </section>
       </section>
   </section>
    </body>
</html>
</fmt:bundle>