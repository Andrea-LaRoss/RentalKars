<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <title>Rental Kars</title>
    <script>var base = document.getElementsByTagName("base")[0].href;</script>
</head>
<body>

<a href="ToHome">Homepage</a>|
<a href="sections/cars_list.jsp">Parco Auto</a>

<!-- SE l'utente è admin account va alla dashboard admin, sennò va alla dashboard utente-->
<c:if test="${account}">
    |<a href="user/dashboard.jsp">Account</a>
    <c:if test="${account.isAdmin}">
        |<a href="admin/dashboard.jsp">Admin</a>
    </c:if>
</c:if>
<hr>
<br>