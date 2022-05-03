<jsp:include page="/template/header.jsp"/>

<div class="container">
    <h2>Log-In</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">

    <form action="UserServlet" method="POST">

        <div class="form-group">
            <div class="form-floating mb-4">
                <input type="email" class="form-control" name="email" id="email" required value="${userUpdate.email}">
                <label for="email">Email</label>
            </div>
            <div class="form-floating mb-4">
                <input type="password" class="form-control" name="password" id="password" required value="${userUpdate.password}">
                <label for="password">Password</label>
            </div>
        </div>
        <div class="form-floating mb-4">
            <input type="date" class="form-control" name="birthday" id="birthday" required value="${userUpdate.birthday}">
            <label for="birthday">Data di Nascita</label>
        </div>

        <input type="hidden" name="command" value="VALIDATE">
        <input type="hidden" name="userId" value="${userUpdate.id}">

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Accedi">
        </div>

    </form>
Non sei ancora registrato? <a href="admin/manage_users.jsp">Crea un account</a>

<jsp:include page="/template/footer.jsp"/>

