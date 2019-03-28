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
    <c:param name="pageName" value="orderDetails"/>
</c:import>

<html>
<head>
    <title>Szczegóły zamówienia</title>
</head>
<body>

<div class="text-center container-fluid">

    <h1>Szczegóły zamówienia</h1>
    <c:forEach var="item" items="${order.orderDetails}">
        <div class="bg-dark pt-3 pb-3 mb-3 text-white pr-3 pl-3 col-md-2 list-inline-item"
             style="border-radius: 5%">
            <div class="row row-space m-auto">
                <div class="col-md-12">
                    <h3>
                        <u><a class="btn text-white btn-md btn-block"
                              href="/gameList/${item.game.id}">${item.game.name}</a></u>
                    </h3>
                </div>
            </div>
            <div class="row row-space">
                <div class="col-md-6 mb-3">
                    <c:choose>
                        <c:when test="${!empty item.game.coverURI}">
                            <img class="col-md-12"
                                 src="${item.game.coverURI}"
                                 alt="Brak zdjęcia"/>
                        </c:when>
                        <c:otherwise>
                            Brak zdjęcia
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-6 mb-3">
                    <div class="row row-space">
                        <div class="col-md-12">
                            <p>
                                Ilość: ${item.quantity}
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row row-space">
                <div class="col-md-12">
                    <p>Cena gry: ${item.game.price} PLN</p>
                </div>
            </div>
            <div class="row row-space">
                <div class="col-md-12">
                    <p>Cena łączna: ${item.game.price * item.quantity} PLN</p>
                </div>
            </div>
        </div>
    </c:forEach>
    <p>Cena łączna zamówienia: ${order.totalValue} PLN</p>

    <security:authorize access="hasRole('EMPLOYEE')">
        <c:if test="${order.status.id == 1}">
            <a href="/employeePanel/orderManagement/accept/${order.id}"
               class="btn btn-danger btn-lg btn-block">Akceptuj</a>
        </c:if>
    </security:authorize>
</div>
</body>
</html>

<c:import url="shared/footer.jsp"/>
