<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%
    String contextPath = request.getContextPath().toString();
%>

<%
    String id = request.getParameter("id");
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.customers.rent">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body>
        
        <jsp:include page="../components/menu.jsp" />
        <div class="container"  id="root">
          <h1><fmt:message key="selectDay"/></h1>
          <c:if test="${errorList.notEmpty}">
              <div class="alert alert-danger" role="alert">
                <ul>
                  <c:forEach var="erro" items="${errorList.errors}">
                      <li>${erro}</li>
                  </c:forEach>
                </ul>
              </div>
          </c:if>
          <form>
            <div class="form-group">
              <label for="inputBirthdate"><fmt:message key="selectHour"/></label>
              <input type="date" name="date" class="form-control" id="inputDate" required value="${data.birthdate}"/>
            </div>
               <button type="submit" class="btn btn-primary"><fmt:message key='submitButton'/></button>
          </form>
          <!--<p> <%=id%> </p>-->
          
            <h1 class="w-100 text-center"><fmt:message key="selectHour"/></h1>
            <table class="table table-striped table-hover"  id="table"
                   data-toggle="table"
                   data-flat="true"
                   data-toolbar="#toolbar"
                   data-locale="${localeCode}"
                   >
              </thead>
              <c:choose>
                  <c:when test = "${not empty sessionScope.customerData}">
                        <c:forEach var="rental" items="${requestScope.rentalList}">
                            <c:set var = "cnpj" value = "${fn:substring(rental.cnpj, 0, 2)}.${fn:substring(rental.cnpj, 2, 5)}.${fn:substring(rental.cnpj, 5, 8)}/${fn:substring(rental.cnpj, 8, 12)}-${fn:substring(rental.cnpj, 12, 14)}" />
                            <tr>
                              <td > <a href="<%=contextPath%>/customers/rent.jsp?id=${rental.id}">${rental.name}</a> </td>
                              <td> ${rental.city.name}</td>
                              <td>${rental.city.state}</td>
                              </td>
                            </tr>
                        </c:forEach>
                  </c:when>
  
                  <c:otherwise>
                    <c:forEach var="rental" items="${requestScope.rentalList}">
                            <c:set var = "cnpj" value = "${fn:substring(rental.cnpj, 0, 2)}.${fn:substring(rental.cnpj, 2, 5)}.${fn:substring(rental.cnpj, 5, 8)}/${fn:substring(rental.cnpj, 8, 12)}-${fn:substring(rental.cnpj, 12, 14)}" />
                            <tr>
                              <td>${rental.name}</td>
                              <td>${rental.city.name}</td>
                              <td>${rental.city.state}</td>
                              </td>
                            </tr>
                    </c:forEach>
                  </c:otherwise>
  
              </c:choose>
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