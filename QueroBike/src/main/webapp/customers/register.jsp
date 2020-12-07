<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.customers.register">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body>
        <jsp:include page="../components/menu.jsp" />
        <div class="container"  id="root">
          <h1><fmt:message key="title"/></h1>
          <c:if test="${errorList.notEmpty}">
              <div class="alert alert-danger" role="alert">
                <ul>
                  <c:forEach var="erro" items="${errorList.errors}">
                      <li>${erro}</li>
                  </c:forEach>
                </ul>
              </div>
          </c:if>
          <form action="<%=contextPath%>/customers/register" method="POST">
            <div class="form-group">
              <label for="inputName"><fmt:message key="nameLabel"/></label>
              <input type="text" name="name" maxlength="128" class="form-control" id="inputName" required placeholder="<fmt:message key='namePlaceholder'/>" value="${data.name}">
            </div>
            <div class="form-group">
              <label for="inputCPF"><fmt:message key="cpfLabel"/></label>
              <input type="text" name="cpf" maxlength="14" class="form-control" id="inputCPF" required placeholder="<fmt:message key='cpfPlaceholder'/>" value="${data.cpf}">
            </div>
            <div class="form-group">
              <label for="inputEmail"><fmt:message key="emailLabel"/></label>
              <input type="email" name="email" maxlength="128" class="form-control" id="inputEmail" required placeholder="<fmt:message key='emailPlaceholder'/>" value="${data.email}">
            </div>
            <div class="form-group">
              <label for="inputPhone"><fmt:message key="phoneLabel"/></label>
              <input type="text" name="phone" maxlength="15" class="form-control" id="inputPhone" required placeholder="<fmt:message key='phonePlaceholder'/>" value="${data.phone}">
            </div>
            <div class="form-group">
              <label for="inputGeneder"><fmt:message key="genderLabel"/></label>
              <input type="text" name="gender" maxlength="64" class="form-control" id="inputGeneder" required placeholder="<fmt:message key='genderPlaceholder'/>" value="${data.gender}">
            </div>
            <div class="form-group">
              <label for="inputPassword"><fmt:message key="passwordLabel"/></label>
              <input type="password" minlength="5" name="password" class="form-control" id="inputPassword" required placeholder="<fmt:message key='passwordPlaceholder'/>">
            </div>
            <div class="form-group">
              <label for="inputBirthdate"><fmt:message key="birthdateLabel"/></label>
              <input type="date" name="birthdate" class="form-control" id="inputBirthdate" required  value="${data.birthdate}">
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key='submitButton'/></button>

          </form>
        </div>
        <jsp:include page="../components/footer.jsp" />
        <jsp:include page="../components/imports.jsp" />
      </body>
    </html>
</fmt:bundle>