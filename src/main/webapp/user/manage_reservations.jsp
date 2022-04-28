<jsp:include page="/template/header.jsp"/>

<h2>Gestisci Prenotazione</h2>

<form action="RentController" method="GET">
    <span>Data Inizio:</span><br><input type="date" name="startDate" required value="${rentUpdate.startDate}"><br>
    <span>Data Fine:</span><br><input type="date" min="startDate" name="endDate" required value="${rentUpdate.endDate}"><br>
    <input type="hidden" name="command" value="CHECKorUPDATE">
    <input type="hidden" name="rentId" value="${rentUpdate.id}">
    <input type="submit" value="Invia">
</form>