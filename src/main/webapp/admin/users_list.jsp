<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Lista utenti</h2>

<!-- SE l'utente è admin mostra il tasto-->
<form action="UserController">
    <input type="hidden" name="command" value="SEARCH">
    <select name="input">
        <option value="firstName">Nome</option>
        <option value="lastName">Cognome</option>
        <option value="email">Email</option>
    </select>
    <input type="text" name="toSearch" placeholder="Inserisci un nome">
    <input type="submit" value="cerca">
</form>   |
<a href="admin/manage_users.jsp"><strong>Aggiungi</strong></a> <br>


<table border="1">
    <tr>
        <th>Nome</th>
        <th>Data di nascita</th>
        <th>Email</th>
        <th>Ultima prenotazione</th>
    </tr>
    <c:forEach var="tempUser" items="${usersList}">
        <c:url var="update" value="UserController">
            <c:param name="command" value="LOAD"/>
            <c:param name="userId" value="${tempUser.id}"/>
        </c:url>
        <c:url var="delete" value="UserController">
            <c:param name="command" value="DELETE"/>
            <c:param name="userId" value="${tempUser.id}"/>
        </c:url>
        <tr>
            <td>${tempUser.firstName} ${tempUser.lastName}</td>
            <td>${tempUser.birthday}</td>
            <td>${tempUser.email}</td>
            <td></td>
            <td><a href="${update}">Modifica</a> | <a href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>