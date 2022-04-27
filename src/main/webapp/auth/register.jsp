<jsp:include page="/template/header.jsp"/>

<h2>Registrati al sito</h2>

<form action="UserRegister" method="POST">
    <span>Email:</span> <br>
    <input type="email" name="email" required> <br>
    <span>Password:</span> <br>
    <input type="password" name="password" required> <br>
    <span>Nome:</span> <br>
    <input type="text" name="firstName" required> <br>
    <span>Cognome: </span> <br>
    <input type="text" name="lastName" required> <br>
    <span> Data di nascita: </span> <br>
    <input type="date" name="birthday" required>
    <input type="submit" value="Registrati">
</form>

Possiedi un account? <a href="auth/login.jsp">Accedi</a>
<%= request.getSession()%>
</body>

</html>