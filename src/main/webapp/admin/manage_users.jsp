<jsp:include page="/template/header.jsp"/>

<h2>Aggiungi Utente</h2>

<form action="UserController" method="GET">
    <span>Nome:</span><br><input type="text" name="firstName" required><br>
    <span>Cognome:</span><br><input type="text" name="lastName" required><br>
    <span>Email:</span><br><input type="email" name="email" required><br>
    <span>Password:</span><br><input type="password" name="password" required><br>
    <span>Data di nascita:</span><br><input type="date" name="birthday" required><br>
    <input type="hidden" name="command" value="ADD">
    <input type="submit" value="Aggiungi">
</form>