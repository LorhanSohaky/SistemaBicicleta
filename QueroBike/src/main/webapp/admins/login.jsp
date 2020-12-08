<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
  <fmt:bundle basename="messages.admins.login">

      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>

      <body>
        <div class="container">
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
          <form action="<%=contextPath%>/admins/login" method="POST">
            <div class="form-group">
              <label for="inputEmail"><fmt:message key="emailLabel"/></label>
              <input type="email" name="email" maxlength="128" class="form-control" id="inputEmail" required placeholder="<fmt:message key='emailPlaceholder'/>" value="${data.email}">
            </div>
            <div class="form-group">
              <label for="inputPassword"><fmt:message key="passwordLabel"/></label>
              <input type="password" minlength="5" name="password" class="form-control" id="inputPassword" required placeholder="<fmt:message key='passwordPlaceholder'/>">
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="submitButton"/></button>

          </form>
        </div>

        <jsp:include page="../components/imports.jsp"/>
      </body>
  </fmt:bundle>

</html>