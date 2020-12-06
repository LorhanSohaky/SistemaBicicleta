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
            <h1 id="slide_title" class="position-absolute">Encontre a<br/>sua bike</h1>
            <img src="<%=contextPath%>/assets/images/home/slide.jpg" alt="Encontre a sua bike!" width="100%"/>
          </section>
          <section class="container mt-5">
            <h1 >Benefícios para você!</h1>
            <div class="mt-3 row justify-content-center">
              <div class="card col-sm-12 col-md-5 mb-3 mr-md-3">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/build.svg" class="position-absolute"  alt="Manutenção"/>
                    <h5 class="text-center">Manutenção</h5>
                  </div>
                  <p class="card-text text-justify">Temos um controle de qualidade muito forte que se preocupa bastante com em ter em nossa plataforma apenas locadoras que também se preocupam com a sua segurança.</p>
                </div>
              </div>
              <div class="card col-sm-12 col-md-5 mb-3 ">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/like.svg" class="position-absolute"  alt="Sem fidelidade"/>
                    <h5 class="text-center">Sem fidelidade</h5>
                  </div>
                  <p class="card-text text-justify">Você não fica preso ao nosso sistema. Sabemos que em alguns momentos as contas ficam pesadas, então pague enquanto utilizar o sistema.</p>
                </div>
              </div>
              <div class="card col-sm-12 col-md-5 mb-3 mr-md-3">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/world.svg" class="position-absolute"  alt="Perto de você"/>
                    <h5 class="text-center">Perto de você</h5>
                  </div>
                  <p class="card-text text-justify">Presente nas principais cidades do Brasil para sempre te atender melhor. Pode utilizar as bibicletas para todos os momentos da sua vida, desde lazer até para ir ao trabalho.</p>
                </div>
              </div>
              <div class="card col-sm-12 col-md-5 mb-3">
                <div class="card-body">
                  <div class="card-title position-relative">
                    <img src="<%=contextPath%>/assets/images/home/icons/build.svg" class="position-absolute"  alt="Baixo custo"/>
                    <h5 class="text-center">Baixo custo</h5>
                  </div>
                  <p class="card-text text-justify">Negociamos os preços com as locadoras para trazer um preço camarada para que você possa usar as bici cletas todos os dias!</p>
                </div>
              </div>
            </div>
          </section>
          <section class="container mt-5">
            <h1>Como funciona?</h1>
            <div class="row mt-4">
              <div class="col-12 col-md-6 order-1">
                <h4>1 - Selecione uma locadora</h4>
                <p class="text-justify">Temos mais de X locadoras disponíveis em todo o Brasil para que você possa pedir a sua bicicleta onde quiser!</p>
              </div>
              <div class="col-12 col-md-6 order-2 d-flex flex-row justify-content-center">
                <img src="<%=contextPath%>/assets/images/home/steps/options.svg" alt="Escolhas" class="w-75" />
              </div>
            </div>
            <div class="row mt-4">
              <div class="col-12 col-md-6 order-1 order-md-2">
                <h4>2 - Escolha o melhor horário</h4>
                <p class="text-justify">Você tem diversas opções de horários para atender melhor a sua necessidade!</p>
              </div>              
              <div class="col-12 col-md-6 order-2 order-md-1 d-flex flex-row justify-content-center">
                <img src="<%=contextPath%>/assets/images/home/steps/calendar.svg" alt="Calendário" class="w-75" />
              </div>
            </div>
            <div class="row mt-4">
              <div class="col-12 col-md-6 order-1">
                <h4>3 - Agora é só dar a sua volta!</h4>
                <p class="text-justify">  Pronto, rápido e fácil de agendar o seu horário!</p>
              </div>
              <div class="col-12 col-md-6 d-flex order-2 flex-row justify-content-center">
                <img src="<%=contextPath%>/assets/images/home/steps/bike.svg" alt="Bicicleta" class="w-75" />
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