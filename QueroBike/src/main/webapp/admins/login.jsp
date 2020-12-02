<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <fmt:bundle basename="messages.admins.login">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="headerTitle"/></title>
        </head>

        <body>
            <div class="container">
                <h1><fmt:message key="title"/></h1>
                <c:if test="${errorList.notEmpty}">
                    <div class="erro">
                        <ul>
                            <c:forEach var="erro" items="${errorList.errors}">
                                <li> ${erro} </li>
                                </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <form action="<%=contextPath%>/admins/login" method="POST">
                    <div class="form-group">
                        <label for="inputEmail"><fmt:message key="emailLabel"/></label>
                        <input type="email" name="email" maxlength="128" class="form-control" id="inputEmail" required placeholder="<fmt:message key='emailPlaceholder'/>" value="${data.email}">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword"><fmt:message key="passwordLabel"/></label>
                        <input type="password" minlength="5" name="password" class="form-control" id="inputPassword" required placeholder="<fmt:message key='passwordPlaceholder'/>">
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="submitButton"/></button>

                </form>
            </div>

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
        </body>
    </fmt:bundle>

</html>