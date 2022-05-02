<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<h2>Gestisci Prenotazione</h2>
<h5>${errorMsg}</h5>

<form action="RentController" method="GET">
    <span>Data Inizio:</span><br><input type="date" name="startDate" required value="${rentUpdate.startDate}"><br>
    <span>Data Fine:</span><br><input type="date" name="endDate" required value="${rentUpdate.endDate}"><br>
    <input type="hidden" name="command" value="CHECKorUPDATE">
    <input type="hidden" name="rentId" value="${rentUpdate.id}">
    <input type="submit" value="Invia">
</form>

    <table>
        <c:forEach var="tempCar" items="${cars}">
            <c:url var="update" value="UserController">
                <c:param name="command" value="RESERVE"/>
                <c:param name="userId" value="${tempCar.id}"/>
            </c:url>
            <td>${tempCar.model}</td>
            <td>${tempCar.numPlate}</td>
            <td><a href="#">Prenota</a></td>
        </c:forEach>
    </table>