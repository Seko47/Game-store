<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 22.12.2018
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:import url="shared/header.jsp">
    <c:param name="pageName" value="registrationSuccess"/>
</c:import>
<head>
    <title>Title</title>
</head>
<body>
<main role="main" class="container text-center">
    <div class="starter-template">
        <h1>${title}</h1>
        <p class="lead">
            ${message}
        </p>
    </div>
</main>
</body>
<c:import url="shared/footer.jsp"/>
</html>
