<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp"/>

<c:forEach var="counter" items="${users_list}">
    ${counter}
</c:forEach>
 TEST

</body>
</html>
