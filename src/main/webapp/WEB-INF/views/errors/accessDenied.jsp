<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 17.11.2018
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<c:import url="../shared/header.jsp">
    <c:param name="pageName" value="error"/>
</c:import>
<head>
    <title>Title</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h4 class="mb-3">Brak uprawnień</h4>
        <p>Niestety nie masz uprawnień do tej strony</p>
    </div>
</div>
</body>
<c:import url="../shared/footer.jsp"/>
</html>
