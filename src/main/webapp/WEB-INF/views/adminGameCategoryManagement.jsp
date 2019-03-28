<%--
  Created by IntelliJ IDEA.
  User: Seko
  Date: 18.10.2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="gameCategoriesManagement"/>
</c:import>

<html>
<head>
    <title>Zarządzanie kontami</title>
</head>
<body>

<div class="container text-center">

    <c:choose>
        <c:when test="${!empty gameCategories.content}">
            <table class="table table-hover table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nazwa kategorii</th>
                    <security:authorize access="hasRole('ADMIN')">
                        <th scope="col" class="text-center" colspan="2">Opcje</th>
                    </security:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${gameCategories.content}" var="gameCategory">
                    <tr>
                        <th scope="row">
                                ${gameCategory.id}
                        </th>
                        <td>
                                ${gameCategory.name}
                        </td>
                        <security:authorize access="hasRole('ADMIN')">
                            <td>
                                <a href="/adminPanel/gameCategoriesManagement/edit/${gameCategory.id}"
                                   class="btn btn-warning btn-sm btn-block">Edytuj</a>
                            </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <a href="/adminPanel/gameCategoriesManagement/add"
               class="btn btn-primary btn-lg btn-block">Dodaj</a>

            <c:set var="page" value="${gameCategories}" scope="request"/>
            <c:set var="mainUrl" value="/adminPanel/gameCategoriesManagement" scope="request"/>
            <c:import url="shared/pagination.jsp"/>
        </c:when>
        <c:otherwise>
            <h1>Brak danych</h1>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

<c:import url="shared/footer.jsp"/>
