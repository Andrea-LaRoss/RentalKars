<jsp:include page="/template/header.jsp"/>

<h2>Accedi al sito</h2>

    <form action="UserServlet" method="POST">
        <span>Email:</span> <br>
        <input type="email" name="email" required placeholder="Inserisci la tua e-mail.."><br>
        <span>Password:</span> <br>
        <input type="password" name="password" required placeholder="Inserisci la password..">
        <input type="submit" value="Accedi">
    </form>
Non sei ancora registrato? <a href="auth/register.jsp">Crea un account</a>

</body>

</html>
