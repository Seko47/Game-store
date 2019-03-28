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
    <c:param name="pageName" value="gameCategoriesManagement"/>
</c:import>
<head>
    <title>Formularz kategorii</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Formularz kategorii</h2>

        <form:form modelAttribute="gameCategory" action="/adminPanel/gameCategoriesManagement/save">
            <form:input path="id" cssClass="invisible disabled"/>
            <div class="mb-3">
                <label for="name" class="required">Nazwa kategorii</label><br>
                <form:errors path="name" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Nazwa gry" required="true"/>
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-lg btn-block">Zapisz</button>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
