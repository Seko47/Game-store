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
    <c:param name="pageName" value="orderList"/>
</c:import>

<html>
<head>
    <title>Gry</title>
</head>
<body>

<div class="container text-center">

    <c:choose>
        <c:when test="${!empty orders.content}">
            <form:form id="searchForm" modelAttribute="searchCommand">
                <div class="mb-3">
                    <h4>Szukaj</h4>
                    <div class="row row-space">
                        <div class="col-md-12 mb-3">
                            <label for="status">Status</label>
                            <div class="input-group">
                                <form:select path="status" cssClass="form-control">
                                    <form:option value="0">Wszystkie</form:option>
                                    <form:options items="${statuses}" itemLabel="name" itemValue="id"/>
                                </form:select>
                            </div>
                            <button type="submit" class="btn btn-primary btn-md btn-block">Szukaj</button>
                        </div>
                    </div>
                </div>
            </form:form>

            <table class="table table-hover table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">Data złożenia zamówienia</th>
                    <th scope="col">Cena całkowita</th>
                    <th scope="col">Status</th>
                    <th scope="col" class="text-center" colspan="2">Opcje</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders.content}" var="order">
                    <tr>
                        <th scope="row">
                            <fmt:formatDate value="${order.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </th>
                        <td>
                                ${order.totalValue} PLN
                        </td>
                        <td>
                                ${order.status.name}
                        </td>
                        <td>
                            <a href="/orders/${order.id}" class="btn btn-primary btn-sm btn-block">Więcej</a>
                            <c:if test="${order.status.id == 1}">
                                <a href="/orders/cancel/${order.id}"
                                   class="btn btn-danger btn-sm btn-block">Anuluj</a>
                            </c:if>
                            <c:if test="${order.status.id == 3}">
                                <a href="/orders/buy/${order.id}"
                                   class="btn btn-danger btn-sm btn-block">Zamów ponownie</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:set var="page" value="${orders}" scope="request"/>
            <c:set var="mainUrl" value="orders" scope="request"/>
            <c:import url="shared/pagination.jsp"/>

        </c:when>
        <c:otherwise>
            <h1>Brak zamówień</h1>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

<c:import url="shared/footer.jsp"/>
