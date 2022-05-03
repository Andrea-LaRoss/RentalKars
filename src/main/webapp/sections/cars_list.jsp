<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Listino auto</h2>

<!-- SE l'utente è admin mostra il tasto-->
<a href="admin/manage_cars.jsp"><strong>Aggiungi</strong></a> <br>

<table border="1">
    <tr>
        <th>Casa</th>
        <th>Modello</th>
        <th>Tipo</th>
        <th>Targa</th>
        <th>Data Immatricolazione</th>
    </tr>
    <c:forEach var="tempCar" items="${carsList}">
        <c:url var="update" value="CarServlet">
            <c:param name="command" value="LOAD"/>
            <c:param name="carId" value="${tempCar.id}"/>
        </c:url>
        <c:url var="delete" value="CarServlet">
            <c:param name="command" value="DELETE"/>
            <c:param name="carId" value="${tempCar.id}"/>
        </c:url>
        <tr>
            <td>${tempCar.manufacturer}</td>
            <td>${tempCar.model}</td>
            <td>${tempCar.type}</td>
            <td>${tempCar.numPlate}</td>
            <td>${tempCar.regDate}</td>
            <td><a href="${update}">Modifica</a> | <a href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>