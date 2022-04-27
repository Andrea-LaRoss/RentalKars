<jsp:include page="/template/header.jsp"/>

<h2>Lista utenti</h2>
<h3>+</h3>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Listino auto</h2>

<!-- SE l'utente è admin mostra il tasto-->
<a href="admin/manage_cars.jsp"><strong>Aggiungi</strong></a> <br>

<table border="1">
    <tr>
        <th>Nome Cognome</th>
        <th>Data di nascita</th>
        <th>Email</th>
        <th>Ruolo</th>
        <th>Ultima prenotazione</th>
    </tr>
    <c:forEach var="tempUser" items="${usersList}">
        <tr>
            <td>${tempUser.firstName} ${tempUser.lastName}</td>
            <td>${tempUser.birthday}</td>
            <td>${tempUser.email}</td>
            <td>${tempUser.isAdmin}</td>
            <td><a href="">Modifica</a> <a href="DeleteObj">Elimina</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>