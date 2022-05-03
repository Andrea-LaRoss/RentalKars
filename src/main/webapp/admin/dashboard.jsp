<jsp:include page="/template/header.jsp"/>

<div class="container">
    <h1>La tua dashboard admin</h1>
    <br><br>
    <div class="btn-group mb-3" role="group">
        <a class="btn btn-outline-primary" href="admin/manage_cars.jsp">Nuova Auto</a>
        <a class="btn btn-primary" href="CarServlet">Parco Auto >></a>
    </div>
    <br>
    <div class="btn-group mb-3" role="group">
        <a class="btn btn-outline-primary" href="admin/manage_users.jsp">Nuovo Utente</a>
        <a class="btn btn-primary" href="UserServlet">Lista Utenti >></a>
    </div>
    <br>
    <a class="btn btn-primary" href="RentServlet">Lista Prenotazioni >></a> <br>
</div>

<jsp:include page="/template/footer.jsp"/>
