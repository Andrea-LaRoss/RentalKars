<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/header.jsp"/>

<div class="container">
    <br>
    <h1>RentalKars</h1>
    <br>
    <h3>Benvenuto ${loggedUser.firstName}</h3>
    <br>
    <br>
    <a class="btn btn-secondary" href="auth/login.jsp">Accedi/Registrati</a> <br>

</div>
<jsp:include page="template/footer.jsp"/>
