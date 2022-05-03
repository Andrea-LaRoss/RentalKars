<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<div class="container">
    <h2>Gestisci prenotazione</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">
    <form action="RentServlet" method="GET">
        <div class="form-floating mb-4">
            <input type="date" class="form-control" name="startDate" id="startDate" required value="${rentUpdate.startDate}">
            <label for="startDate">Data di Nascita</label>
        </div>
        <div class="form-floating mb-4">
            <input type="date" class="form-control" name="endDate" id="endDate" required value="${rentUpdate.endDate}">
            <label for="endDate">Data di Nascita</label>
        </div>

        <input type="hidden" name="command" value="CHECKorUPDATE">
        <input type="hidden" name="rentId" value="${rentUpdate.id}">

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Invia">
        </div>

        <br><br><br>

        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Marca</th>
                    <th scope="col">Modello</th>
                    <th scope="col">Targa</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tempCar" items="${cars}">
                    <c:url var="confirm" value="RentServlet">
                        <c:param name="command" value="RESERVE"/>
                        <c:param name="startDate" value="${rentUpdate.startDate}"/>
                        <c:param name="endDate" value="${rentUpdate.endDate}"/>
                        <c:param name="carId" value="${tempCar.id}"/>
                    </c:url>
                    <tr class="table-secondary">
                        <td>${tempCar.manufacturer}</td>
                        <td>${tempCar.model}</td>
                        <td>${tempCar.numPlate}</td>
                        <td><a class="btn btn-outline-warning" href="${confirm}">Prenota</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </form>
</div>
<jsp:include page="/template/footer.jsp"/>


