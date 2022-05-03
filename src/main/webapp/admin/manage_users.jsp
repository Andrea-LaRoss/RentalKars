<jsp:include page="/template/header.jsp"/>

<h2>Form Utente</h2>
<h5>${errorMsg}</h5>

<form action="UserServlet" method="POST">
    <span>Nome:</span><br><input type="text" name="firstName" required value="${userUpdate.firstName}"><br>
    <span>Cognome:</span><br><input type="text" name="lastName" required value="${userUpdate.lastName}"><br>
    <span>Email:</span><br><input type="email" name="email" required value="${userUpdate.email}"><br>
    <span>Password:</span><br><input type="password" name="password" required value="${userUpdate.password}"><br>
    <span>Data di nascita:</span><br><input type="date" name="birthday" required value="${userUpdate.birthday}"><br>
    <input type="hidden" name="command" value="ADDorUPDATE">
    <input type="hidden" name="userId" value="${userUpdate.id}">
    <input type="submit" value="Salva">
</form>