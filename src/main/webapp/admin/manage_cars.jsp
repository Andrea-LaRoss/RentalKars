<jsp:include page="/template/header.jsp"/>

<h2>Gestisci Auto</h2>

<form action="" method="GET">
    <span>Marca:</span><br><input type="text" name="manufacturer" required placeholder="Inserisci la marca.."><br>
    <span>Modello:</span><br><input type="text" name="model" required placeholder="Inserisci il modello.."><br>
    <span>Tipo:</span><br><input type="text" name="type" required placeholder="Inserisci il tipo di auto.."><br>
    <span>Targa:</span><br><input type="text" name="numPlate" required placeholder="Inserisci la targa.."><br>
    <span>Anno:</span><br><input type="date" name="regDate" required placeholder="Inserisci l'anno di immatricolazione.."><br>
    <input type="submit" value="Aggiungi">
</form>