<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.rentals.home">
    <html>
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body>
        <jsp:include page="../components/menu.jsp" />
        <div class="container" id="root">
          <p>${sessionScope.rentalData.id}</p>
          <p>${sessionScope.rentalData.name}</p>
          <p>${sessionScope.rentalData.cnpj}</p>
          <p>${sessionScope.rentalData.email}</p>
        </div>
        <jsp:include page="../components/footer.jsp" />
        <jsp:include page="../components/imports.jsp" />
        <link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/css/pages/index.css" />
      </body>
    </html>
</fmt:bundle>