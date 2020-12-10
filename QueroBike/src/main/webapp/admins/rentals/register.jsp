<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<%
    String contextPath = request.getContextPath();
%>

<html>
  <fmt:bundle basename="messages.admins.rental_register">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body  class="d-flex flex-column justify-content-between">
        <jsp:include page="../../components/menu.jsp" />
        <div class="container" id="root">
          <h1 class="w-100 text-center"><fmt:message key="title"/></h1>
          <c:if test="${errorList.notEmpty}">
              <div class="alert alert-danger" role="alert">
                <ul>
                  <c:forEach var="erro" items="${errorList.errors}">
                      <li>${erro}</li>
                      </c:forEach>
                </ul>
              </div>
          </c:if>
          <form action="<%=contextPath%>/admins/rentals/register" method="POST">
            <input type="hidden" value="${rental.id}" name="id"/>
            <div class="form-group">
              <label for="inputName"><fmt:message key="nameLabel"/></label>
              <input type="text" name="name" maxlength="128" class="form-control" id="inputName" required value="${rental.name}">
            </div>
            <div class="form-group">
              <label for="inputCNPJ">CNPJ</label>
              <input type="text" name="cnpj" maxlength="14" class="form-control" id="inputCNPJ" required value="${rental.cnpj}">
            </div>
            <div class="form-group">
              <label for="inputEmail"><fmt:message key="emailLabel"/></label>
              <input type="email" name="email" maxlength="128" class="form-control" id="inputEmail" required value="${rental.email}">
            </div>
            <div class="form-group">
              <label for="inputPassword"><fmt:message key="passwordLabel"/></label>
              <input type="password" minlength="5" name="password" class="form-control" id="inputPassword" required>
            </div>
            <div class="form-group">
              <label for="inputDescription"><fmt:message key="descriptionLabel"/></label>
              <input type="text" name="description" class="form-control" id="inputDescription" value="${rental.description}">
            </div>
            <div class="form-group">
              <label for="inputPostalCode"><fmt:message key="postalCodeLabel"/></label>
              <input type="text" name="postalCode" maxlength="8" class="form-control" id="inputPostalCode" value="${rental.postalCode}" required>
            </div>
            <div class="form-group">
              <label for="inputStreetName"><fmt:message key="streetNameLabel"/></label>
              <input type="text" name="streetName" class="form-control" id="inputStreetName" value="${rental.streetName}" required>
            </div>
            <div class="form-group">
              <label for="inputNeighborhood"><fmt:message key="neighborhoodLabel"/></label>
              <input type="text" name="neighborhood" class="form-control" id="inputNeighborhood" value="${rental.neighborhood}" required>
            </div>
            <div class="form-group">
              <label for="inputStreetNumber"><fmt:message key="streetNumberLabel"/></label>
              <input type="text" name="streetNumber" class="form-control" id="inputStreetNumber" value="${rental.streetNumber}" required>
            </div>
            <div class="form-group">
              <label for="inputCity"><fmt:message key="cityLabel"/></label>
              <input type="text" name="city" class="form-control" id="inputCity" value="${rental.city}" required>
            </div>
            <div class="form-group">
              <label for="inputState"><fmt:message key="stateLabel"/></label>
              <input type="text" name="state" class="form-control" id="inputState" value="${rental.state}" required>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="submitButton"/></button>

          </form>
        </div>

        <jsp:include page="../../components/footer.jsp" />
        <jsp:include page="../../components/imports.jsp" />
      </body>
  </fmt:bundle>
</html>