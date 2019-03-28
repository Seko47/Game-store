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
    <c:param name="pageName" value="accountManagement"/>
</c:import>

<html>
<head>
    <title>Zarządzanie kontami</title>
</head>
<body>

<div class="container text-center">

    <form:form id="searchForm" modelAttribute="searchUser">
        <div class="mb-3">
            <label for="phrase">Szukaj</label>
            <form:errors path="phrase" cssClass="error text-danger" element="div"/>
            <div class="input-group">
                <form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-lg btn-block">Szukaj</button>
    </form:form>

    <c:choose>
        <c:when test="${!empty userCommand.content}">
            <table class="table table-hover table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Login</th>
                    <th scope="col">Imię i nazwisko</th>
                    <th scope="col">Adres</th>
                    <th scope="col">Email</th>
                    <th scope="col">Telefon</th>
                    <th scope="col">Status</th>
                    <th scope="col">Role</th>
                    <security:authorize access="hasRole('ADMIN')">
                        <th scope="col" class="text-center" colspan="2">Opcje</th>
                    </security:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${userCommand.content}" var="user">
                    <tr>
                        <th scope="row">
                                ${user.id}
                        </th>
                        <td>
                                ${user.username}
                        </td>
                        <td>
                                ${user.name} ${user.surname}
                        </td>
                        <td>
                                ${user.postCode}, ${user.city}, ${user.street} ${user.houseNumber}
                            <c:if test="${!empty user.apartmentNumber}">/ ${user.apartmentNumber}</c:if>
                        </td>
                        <td>
                                ${user.email}
                        </td>
                        <td>
                                ${user.phoneNumber}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.enabled}">Aktywne</c:when>
                                <c:otherwise>Nieaktywne</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:set var="isEmployee" value="false"/>
                            <c:set var="isUser" value="false"/>
                            <c:set var="isAdmin" value="false"/>
                            <c:forEach items="${user.roles}" var="userRole">
                                <c:if test="${isEmployee || isUser || isAdmin}"><br/></c:if>
                                <c:choose>
                                    <c:when test="${userRole.type == 'ROLE_EMPLOYEE'}">
                                        <c:set var="isEmployee" value="true"/>
                                    </c:when>
                                    <c:when test="${userRole.type == 'ROLE_USER'}">
                                        <c:set var="isUser" value="true"/>
                                    </c:when>
                                    <c:when test="${userRole.type == 'ROLE_ADMIN'}">
                                        <c:set var="isAdmin" value="true"/>
                                    </c:when>
                                </c:choose>
                                ${userRole.type}
                            </c:forEach>
                        </td>
                        <security:authorize access="hasRole('ADMIN')">
                            <c:if test="${!isAdmin}">
                                <c:if test="${isUser && !isEmployee}">
                                    <td>
                                        <a href="/adminPanel/accountManagement/hire/${user.id}"
                                           class="btn btn-warning btn-sm btn-block">Zatrudnij</a>
                                    </td>
                                </c:if>
                                <c:if test="${isEmployee}">
                                    <td>
                                        <a href="/adminPanel/accountManagement/fire/${user.id}"
                                           class="btn btn-warning btn-sm btn-block">Zwolnij</a>
                                    </td>
                                </c:if>
                            </c:if>
                        </security:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <c:set var="page" value="${userCommand}" scope="request"/>
            <c:set var="mainUrl" value="/adminPanel/accountManagement" scope="request"/>
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
