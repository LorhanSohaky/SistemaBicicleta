<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.customers.login">
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body class="d-flex flex-column justify-content-between">
        <jsp:include page="/components/menu.jsp" />
        <div class="container h-100 pt-sm-5 d-flex flex-column justify-content-center align-items-center" id="root">
          <div id="step1" class="w-100">
            <div class="row rounded-right"  style="box-shadow: 0px 4px 32px 8px rgba(0, 0, 0, 0.16);">
              <div class="bg-primary rounded-left col-12 col-md-5 py-3 px-3 d-inline-flex align-items-center justify-content-center">
                <img src="<%=contextPath%>/assets/images/login/options.svg" alt="Options" width="200"/>
              </div>
              <div class="rounded-right col-12 col-md-7 py-3 px-4">
                <h1 class="text-center w-100">Vamos começar!</h1>
                <p class="h5 mt-3 text-center w-100">Nos ajude a saber mais sobre você.<br/>Você é um cliente?</p>
                <div class="mt-5 mb-2 d-flex flex-column align-items-center">
                  <a class="btn btn-secondary mb-4" href="<%=contextPath%>/rentals/login">Não, sou uma locadora</a>
                  <a class="btn btn-primary" href="<%=contextPath%>/customers/login">Sim, sou um cliente</a>
                </div>
              </div>
            </div>
          </div>
        </div>

        <jsp:include page="/components/footer.jsp" />
        <jsp:include page="/components/imports.jsp" />
      </body>
    </html>
</fmt:bundle>