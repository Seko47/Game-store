<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 17.11.2018
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="loginForm"/>
</c:import>
<head>
    <title>Title</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h4 class="mb-3">Logowanie</h4>

        <c:if test="${param.error != null}">
            <p class="text-danger">Nie udało się zalogować. Konto nie zostało aktywowane lub podano błędny login i/lub
                hasło</p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p class="text-info">Wylogowano pomyślnie</p>
        </c:if>

        <form action="/login" method="post">
            <div class="mb-3">
                <label for="username" class="required">Login</label>
                <div class="input-group">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Login"
                           required>
                </div>
            </div>
            <div class="mb-3">
                <label for="password" class="required">Hasło</label>
                <div class="input-group">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Hasło" required>
                </div>
            </div>
            <security:csrfInput/>
            <button type="submit" class="btn btn-primary btn-lg btn-block">Zaloguj</button>
        </form>
    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
