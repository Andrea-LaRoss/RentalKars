<jsp:include page="/template/header.jsp"/>
<h1 align="center">La tua dashboard admin</h1>
<br><br>
<a href="admin/manage_cars.jsp">Aggiungi auto</a>  |  <a href="CarsController">Vai al parco auto >></a> <br>
<a href="admin/manage_users.jsp">Aggiungi utente</a>  |  <a href="UserController">Vedi lista utenti >></a> <br>
<a href="RentController">Vai alle prenotazioni >></a> <br>
<%= request.getSession()%>
</body>
</html>