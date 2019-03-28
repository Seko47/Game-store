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
    <c:param name="pageName" value="gameDetails"/>
</c:import>

<html>
<head>
    <title>Szczegóły gry</title>
</head>
<body>

<div class="container text-center">
    <div class="bg-dark pt-3 pb-3 mb-3 text-white pr-3 pl-3" style="border-radius: 5%">
        <div class="row row-space">
            <div class="col-md-4 mb-3">
                <div class="row row-space">
                    <c:if test="${!empty store.game.coverURI}">
                        <div class="col-md-12 mb-3">
                            <img class="col-md-12" src="${store.game.coverURI}" alt="Brak zdjęcia"/>
                        </div>
                    </c:if>
                    <c:if test="${!empty store.game.image1 || !empty store.game.image2 || !empty store.game.image3}">
                        <button type="button" class="btn btn-warning btn-lg btn-block" data-toggle="modal"
                                data-target="#exampleModalCenter">
                            Galeria
                        </button>
                    </c:if>

                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title text-dark"
                                        id="exampleModalCenterTitle">${store.game.name}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <c:if test="${!empty store.game.image1 || !empty store.game.image2 || !empty store.game.image3}">
                                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                            <ol class="carousel-indicators">
                                                <c:if test="${!empty store.game.image1}">
                                                    <li data-target="#carouselExampleIndicators" data-slide-to="0"
                                                        class="active"></li>
                                                </c:if>
                                                <c:if test="${!empty store.game.image2}">
                                                    <li data-target="#carouselExampleIndicators" data-slide-to="1"
                                                            <c:if test="${empty store.game.image1}"> class="active"</c:if>
                                                    ></li>
                                                </c:if>
                                                <c:if test="${!empty store.game.image3}">
                                                    <li data-target="#carouselExampleIndicators" data-slide-to="2"
                                                            <c:if test="${empty store.game.image1 && empty store.game.image2}"> class="active"</c:if>
                                                    ></li>
                                                </c:if>
                                            </ol>
                                            <div class="carousel-inner">
                                                <c:if test="${!empty store.game.image1}">
                                                    <div class="carousel-item active">
                                                        <img class="d-block w-100" src="${store.game.image1}"
                                                             alt="First slide">
                                                    </div>
                                                </c:if>
                                                <c:if test="${!empty store.game.image2}">
                                                    <div class="carousel-item <c:if test="${empty store.game.image1}">active</c:if>">
                                                        <img class="d-block w-100" src="${store.game.image2}"
                                                             alt="Second slide">
                                                    </div>
                                                </c:if>
                                                <c:if test="${!empty store.game.image3}">
                                                    <div class="carousel-item <c:if test="${empty store.game.image1 && empty store.game.image2}">active</c:if>">
                                                        <img class="d-block w-100" src="${store.game.image3}"
                                                             alt="Third slide">
                                                    </div>
                                                </c:if>
                                            </div>
                                            <a class="carousel-control-prev" href="#carouselExampleIndicators"
                                               role="button"
                                               data-slide="prev">
                                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Previous</span>
                                            </a>
                                            <a class="carousel-control-next" href="#carouselExampleIndicators"
                                               role="button"
                                               data-slide="next">
                                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                <span class="sr-only">Next</span>
                                            </a>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Zamknij
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <div class="col-md-5 mb-3">
                <div class="row row-space">
                    <div class="col-md-12">
                        <p>
                        <h3><u><b>${store.game.name}</b></u></h3></p>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-md-12">
                        <p><b>${store.game.descriptionShort}</b></p>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-md-12">
                        <p>${store.game.descriptionLong}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 mb-3">
                <div class="row row-space">
                    <div class="col-md-12">
                        <p class=" text-right">
                            <u>Wydawca:</u> <b>${store.game.publisher}</b>
                        </p>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-md-12">
                        <p class=" text-right">
                            <u>Producent:</u> <b>${store.game.producer}</b>
                        </p>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-md-12">
                        <p class=" text-right">
                            <u>Data wydania:</u> <b>${store.game.releaseDate}</b>
                        </p>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-md-12">

                        <ul class="text-right">
                            <u>Kategorie gry:</u>
                            <c:forEach items="${store.game.gameCategories}" var="category">
                                <li class="text-right" style="list-style-type: none;">
                                    <b>${category.name}</b>
                                </li>
                            </c:forEach>

                        </ul>

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
</div>
</body>
</html>

<c:import url="shared/footer.jsp"/>
