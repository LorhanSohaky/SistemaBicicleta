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
  <c:set var="localeCode" value="${fn:replace(pageContext.request.locale, '_', '-')}" />
  <fmt:bundle basename="messages.rentals.home">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body  class="d-flex flex-column justify-content-between">
        <jsp:include page="../components/menu.jsp" />
        <div class="container" id="root">
          <h1><fmt:message key="title"/></h1>
          <table class="table table-striped table-hover"  data-show-columns="true" id="table"
                 data-toggle="table"
                 data-flat="true"
                 data-search="true"
                 data-toolbar="#toolbar"
                 data-locale="${localeCode}"
                 >
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">CPF</th>
                <th scope="col"><fmt:message key="customer"/></th>
                <th scope="col"><fmt:message key="date"/></th>
              </tr>
            </thead>
            <c:forEach var="reserve" items="${requestScope.reservesList}">
                <c:set var = "cpf" value = "${fn:substring(reserve.customer.cpf, 0, 3)}.${fn:substring(reserve.customer.cpf, 3, 6)}.${fn:substring(reserve.customer.cpf, 6, 9)}-${fn:substring(reserve.customer.cpf, 9, 11)}" />
                <tr>
                  <th scope="row">${reserve.id}</th>
                  <td>${cpf}</td>
                  <td>${reserve.customer.name}</td>
                  <td><fmt:formatDate type = "both" 
         dateStyle = "short" timeStyle = "short" value = "${reserve.moment}" /></td>
                </tr>
            </c:forEach>
          </table>
        </div>
        <jsp:include page="../components/footer.jsp" />
        <jsp:include page="../components/imports.jsp" />
        <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.18.1/dist/bootstrap-table.min.css">
        <script src="https://unpkg.com/bootstrap-table@1.18.1/dist/bootstrap-table.min.js"></script>
        <script src="https://unpkg.com/bootstrap-table@1.18.1/dist/locale/bootstrap-table-en-US.js"></script>
        <script src="https://unpkg.com/bootstrap-table@1.18.1/dist/locale/bootstrap-table-pt-BR.js"></script>
      </body>
  </fmt:bundle>
</html>