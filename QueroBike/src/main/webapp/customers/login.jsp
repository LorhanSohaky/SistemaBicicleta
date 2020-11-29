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
            <table>
                <c:forEach var="error" items="${requestScope.errors}">
                    <tr>
                        <td>${error}</td>
                    </tr>
                </c:forEach>
            </table>
        </body>
    </fmt:bundle>
</html>