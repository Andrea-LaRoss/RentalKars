<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<div class="container">
    <h1>La tua dashboard</h1>
    <br><br>

    <c:url var="profile" value="UserServlet">
        <c:param name="command" value="LOAD"/>
        <c:param name="userId" value="${tempUser.id}"/>
    </c:url>

    <c:url var="reservations" value="RentServlet">
        <c:param name="command" value="LIST"/>
        <c:param name="userId" value="${tempUser.id}"/>
    </c:url>

    <a class="btn btn-primary mb-3" href="${reservations}">Vai alla lista delle tue prenotazioni</a> <br>
    <a class="btn btn-primary" href="${profile}">Modifica il tuo profilo</a> <br>
</div>
<jsp:include page="/template/footer.jsp"/>
