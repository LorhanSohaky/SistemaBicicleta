<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.rentals.login">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body class="d-flex flex-column justify-content-between">
        <jsp:include page="../components/menu.jsp" />
        <div class="container h-100 pt-sm-5 d-flex flex-column justify-content-center align-items-center" id="root">
          <div class="w-100">
            <form action="<%=contextPath%>/rentals/login" method="POST">
              <div class="row rounded-right"  style="box-shadow: 0px 4px 32px 8px rgba(0, 0, 0, 0.16);">
                <div class="bg-primary rounded-left col-12 col-md-4 py-3 px-3 d-inline-flex align-items-center justify-content-center">
                  <img src="<%=contextPath%>/assets/images/login/login.svg" alt="Login" width="200"/>
                </div>
                <div class="rounded-right col-12 col-md-8 py-3 px-4">
                  <h1 class="text-center w-100"><fmt:message key="title"/></h1>
                  <p class="h5 mt-3 text-center w-100"><fmt:message key="subTitle"/></p>
                  <c:if test="${errorList.notEmpty}">
                      <div class="alert alert-danger" role="alert">
                        <ul>
                          <c:forEach var="erro" items="${errorList.errors}">
                              <li>${erro}</li>
                          </c:forEach>
                        </ul>
                      </div>
                  </c:if>
                  <div class="mt-5 mb-2 mx-5">
                    <div class="form-group">
                      <label for="inputEmail"><fmt:message key="emailLabel"/></label>
                      <input type="email" name="email" maxlength="128" class="form-control" id="inputEmail" required placeholder="<fmt:message key='emailPlaceholder'/>" value="${data.email}">
                    </div>
                    <div class="form-group">
                      <label for="inputPassword"><fmt:message key="passwordLabel"/></label>
                      <input type="password" minlength="5" name="password" class="form-control" id="inputPassword" required placeholder="<fmt:message key='passwordPlaceholder'/>">
                    </div>
                    <div class="d-flex flex-column align-items-center">
                      <button type="submit" class="btn btn-primary"><fmt:message key='submitButton'/></button>
                    </div>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>

        <jsp:include page="../components/footer.jsp" />
        <jsp:include page="../components/imports.jsp" />
      </body>
    </html>
</fmt:bundle>