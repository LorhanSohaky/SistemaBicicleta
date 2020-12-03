<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <fmt:bundle basename="messages">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Quero BIKE</title>
        </head>
        <body>
            <h1>Home</h1>
            <p>${sessionScope.customerData.id}</p>
            <p>${sessionScope.customerData.name}</p>
            <p>${sessionScope.customerData.cpf}</p>
            <p>${sessionScope.customerData.phone}</p>
            <p>${sessionScope.customerData.email}</p>
            <p>${sessionScope.customerData.password}</p>


        </body>
    </fmt:bundle>
</html>