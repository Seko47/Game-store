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
    <c:param name="pageName" value="account"/>
</c:import>
<head>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
    <title>Title</title>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Ustawienia konta</h2>
        <c:if test="${!empty infoAlert}">
            <label class="alert-info">${infoAlert}</label>
        </c:if>

        <form:form modelAttribute="userCommand">
            <form:input path="id" cssClass="invisible disabled"/>
            <input id="username" name="username" type="password" value="$%#@" class="invisible disabled"/>
            <input id="password" name="password" type="password" value="1" class="invisible disabled"/>
            <input id="passwordConfirm" name="passwordConfirm" type="password" value="1" class="invisible disabled"/>
            <form:input path="email" cssClass="invisible disabled"/>

            <h4>Adres</h4>
            <div class="mb-3">
                <div class="row row-space mb-3">
                    <div class="col-md-8 mb-3">
                        <label for="city" class="required">Miasto</label><br>
                        <form:errors path="city" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="city" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="Miasto" required="true"/>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label for="postCode" class="required">Kod pocztowy</label><br>
                        <form:errors path="postCode" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="postCode" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="XX-XXX" required="true"/>
                        </div>
                    </div>
                </div>
                <div class="row row-space">
                    <div class="col-md-8 mb-3">
                        <label for="street" class="required">Ulica</label><br>
                        <form:errors path="street" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="street" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="Ulica" required="true"/>
                        </div>
                    </div>
                    <div class="col-md-2 mb-3">
                        <label for="houseNumber" class="required">Numer domu</label><br>
                        <form:errors path="houseNumber" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="houseNumber" cssClass="form-control"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="Nr" required="true"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label for="apartmentNumber">Numer lokalu</label><br>
                        <form:errors path="apartmentNumber" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="apartmentNumber" cssClass="form-control"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="Nr"/>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <h4>Dane osobowe</h4>
            <div class="mb-3">
                <div class="row row-space mb-3">
                    <div class="mb-3 col-md-4">
                        <label for="name" class="required">Imię</label><br>
                        <form:errors path="name" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="Imię" required="true"/>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="surname" class="required">Nazwisko</label><br>
                        <form:errors path="surname" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="surname" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                        placeholder="Nazwisko" required="true"/>
                        </div>
                    </div>
                    <div class="col-md-2 mb-3">
                        <label for="phoneNumber" class="required">Numer telefonu</label><br>
                        <form:errors path="phoneNumber" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="phoneNumber" cssClass="form-control"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="123456789" required="true"/>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <label for="creditCardNumber" class="required">Numer karty</label><br>
                        <form:errors path="creditCardNumber" cssClass="alert-danger"/>
                        <div class="input-group">
                            <form:input path="creditCardNumber" cssClass="form-control"
                                        cssErrorClass="form-control is-invalid"
                                        placeholder="5474539935749518" data-toggle="tooltip"
                                        title="5531196234413733
                                        5316882769357111"
                                        required="true"/>
                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-primary btn-lg btn-block">Zmień dane</button>
        </form:form>
    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
