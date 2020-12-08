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
  <fmt:bundle basename="messages.admins.rentals">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body  class="d-flex flex-column justify-content-between">
        <jsp:include page="../components/menu.jsp" />
        <div class="container" id="root">
          <h1 class="w-100 text-center"><fmt:message key="title"/></h1>
          <div id="toolbar">
            <a class="btn btn-primary" href="<%=contextPath%>/admins/rentals/register"><fmt:message key="new"/></a>
          </div>
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
                <th scope="col"><fmt:message key="name"/></th>
                <th scope="col">CNPJ</th>
                <th scope="col"><fmt:message key="city"/></th>
                <th scope="col"><fmt:message key="state"/></th>
                <th scope="col"><fmt:message key="actions"/></th>
              </tr>
            </thead>
            <c:forEach var="rental" items="${requestScope.rentalList}">
                <c:set var = "cnpj" value = "${fn:substring(rental.cnpj, 0, 2)}.${fn:substring(rental.cnpj, 2, 5)}.${fn:substring(rental.cnpj, 5, 8)}/${fn:substring(rental.cnpj, 8, 12)}-${fn:substring(rental.cnpj, 12, 14)}" />
                <tr>
                  <th scope="row">${rental.id}</th>
                  <td>${rental.name}</td>
                  <td>${cnpj}</td>
                  <td>${rental.city.name}</td>
                  <td>${rental.city.state}</td>
                  <td><a href="<%=contextPath%>/admins/rentals/edit?id=${rental.id}"><fmt:message key="edit"/></a>
                    <br/>
                    <a href="<%=contextPath%>/admins/rentals/delete?id=${rental.id}"><fmt:message key="remove"/></a>
                  </td>
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