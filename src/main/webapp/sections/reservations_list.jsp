<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Listino auto</h2>
<h5>${errorMsg}</h5>

<!-- SE l'utente è admin mostra il tasto-->
<a href="user/manage_reservations.jsp"><strong>Nuova prenotazione</strong></a> <br>

<table border="1">
    <tr>
        <th>Cliente</th>
        <th>Auto</th>
        <th>Data Inizio</th>
        <th>Data Fine</th>
        <th></th>
    </tr>
    <c:forEach var="tempRent" items="${rentsList}">
        <c:url var="update" value="RentController">
            <c:param name="command" value="LOAD"/>
            <c:param name="rentId" value="${tempRent.id}"/>
        </c:url>
        <c:url var="delete" value="RentController">
            <c:param name="command" value="DELETE"/>
            <c:param name="rentId" value="${tempRent.id}"/>
        </c:url>
        <tr>
            <td></td>
            <td></td>
            <td>${tempRent.startDate}</td>
            <td>${tempRent.endDate}</td>
            <td><a href="${update}">Modifica</a> | <a href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>