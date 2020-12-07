<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

      <ul class="navbar-nav">
        <li class="nav-item mr-2">
          <a class="nav-link" href="#"><fmt:message key="access"/></a>
        </li>
        <li class="nav-item">
          <button class="btn btn-primary" href="#"><fmt:message key="createAccount"/></button>
        </li>
      </ul>
    </div>
  </nav>
</fmt:bundle>