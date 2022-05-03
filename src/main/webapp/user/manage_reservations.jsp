<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Gestisci Prenotazione</h2>
<h5>${errorMsg}</h5>

<form action="RentServlet" method="GET">
    <span>Data Inizio:</span><br><input type="date" name="startDate" required value="${rentUpdate.startDate}"><br>
    <span>Data Fine:</span><br><input type="date" name="endDate" required value="${rentUpdate.endDate}"><br>
    <input type="hidden" name="command" value="CHECKorUPDATE">
    <input type="hidden" name="rentId" value="${rentUpdate.id}">
    <input type="submit" value="Invia">
    <br><br>
    <table>
        <c:forEach var="tempCar" items="${cars}">
            <c:url var="confirm" value="RentServlet">
                <c:param name="command" value="RESERVE"/>
                <c:param name="startDate" value="${rentUpdate.startDate}"/>
                <c:param name="endDate" value="${rentUpdate.endDate}"/>
                <c:param name="carId" value="${tempCar.id}"/>
            </c:url>
            <tr>
                <td>${tempCar.manufacturer}</td>
                <td>${tempCar.model}</td>
                <td>${tempCar.numPlate}</td>
                <td><a href="${confirm}">Prenota</a></td>
            </tr>
        </c:forEach>
    </table>
</form>


