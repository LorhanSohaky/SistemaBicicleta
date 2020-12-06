<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String contextPath = request.getContextPath().toString();
%>
<!DOCTYPE html>
<fmt:bundle basename="messages.main.index">
    <html>
      <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title><fmt:message key="headerTitle"/></title>
      </head>
      <body>
        <jsp:include page="components/menu.jsp" />
        <div class="container-fluid" id="root">
          <section class="row position-relative">
            <h1 id="slide_title" class="position-absolute"><fmt:message key="slideFindYourBike"/></h1>
            <img src="<%=contextPath%>/assets/images/home/slide.jpg" alt="<fmt:message key="slideFindYourBike"/>" width="100%"/>
          </section>
          <section class="container mt-5">
            <h1><fmt:message key="benefits"/></h1>
            <div class="mt-3 row justify-content-center">
              <div class="card col-sm-12 col-md-5 mb-3 mr-md-3">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/build.svg" class="position-absolute"  alt="<fmt:message key="maintenance"/>"/>
                    <h5 class="text-center"><fmt:message key="maintenance"/></h5>
                  </div>
                  <p class="card-text text-justify"><fmt:message key="maintenanceDescription"/></p>
                </div>
              </div>
              <div class="card col-sm-12 col-md-5 mb-3 ">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/like.svg" class="position-absolute"  alt="<fmt:message key="withoutFidelity"/>"/>
                    <h5 class="text-center"><fmt:message key="withoutFidelity"/></h5>
                  </div>
                  <p class="card-text text-justify"><fmt:message key="withoutFidelityDescription"/></p>
                </div>
              </div>
              <div class="card col-sm-12 col-md-5 mb-3 mr-md-3">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/world.svg" class="position-absolute"  alt="<fmt:message key="nearToYou"/>"/>
                    <h5 class="text-center"><fmt:message key="nearToYou"/></h5>
                  </div>
                  <p class="card-text text-justify"><fmt:message key="nearToYouDescription"/></p>
                </div>
              </div>
              <div class="card col-sm-12 col-md-5 mb-3">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/build.svg" class="position-absolute"  alt="<fmt:message key="lowCost"/>"/>
                    <h5 class="text-center"><fmt:message key="lowCost"/></h5>
                  </div>
                  <p class="card-text text-justify"><fmt:message key="lowCostDescription"/></p>
                </div>
              </div>
            </div>
          </section>
          <section class="container mt-5">
            <h1><fmt:message key="howItWorks"/></h1>
            <div class="row mt-4">
              <div class="col-12 col-md-6 order-1">
                <h4><fmt:message key="selectRental"/></h4>
                <p class="text-justify"><fmt:message key="selectRentalDescription"/></p>
              </div>
              <div class="col-12 col-md-6 order-2 d-flex flex-row justify-content-center">
                <img src="<%=contextPath%>/assets/images/home/steps/options.svg" alt="<fmt:message key="selectRental"/>" class="w-75" />
              </div>
            </div>
            <div class="row mt-4">
              <div class="col-12 col-md-6 order-1 order-md-2">
                <h4><fmt:message key="chooseTheBestTime"/></h4>
                <p class="text-justify"><fmt:message key="chooseTheBestTimeDescription"/></p>
              </div>              
              <div class="col-12 col-md-6 order-2 order-md-1 d-flex flex-row justify-content-center">
                <img src="<%=contextPath%>/assets/images/home/steps/calendar.svg" alt="<fmt:message key="chooseTheBestTime"/>" class="w-75" />
              </div>
            </div>
            <div class="row mt-4">
              <div class="col-12 col-md-6 order-1">
                <h4><fmt:message key="turnAround"/></h4>
                <p class="text-justify"><fmt:message key="turnAroundDescription"/></p>
              </div>
              <div class="col-12 col-md-6 d-flex order-2 flex-row justify-content-center">
                <img src="<%=contextPath%>/assets/images/home/steps/bike.svg" alt="<fmt:message key="turnAround"/>" class="w-75" />
              </div>
            </div>
          </section>
        </div>
        <jsp:include page="components/footer.jsp" />
        <jsp:include page="components/imports.jsp" />
        <link rel="stylesheet" type="text/css" href="<%=contextPath%>/assets/css/pages/index.css" />
      </body>
    </html>
</fmt:bundle>