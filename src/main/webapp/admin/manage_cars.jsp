<jsp:include page="/template/header.jsp"/>

<h2>Gestisci Auto</h2>
<h5>${errorMsg}</h5>

<form action="CarServlet" method="GET">
    <span>Marca:</span><br><input type="text" name="manufacturer" required value="${carUpdate.manufacturer}"><br>
    <span>Modello:</span><br><input type="text" name="model" required value="${carUpdate.model}"><br>
    <span>Tipo:</span><br><input type="text" name="type" required value="${carUpdate.type}"><br>
    <span>Targa:</span><br><input type="text" name="numPlate" required value="${carUpdate.numPlate}"><br>
    <span>Anno:</span><br><input type="date" name="regDate" required value="${carUpdate.regDate}"><br>
    <input type="hidden" name="command" value="ADDorUPDATE">
    <input type="hidden" name="carId" value="${carUpdate.id}">
    <input type="submit" value="Aggiungi">
</form>