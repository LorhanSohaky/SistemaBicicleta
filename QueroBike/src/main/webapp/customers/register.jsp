<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String basePath = request.getRequestURL().toString();
%>
<!DOCTYPE html>
<html>
    <fmt:bundle basename="messages">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Quero BIKE - Cadastro do cliente</title>

            <!-- CSS -->
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
                  integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

            <!-- jQuery and JS bundle w/ Popper.js -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                    integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>
        </head>

        <body>
            <div class="container">
                <h1>Cadastro do cliente</h1>
                <c:if test="${errorList.notEmpty}">
                    <div class="erro">
                        <ul>
                            <c:forEach var="erro" items="${errorList.errors}">
                                <li> ${erro} </li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <form action="/customers/register" method="POST">
                    <div class="form-group">
                        <label for="inputName">Nome</label>
                        <input type="text" name="name" maxlength="128" class="form-control" id="inputName" required placeholder="Digite seu nome aqui" value="${data.name}">
                    </div>
                    <div class="form-group">
                        <label for="inputCPF">CPF</label>
                        <input type="text" name="cpf" maxlength="14" class="form-control" id="inputCPF" required placeholder="Digite seu CPF" value="${data.cpf}">
                    </div>
                    <div class="form-group">
                        <label for="inputEmail">E-mail</label>
                        <input type="email" name="email" maxlength="128" class="form-control" id="inputEmail" required placeholder="Digite seu e-mail aqui" value="${data.email}">
                    </div>
                    <div class="form-group">
                        <label for="inputPhone">Celular</label>
                        <input type="text" name="phone" maxlength="15" class="form-control" id="inputPhone" required placeholder="Digite seu celular no formato 55DDDNumero" value="${data.phone}">
                    </div>
                    <div class="form-group">
                        <label for="inputGeneder">Gênero</label>
                        <input type="text" name="gender" maxlength="64" class="form-control" id="inputGeneder" required placeholder="Informe seu gênero" value="${data.gender}">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">Senha</label>
                        <input type="password" minlength="5" name="password" class="form-control" id="inputPassword" required placeholder="Digite aqui a sua senha">
                    </div>
                    <div class="form-group">
                        <label for="inputBirthdate">Data de nascimento</label>
                        <input type="date" name="birthdate" class="form-control" id="inputBirthdate" required  value="${data.birthdate}">
                    </div>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>

                </form>
            </div>

        </body>
    </fmt:bundle>

</html>