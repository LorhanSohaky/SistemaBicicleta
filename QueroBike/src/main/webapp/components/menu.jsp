<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String contextPath = request.getContextPath();
%>
<fmt:bundle basename="messages.main.menu">
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
      <a class="navbar-brand" href="<%=contextPath%>/">
        <img src="<%=contextPath%>/assets/images/logo.svg" alt="Quero BIKE"/>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="<%=contextPath%>/rentals/"><fmt:message key="rentals"/></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><fmt:message key="whoWeAre"/></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><fmt:message key="contact"/></a>
          </li>
        </ul>
        <c:choose>
            <c:when test = "${not empty sessionScope.customerData}">
                <ul class="navbar-nav">
                  <li class="nav-item mr-2">
                    <a class="nav-link" href="<%=contextPath%>/customers/"><fmt:message key="profile"/></a>
                  </li>
                  <li class="nav-item">
                    <a class="btn btn-primary" href="<%=contextPath%>/customers/logout"><fmt:message key="logout"/></a>
                  </li>
                </ul>
            </c:when>
            <c:when test = "${not empty sessionScope.rentalData}">
                <ul class="navbar-nav">
                  <li class="nav-item mr-2">
                    <a class="nav-link" href="<%=contextPath%>/rentals/home"><fmt:message key="profile"/></a>
                  </li>
                  <li class="nav-item">
                    <a class="btn btn-primary" href="<%=contextPath%>/rentals/logout"><fmt:message key="logout"/></a>
                  </li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav">
                  <li class="nav-item mr-2">
                    <a class="nav-link" href="<%=contextPath%>/login.jsp"><fmt:message key="access"/></a>
                  </li>
                  <li class="nav-item">
                    <a class="btn btn-primary" href="<%=contextPath%>/customers/register"><fmt:message key="createAccount"/></a>
                  </li>
                </ul>
            </c:otherwise>
        </c:choose>

      </div>
    </nav>
</fmt:bundle>