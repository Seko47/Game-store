<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 19.10.2018
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<BR><BR>
<footer class="footer bg-dark fixed-bottom">
    <div class="container text-center">
        <span class="text-light">
            Rafał Sekular - Sklep z grami
            <security:authorize access="isAuthenticated()">
                <security:authentication property="principal.authorities"/>
            </security:authorize>
        </span>
    </div>
</footer>
</body>
</html>

