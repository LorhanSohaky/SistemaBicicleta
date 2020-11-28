<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <fmt:bundle basename="messages">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Quero BIKE</title>
        </head>
        <body>
            <%
		String contextPath = request.getContextPath().replace("/", "");
	%>
            <a href="/<%=contextPath%>/cities"><fmt:message key="crudCities"/></a><br/>
            <a href="/<%=contextPath%>/rentals"><fmt:message key="crudRentals"/></a><br/>
        </body>
    </fmt:bundle>
</html>