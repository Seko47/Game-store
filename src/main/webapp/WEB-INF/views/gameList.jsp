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
    <c:param name="pageName" value="gameList"/>
</c:import>

<html>
<head>
    <title>Gry</title>
</head>
<body>

<div class="container text-center">

    <form:form id="searchForm" modelAttribute="searchCommand">
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
                <div class="col-md-3">
                    <div class="form-check style='left:25px;'">
                        <label>
                            <form:checkbox path="allGames" cssClass="form-check-input"
                                           cssErrorClass="form-control is-invalid"/>
                            Wyświetl też gry, których nie ma w sklepie
                        </label>
                    </div>
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">Szukaj</button>
        </div>
    </form:form>

    <c:choose>
        <c:when test="${!empty gameListPage.content}">
            <c:forEach var="store" items="${gameListPage.content}">
                <div class="bg-dark pt-3 pb-3 mb-3 text-white pr-3 pl-3" style="border-radius: 5%">
                    <div class="row row-space">
                        <div class="col-md-4 mb-3">
                            <c:choose>
                                <c:when test="${!empty store.game.coverURI}">
                                    <img class="col-md-8"
                                         src="${store.game.coverURI}"
                                         alt="Brak zdjęcia"/>
                                </c:when>
                                <c:otherwise>
                                    Brak zdjęcia
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-md-8 mb-3">
                            <div class="row row-space">
                                <div class="col-md-12">
                                    <p>
                                    <h3><u><a class="btn text-white btn-lg btn-block"
                                              href="/gameList/${store.game.id}">${store.game.name}</a></u></h3></p>
                                </div>
                            </div>
                            <div class="row row-space">
                                <div class="col-md-12">
                                    <p>${store.game.descriptionShort}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row row-space">
                        <div class="col-md-3">
                            <p>${store.game.price} PLN</p>
                        </div>
                        <c:choose>
                            <c:when test="${store.quantity > 0}">
                                <div class="col-md-9">
                                    <a class="btn btn-warning btn-lg btn-block"
                                       href="/shoppingCart/add/${store.game.id}">Dodaj
                                        do
                                        koszyka</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-9">
                                    <p>Brak w sklepie</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>

            <c:set var="page" value="${gameListPage}" scope="request"/>
            <c:set var="mainUrl" value="gameList" scope="request"/>
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
