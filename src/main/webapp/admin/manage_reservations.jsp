<jsp:include page="/template/header.jsp"/>

<h2>Aggiungi Prenotazione</h2>

<form action="Reservations" method="GET">
    <span>Nome:</span><br><input type="text" name="firstName" required placeholder="Inserisci il nome.."><br>
    <span>Cognome:</span><br><input type="text" name="lastName" required placeholder="Inserisci il cognome.."><br>
    <span>Email:</span><br><input type="email" name="email" required placeholder="Inserisci la mail.."><br>
    <span>Password:</span><br><input type="text" name="password" required placeholder="Inserisci la password.."><br>
    <span>Data di nascita:</span><br><input type="date" name="bDate" required placeholder="Inserisci la data di nascita.."><br>
    <input type="submit" value="Aggiungi">
</form>