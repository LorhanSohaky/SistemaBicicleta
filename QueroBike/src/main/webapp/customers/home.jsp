<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <fmt:bundle basename="messages.customers.home">
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body  class="d-flex flex-column justify-content-between">
        <jsp:include page="../components/menu.jsp" />
        <div class="container" id="root">
          <h1>Home</h1>
          <p>${sessionScope.customerData.id}</p>
          <p>${sessionScope.customerData.name}</p>
        </div>
        <jsp:include page="../components/footer.jsp" />
        <jsp:include page="../components/imports.jsp" />
      </body>
  </fmt:bundle>
</html>