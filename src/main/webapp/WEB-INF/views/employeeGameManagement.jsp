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
    <c:param name="pageName" value="gameManagement"/>
</c:import>

<html>
<head>
    <title>Zarządzanie grami</title>
</head>
<body>

<div class="container text-center">

    <form:form id="searchForm" modelAttribute="searchGameEmployeeCommand">
        <div class="mb-3">
            <h4>Szukaj</h4>
            <div class="row row-space">
                <div class="col-md-6 mb-3">
                    <label for="phrase">Nazwa</label>
                    <form:errors path="phrase" cssClass="error text-danger" element="div"/>
                    <div class="input-group">
                        <form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                    </div>
                </div>
                <div class="col-md-3 mb-3">
                    <label for="priceMin">Cena min</label>
                    <form:errors path="priceMin" cssClass="error text-danger" element="div"/>
                    <div class="input-group">
                        <form:input path="priceMin" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                    </div>
                </div>
                <div class="col-md-3">
                    <label for="priceMax">Cena max</label>
                    <form:errors path="priceMax" cssClass="error text-danger" element="div"/>
                    <div class="input-group">
                        <form:input path="priceMax" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">Szukaj</button>
        </div>
    </form:form>

    <c:choose>
        <c:when test="${!empty gameListPage.content}">
            <table class="table table-hover table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nazwa</th>
                    <th scope="col">Cena</th>
                    <th scope="col">Wydawca</th>
                    <th scope="col">Producent</th>
                    <th scope="col">Data wydania</th>
                    <th scope="col">Kategoria gry</th>
                    <th scope="col">Ilość</th>
                    <security:authorize access="hasRole('EMPLOYEE')">
                        <th scope="col" class="text-center" colspan="2">Opcje</th>
                    </security:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${gameListPage.content}" var="store">
                    <tr>
                        <th scope="row">
                                ${store.game.id}
                        </th>
                        <td>
                                ${store.game.name}
                        </td>
                        <td>
                                ${store.game.price} PLN
                        </td>
                        <td>
                                ${store.game.publisher}
                        </td>
                        <td>
                                ${store.game.producer}
                        </td>
                        <td>
                                ${store.game.releaseDate}
                        </td>
                        <td>
                            <c:set value="${true}" var="isFirst"/>
                            <c:forEach items="${store.game.gameCategories}" var="category">
                                <c:if test="${!isFirst}"><br/></c:if>
                                <c:set value="${false}" var="isFirst"/>
                                ${category.name}
                            </c:forEach>
                        </td>
                        <security:authorize access="hasRole('EMPLOYEE')">
                            <td>
                                <form method="GET"
                                      action="/employeePanel/gameManagement/changeQuantity/${store.id}">
                                    <input class="invisible disabled" id="id" name="id" value="${store.id}"/>
                                    <div class="mb-3">
                                        <div class="input-group">
                                            <input class="form-control" id="quantity" name="quantity"
                                                   placeholder="${store.quantity}" required="true" type="number"
                                                   min="0"/>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-sm btn-block">Aktualizuj</button>
                                </form>
                            </td>
                            <td>
                                <a href="/employeePanel/gameManagement/edit/${store.id}"
                                   class="btn btn-warning btn-sm btn-block">Edytuj</a>
                            </td>
                            <td>
                                <a href="/employeePanel/gameManagement/delete/${store.id}"
                                   class="btn btn-danger btn-sm btn-block">Wyzeruj</a>
                            </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <a class="btn btn-primary btn-lg btn-block" href="/employeePanel/gameManagement/add">Dodaj</a>

            <c:set var="page" value="${gameListPage}" scope="request"/>
            <c:set var="mainUrl" value="/employeePanel/gameManagement" scope="request"/>
            <c:import url="shared/pagination.jsp"/>
        </c:when>
        <c:otherwise>
            <h1>Brak danych</h1>
            <a class="btn btn-primary btn-lg btn-block" href="/employeePanel/gameManagement/add">Dodaj</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

<c:import url="shared/footer.jsp"/>
