<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<br>

<div class="container">
    <h2>Lista prenotazioni</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Cliente</th>
            <th scope="col">Auto</th>
            <th scope="col">Data Inizio</th>
            <th scope="col">Data Fine</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempRent" items="${rentsList}">
        <c:url var="update" value="RentServlet">
            <c:param name="command" value="LOAD"/>
            <c:param name="rentId" value="${tempRent.id}"/>
        </c:url>
        <c:url var="delete" value="RentServlet">
            <c:param name="command" value="DELETE"/>
            <c:param name="rentId" value="${tempRent.id}"/>
        </c:url>
        <c:url var="approve" value="RentServlet">
            <c:param name="command" value="APPROVE"/>
            <c:param name="rentId" value="${tempRent.id}"/>
        </c:url>
        <tr class="table-secondary">
                <td>${tempRent.user.firstName} ${tempRent.user.lastName}</td>
                <td>${tempRent.car.manufacturer} ${tempRent.car.model}</td>
                <td>${tempRent.startDate}</td>
                <td>${tempRent.endDate}</td>
                <td></td>
                <td></td>
                <td>
                    <a class="btn btn-outline-primary" href="${update}">Modifica</a>
                    <a class="btn btn-outline-danger" href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                    <a class="btn btn-outline-success" href="${approve}">Approva</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="d-grid gap-2">
        <a class="btn btn-info" href="user/manage_reservations.jsp"><strong>Nuova prenotazione</strong></a>
    </div>

</div>

<jsp:include page="/template/footer.jsp"/>
