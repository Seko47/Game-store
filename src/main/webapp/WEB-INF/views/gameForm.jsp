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
    <c:param name="pageName" value="gameForm"/>
</c:import>
<head>
    <title>Formularz gry</title>
    <script>
        var maxLength = ${maxShortDescriptionLength};

        function enforceMaxLength(obj) {
            if (obj.value.length > maxLength) {
                obj.value = obj.value.substring(0, maxLength);
            }
            document.getElementById("descriptionShortLabel").innerText = "Krótki opis " + obj.value.length + "/" + maxLength;
        }

        $("document").ready(function () {

            $('#cover').change(function () {
                var input = this;
                if (input.files && input.files[0]) {
                    $('#coverURIOn').css('display', 'inline');
                    var reader = new FileReader();

                    reader.onload = function (ev) {
                        $('#coverURIOn').attr('src', ev.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else {
                    $('#coverURIOn').css('display', 'none');
                }
            });
            $('#image1On').change(function () {
                var input = this;
                if (input.files && input.files[0]) {
                    $('#image1URI').css('display', 'inline');
                    var reader = new FileReader();

                    reader.onload = function (ev) {
                        $('#image1URI').attr('src', ev.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else {
                    $('#image1URI').css('display', 'none');
                }
            });
            $('#image2On').change(function () {
                var input = this;
                if (input.files && input.files[0]) {
                    $('#image2URI').css('display', 'inline');
                    var reader = new FileReader();

                    reader.onload = function (ev) {
                        $('#image2URI').attr('src', ev.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else {
                    $('#image2URI').css('display', 'none');
                }
            });
            $('#image3On').change(function () {
                var input = this;
                if (input.files && input.files[0]) {
                    $('#image3URI').css('display', 'inline');
                    var reader = new FileReader();

                    reader.onload = function (ev) {
                        $('#image3URI').attr('src', ev.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else {
                    $('#image3URI').css('display', 'none');
                }
            });
        });
    </script>
</head>
<body>
<div class="container text-center">
    <div class="order-md-1">
        <h2 class="mb-3">Formularz gry</h2>

        <form:form modelAttribute="game" action="/employeePanel/gameManagement/form" enctype="multipart/form-data">
            <h4>Informacje podstawowe</h4>
            <form:input path="id" cssClass="invisible disabled"/>
            <div class="mb-3">
                <label for="name" class="required">Nazwa</label><br>
                <form:errors path="name" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Nazwa gry" required="true"/>
                </div>
            </div>
            <div class="mb-3">
                <label id="descriptionShortLabel" for="descriptionShort" class="required">Krótki opis</label><br>
                <form:errors path="descriptionShort" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:textarea onkeyup="enforceMaxLength(this)" rows="5" path="descriptionShort"
                                   cssClass="form-control"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Krótki opis" required="true"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="descriptionLong" class="required">Pozostały opis</label><br>
                <form:errors path="descriptionLong" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:textarea rows="15" path="descriptionLong" cssClass="form-control"
                                   cssErrorClass="form-control is-invalid"
                                   placeholder="Pozostały opis (inny niż krótki)" required="true"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="price" class="required">Cena</label><br>
                <form:errors path="price" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:input path="price" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Cena gry" required="true"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="publisher" class="required">Wydawca</label><br>
                <form:errors path="publisher" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:input path="publisher" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Nazwa wydawcy" required="true"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="producer" class="required">Producent</label><br>
                <form:errors path="producer" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:input path="producer" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Nazwa producenta" required="true"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="releaseDate" class="required">Data wydania</label><br>
                <form:errors path="releaseDate" cssClass="alert-danger"/>
                <div class="input-group">
                    <form:input path="releaseDate" cssClass="form-control" cssErrorClass="form-control is-invalid"
                                placeholder="Data wydania" required="true" type="date"/>
                </div>
            </div>
            <div class="mb-3">
                <label for="gameCategories">Kategorie gry</label><br>
                <form:errors path="gameCategories" cssClass="alert-danger"/>
                <div class="list-group">
                    <form:checkboxes path="gameCategories" items="${gameCategories}"
                                     element="div class='form-check' style='left:25px;'" cssClass="form-check-input"
                                     itemLabel="name" itemValue="id"/>
                </div>
            </div>
            <hr>
            <h4>Zdjęcia <c:if
                    test="${!empty game.id}">(nie wybieraj jeśli nie chcesz zmieniać)</c:if></h4>
            <div class="mb-3">
                <label>Okładka gry</label><br>
                <form:input path="coverURI" cssClass="invisible disabled"/>
                <img id="coverURIOn" src="${game.coverURI}"
                     style="height: 100px"
                     onerror="this.style.display='none'"/>
                <input id="cover" type="file" name="files" accept="image/*"/><br>
            </div>
            <div class="mb-3">
                <label>Pozostałe zdjęcia</label><br>
                <form:input path="image1" cssClass="invisible disabled"/>
                <img id="image1URI" src="${game.image1}"
                     style="height: 100px"
                     onerror="this.style.display='none'"/>
                <input id="image1On" type="file" name="files" accept="image/*"/>
            </div>
            <div class="mb-3">
                <form:input path="image2" cssClass="invisible disabled"/>
                <img id="image2URI" src="${game.image2}"
                     style="height: 100px"
                     onerror="this.style.display='none'"/>
                <input id="image2On" type="file" name="files" accept="image/*"/>
            </div>
            <div class="mb-3">
                <form:input path="image3" cssClass="invisible disabled"/>
                <img id="image3URI" src="${game.image3}"
                     style="height: 100px"
                     onerror="this.style.display='none'"/>
                <input id="image3On" type="file" name="files" accept="image/*"/>
            </div>

            <c:if test="${empty game.name}">
                <div class="mb-3">
                    <label>Ilość gier (domyślnie 0)</label><br>
                    <div class="input-group">
                        <input name="quantity" class="mb-3 form-control" placeholder="0 sztuk" type="number" min="0"/>
                    </div>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary btn-lg btn-block">Zapisz</button>
        </form:form>

    </div>
</div>
</body>
<c:import url="shared/footer.jsp"/>
</html>
