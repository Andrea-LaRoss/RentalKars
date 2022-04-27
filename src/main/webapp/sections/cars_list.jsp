<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Listino auto</h2>

<!-- SE l'utente è admin mostra il tasto-->
<a href="/admin/manage_cars"><strong>+</strong></a> <br>

<table border="1">
    <tr>
        <th>Casa</th>
        <th>Modello</th>
        <th>Tipo</th>
        <th>Targa</th>
        <th>Data Immatricolazione</th>
        <th>Ultima prenotazione</th>
    </tr>
    <c:forEach var="tempCar" items="${carsList}">
        <tr>
            <td>${tempCar.manufacturer}</td>
            <td>${tempCar.model}</td>
            <td>${tempCar.type}</td>
            <td>${tempCar.numPlate}</td>
            <td>${tempCar.regDate}</td>
            <td><a href="">Modifica</a> <a href="DeleteObj">Elimina</a></td>
        </tr>
    </c:forEach>
</table>

<!-- Mostrare tutta la lista di auto-->
