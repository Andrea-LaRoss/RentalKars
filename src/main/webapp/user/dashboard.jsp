<jsp:include page="/template/header.jsp"/>
<h1 align="center">La tua dashboard admin</h1>
<hr>
<!-- I link devono essere aggiornati appena implementato il login-->
<a href="sections/reservations_list.jsp">Vai alla lista delle tue prenotazioni</a> <br>
<a href="user/profile.jsp">Modifica il tuo profilo</a> <br>
<%= request.getSession()%>
</body>
</html>