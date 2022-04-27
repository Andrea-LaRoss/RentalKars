<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Lista utenti</h2>

<!-- SE l'utente è admin mostra il tasto-->
<a href="admin/manage_users.jsp"><strong>Aggiungi</strong></a> <br>

<table border="1">
    <tr>
        <th>Nome Cognome</th>
        <th>Data di nascita</th>
        <th>Email</th>
        <th>Ultima prenotazione</th>
    </tr>
    <c:forEach var="tempUser" items="${usersList}">
        <tr>
            <td>${tempUser.firstName} ${tempUser.lastName}</td>
            <td>${tempUser.birthday}</td>
            <td>${tempUser.email}</td>
            <td></td>
            <td><a href="">Modifica</a> | <a href="">Elimina</a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>