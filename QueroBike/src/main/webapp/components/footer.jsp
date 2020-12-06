<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String contextPath = request.getContextPath().toString();
%>
<fmt:bundle basename="messages.main.footer">
<footer id="page_footer" class=" mt-5">
  <div class="container">
    <div class="container row">
      <div class="col-12 col-md-6 d-flex flex-column justify-content-center my-2">
        <p class="h5">QUERO BIKE - <fmt:message key="bikeRentals"/></p>
      </div>
      <div class="col-12 col-md-6 d-flex flex-column justify-content-center align-items-center">
        <ul class="list-inline text-center">
          <li class="list-inline-item"><img  src="<%=contextPath%>/assets/images/footer/facebook.svg" alt="Facebook" height="48" class="mt-2"/></li>
          <li class="list-inline-item"><img  src="<%=contextPath%>/assets/images/footer/linkedin.svg" alt="Linkedin" height="48" class="mt-2"/></li>
          <li class="list-inline-item"><img  src="<%=contextPath%>/assets/images/footer/youtube.svg" alt="Youtube" height="48" class="mt-2"/></li>
          <li class="list-inline-item"><img  src="<%=contextPath%>/assets/images/footer/instagram.svg" alt="Instagram" height="48" class="mt-2"/></li>
        </ul>
      </div>
    </div>
  </div>
</footer>
        </fmt:bundle>