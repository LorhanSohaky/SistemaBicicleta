<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.main.rentals">
  <html>
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
      <title><fmt:message key="headerTitle"/></title>
    </head>
    <body>
      <jsp:include page="../components/menu.jsp" />
      <div class="container" id="root">
        <section class="container">
          <h1><fmt:message key="rentals"/></h1>
          <div class="mt-3 row justify-content-center">
            <c:forEach var="rental" items="${requestScope.rentalList}">
              <div class="card col-12 col-sm-5 col-md-3 mb-3 mr-sm-3 pt-3">
                <img class="card-img-top" src="<%=contextPath%>/assets/images/icons/default.svg" alt="${rental.name}" height="128" />
                <div class="card-body">
                  <h5 class="card-title text-center">${rental.name}</h5>
                  <p class="card-text text-justify">${renta.description}</p>
                </div>
              </div>
            </c:forEach>
          </div>
          <table>

          </table>
        </section>
      </div>
      <jsp:include page="../components/footer.jsp" />
      <jsp:include page="../components/imports.jsp" />
      <link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/css/pages/index.css" />
    </body>
  </html>
</fmt:bundle>