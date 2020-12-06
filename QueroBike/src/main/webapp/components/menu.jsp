<%
    String contextPath = request.getContextPath();
%>

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
        <a class="nav-link" href="#">Locadoras</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Quem somos</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Contato</a>
      </li>
    </ul>

    <ul class="navbar-nav">
      <li class="nav-item mr-2">
        <a class="nav-link" href="#">ACESSAR</a>
      </li>
      <li class="nav-item">
        <button class="btn btn-primary" href="#">CRIAR CONTA</button>
      </li>
    </ul>
  </div>
</nav>