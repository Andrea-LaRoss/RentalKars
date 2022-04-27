<jsp:include page="/template/header.jsp"/>
<h1 align="center">La tua dashboard admin</h1>
<hr>
<a href="admin/manage_cars.jsp">Gestisci il parco auto</a> <br>
<a href="admin/manage_users.jsp">Gestisci gli utenti</a> <br>
<a href="admin/manage_reservations.jsp">Gestisci le prenotazione</a> <br>
<%= request.getSession()%>
</body>
</html>