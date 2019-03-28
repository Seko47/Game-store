<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 19.10.2018
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
          integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX" crossorigin="anonymous">
    <!--<link rel="stylesheet" type="text/css" href="webjars/bootstrap-material-design/4.1.1/assets/css/docs.min.css"/>-->
    <link rel="stylesheet" type="text/css" href="/statics/style.css"/>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
            integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
            integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
            crossorigin="anonymous"></script>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="/"><img src="/upload/RS_logo.png" alt="Strona główna" height="60pt"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbar" aria-controls="myNavbar"
            aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"/>
    </button>
    <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="navbar-nav mr-md-auto">
            <li class="nav-item">
                <a class="nav-link ${param['pageName'] == 'gameList' ? 'active':''}"
                   href="/gameList">Gry</a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${param['pageName'] == 'shoppingCart' ? ' active':''}"
                   href="/shoppingCart">Koszyk</a>
            </li>
            <security:authorize access="hasRole('USER')">
                <li class="nav-item">
                    <a class="nav-link ${param['pageName'] == 'orderList' ? ' active':''}"
                       href="/orders">Zamówienia</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <div class="dropdown show">
                        <a class="nav-link text-danger dropdown-toggle" href="#" role="button"
                           id="dropdownMenuLinkForAdmin"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Panel administratora
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLinkForAdmin">
                            <a class="dropdown-item ${param['pageName'] == 'accountManagement' ? ' active':''}"
                               href="/adminPanel/accountManagement">Zarządzanie uprawnieniami</a>
                            <a class="dropdown-item ${param['pageName'] == 'gameCategoriesManagement' ? ' active':''}"
                               href="/adminPanel/gameCategoriesManagement">Zarządzanie kategoriami gier</a>
                        </div>
                    </div>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('EMPLOYEE')">
                <li class="nav-item">
                    <div class="dropdown show">
                        <a class="nav-link text-danger dropdown-toggle" href="#" role="button"
                           id="dropdownMenuLinkForEmployee"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Panel pracownika
                        </a>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLinkForEmployee">
                            <a class="dropdown-item ${param['pageName'] == 'gameManagement' ? ' active':''}"
                               href="/employeePanel/gameManagement">Zarządzanie grami</a>
                            <a class="dropdown-item ${param['pageName'] == 'orderManagement' ? ' active':''}"
                               href="/employeePanel/orderManagement">Zarządzanie zamówieniami</a>
                        </div>
                    </div>
                </li>
            </security:authorize>
        </ul>
        <ul class="navbar-nav ml-md-auto"
        <security:authorize access="isAnonymous()">
            <li class="nav-item text-md-right">
                <a class="nav-link"
                   href="/registration"  ${param['pageName'] == "registrationForm" ? 'class="active"':''}>Zarejestruj
                    się</a>
            </li>
            <li class="nav-item text-md-right">
                <a class="nav-link" href="/login"  ${param['pageName'] == "loginForm" ? 'class="active"':''}>Zaloguj
                    się</a>
            </li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <li>
                <span class="navbar-text">
                <a class="nav-link ${param['pageName'] == 'account' ? ' active':''}"
                   href="/account">Witaj <strong><security:authentication property="principal.username"/></strong>!</a>
                </span>

            </li>
            <li>
                <span class="navbar-text">
                    <a class="nav-link" href="#" onclick="document.getElementById('logout').submit()">Wyloguj się</a>
                    <form action="/logout" id="logout" method="POST" style="display: none;">
                        <security:csrfInput/>
                    </form>
                </span>
            </li>
        </security:authorize>
        </ul>
    </div>
</nav>
<br>
</body>
</html>
