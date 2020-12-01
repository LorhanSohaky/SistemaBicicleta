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
            <p>${sessionScope.adminData.name}</p>
            <p>${sessionScope.adminData.email}</p>
        </body>
    </fmt:bundle>
</html>