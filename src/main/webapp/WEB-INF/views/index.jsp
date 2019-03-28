<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 17.10.2018
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona główna</title>
</head>
<body>

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="index"/>
</c:import>
<main role="main" class="container text-center">
    <div class="starter-template">
        <h1>Projekt na platformy programowania</h1>
        <p class="lead">
            Projekt numer 19. Sklep z grami komputerowymi.<BR>
            Autor: <strong>Rafał Sekular</strong>
        </p>
    </div>
</main>
<c:import url="shared/footer.jsp"/>
</body>
</html>
