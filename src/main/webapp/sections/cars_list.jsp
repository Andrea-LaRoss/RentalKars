<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<div class="container">
    <h2>Listino Auto</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>


<div class="container">

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Marca</th>
            <th scope="col">Modello</th>
            <th scope="col">Tipo</th>
            <th scope="col">Targa</th>
            <th scope="col">Data Immatricolazione</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tempCar" items="${carsList}">
            <c:url var="update" value="CarServlet">
                <c:param name="command" value="LOAD"/>
                <c:param name="carId" value="${tempCar.id}"/>
            </c:url>
            <c:url var="delete" value="CarServlet">
                <c:param name="command" value="DELETE"/>
                <c:param name="carId" value="${tempCar.id}"/>
            </c:url>
            <tr class="table-secondary">
                <td>${tempCar.manufacturer}</td>
                <td>${tempCar.model}</td>
                <td>${tempCar.type}</td>
                <td>${tempCar.numPlate}</td>
                <td>${tempCar.regDate}</td>
                <td>
                    <c:if test="${loggedUser.admin eq true}">
                        <a class="btn btn-outline-primary" href="${update}">Modifica</a>
                        <a class="btn btn-outline-danger" href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${loggedUser.admin eq true}">
        <div class="d-grid gap-2">
            <a class="btn btn-info" href="admin/manage_cars.jsp"><strong>Aggiungi</strong></a>
        </div>
    </c:if>
</div>

<jsp:include page="/template/footer.jsp"/>
