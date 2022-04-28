<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>
<h1 align="center">La tua dashboard</h1>
<br><br>

<c:url var="profile" value="UserController">
    <c:param name="command" value="LOAD"/>
    <c:param name="userId" value="${tempUser.id}"/>
</c:url>

<c:url var="reservations" value="RentController">
    <c:param name="command" value="LOAD"/>
    <c:param name="userId" value="${tempUser.id}"/>
</c:url>

<a href="${reservations}">Vai alla lista delle tue prenotazioni</a> <br>
<a href="${profile}">Modifica il tuo profilo</a> <br>
<%= request.getSession()%>
</body>
</html>