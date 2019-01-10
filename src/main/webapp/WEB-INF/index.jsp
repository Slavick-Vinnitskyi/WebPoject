<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:bundle basename="index" prefix="index.">
<html>
<%--TODO: добавить c:if для проверти роли на индексе чтобы не выводить эти кнопки--%>
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
   <section id="container" class="">
       <header class="header dark-bg">
           <!--logo start-->
           <a href="${pageContext.request.contextPath}/park/index" class="logo">Car <span class="lite">Park</span></a>
           <!--logo end-->
           <div style="text-align: right" id="lang-div">
               <a href="?sessionLocale=en"><img src="${pageContext.request.contextPath}/resources/bootstrap/img/icons/us.png"><fmt:message key="lang.en"/></a>
               <br>
               <a href="?sessionLocale=ua"><img src="${pageContext.request.contextPath}/resources/bootstrap/img/icons/ua.png"><fmt:message key="lang.ua"/></a>
           </div>
       </header>
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
       <!--header end-->

       <section id="container main-content">
           <section class="wrapper">
               <div class="row">
                   <div align = "center">
                       <a class="btn btn-primary" href="${pageContext.request.contextPath}/login.jsp" ><fmt:message key="login"/></a>
                       <a class="btn btn-success" href="${pageContext.request.contextPath}/register.jsp"><fmt:message key="signup"/></a>
                       <br><br>
                   </div>
               </div>
               <div class="row">
                   <div class="col-lg-8 col-lg-offset-2">
                       <section class="panel">
                           <header class="panel-heading text-center">
                               <c:if test="${requestScope.selectError == null}">
                               <fmt:message key="welcome"/> <fmt:message key="schedule"/>
                               </c:if>
                               <c:if test="${requestScope.selectError != null}">
                                   <c:out value="${requestScope.selectError}"/>
                               </c:if>
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
                               <c:if test="${requestScope.pagesError != null}">
                                   <c:out value="${requestScope.pagesError}"/>
                               </c:if>
                               <c:if test="${requestScope.pagesError == null}">
                                   <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                                       <li class="page-item">
                                           <a class="page-link" href="${pageContext.request.contextPath}/park/index?page=${i}">${i}</a>
                                       </li>
                                   </c:forEach>
                               </c:if>
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